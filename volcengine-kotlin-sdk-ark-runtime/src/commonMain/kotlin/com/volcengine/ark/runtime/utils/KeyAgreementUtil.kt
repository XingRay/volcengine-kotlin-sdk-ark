package com.volcengine.ark.runtime.utils

import org.bouncycastle.jce.provider.BouncyCastleProvider

object KeyAgreementUtil {
    init {
        Security.addProvider(BouncyCastleProvider())
    }

    private val HKDF_ALGORITHM = "HmacSHA256"

    @Throws(GeneralSecurityException::class)
    fun generateEciesKeyPair(publicKey: PublicKey): SessionData {
        val keyPairGenerator: KeyPairGenerator = KeyPairGenerator.getInstance("EC", "BC")
        val ecSpec: ECParameterSpec? = (publicKey as java.security.interfaces.ECPublicKey).getParams()
        keyPairGenerator.initialize(ecSpec)

        val ephemeralKeyPair: KeyPair = keyPairGenerator.generateKeyPair()
        val peerPrivateKey: PrivateKey? = ephemeralKeyPair.getPrivate()

        val keyAgreement: javax.crypto.KeyAgreement = javax.crypto.KeyAgreement.getInstance("ECDH", "BC")
        keyAgreement.init(peerPrivateKey)
        keyAgreement.doPhase(publicKey, true)
        val dh: ByteArray? = keyAgreement.generateSecret()

        val length = 32 + 12
        val buf: ByteArray = com.volcengine.ark.runtime.utils.KeyAgreementUtil.hkdf(dh, null, null, length)
        val cryptoKey: ByteArray? = Arrays.copyOfRange(buf, 0, 32)
        val cryptoNonce: ByteArray? = Arrays.copyOfRange(buf, 32, length)

        val ephemeralPublicKey: java.security.interfaces.ECPublicKey = ephemeralKeyPair.getPublic() as java.security.interfaces.ECPublicKey
        val token: ByteArray = com.volcengine.ark.runtime.utils.KeyAgreementUtil.marshalEcPublicKey(ephemeralPublicKey)
        val sessionToken: String? = Base64.getEncoder().encodeToString(token)

        return com.volcengine.ark.runtime.utils.KeyAgreementUtil.SessionData(cryptoKey, cryptoNonce, sessionToken)
    }

    @Throws(GeneralSecurityException::class)
    fun hkdf(sharedSecret: ByteArray?, salt: ByteArray?, info: ByteArray?, length: Int): ByteArray {
        var salt = salt
        val hmacExtract: Mac = Mac.getInstance(com.volcengine.ark.runtime.utils.KeyAgreementUtil.HKDF_ALGORITHM)
        if (salt == null) {
            salt = ByteArray(32)
        }
        val saltKey: SecretKeySpec = SecretKeySpec(salt, com.volcengine.ark.runtime.utils.KeyAgreementUtil.HKDF_ALGORITHM)
        hmacExtract.init(saltKey)
        val prk: ByteArray? = hmacExtract.doFinal(sharedSecret)

        val hmacExpand: Mac = Mac.getInstance(com.volcengine.ark.runtime.utils.KeyAgreementUtil.HKDF_ALGORITHM)
        val prkKey: SecretKeySpec = SecretKeySpec(prk, com.volcengine.ark.runtime.utils.KeyAgreementUtil.HKDF_ALGORITHM)
        hmacExpand.init(prkKey)

        val result = ByteArray(length)
        var t = ByteArray(0)
        var pos = 0

        while (pos < length) {
            hmacExpand.update(t)
            hmacExpand.update(info)
            hmacExpand.update((pos / 32 + 1).toByte())
            t = hmacExpand.doFinal()

            val copyLen: Int = Math.min(t.size, length - pos)
            System.arraycopy(t, 0, result, pos, copyLen)
            pos += copyLen
        }

        return result
    }

    fun marshalEcPublicKey(publicKey: java.security.interfaces.ECPublicKey): ByteArray {
        try {
            val point: ECPoint = publicKey.getW()
            val x: BigInteger = point.getAffineX()
            val y: BigInteger = point.getAffineY()

            val xBytes: ByteArray = com.volcengine.ark.runtime.utils.KeyAgreementUtil.toUnsignedBigEndian(x, 32)
            val yBytes: ByteArray = com.volcengine.ark.runtime.utils.KeyAgreementUtil.toUnsignedBigEndian(y, 32)

            val result = ByteArray(1 + 32 + 32)
            result[0] = 0x04
            System.arraycopy(xBytes, 0, result, 1, 32)
            System.arraycopy(yBytes, 0, result, 33, 32)

            return result
        } catch (e: Exception) {
            throw RuntimeException("Failed to marshal EC public key", e)
        }
    }

    fun toUnsignedBigEndian(value: BigInteger, length: Int): ByteArray {
        val bytes: ByteArray = value.toByteArray()
        val result = ByteArray(length)

        if (bytes.size > length) {
            System.arraycopy(bytes, bytes.size - length, result, 0, length)
        } else if (bytes.size < length) {
            System.arraycopy(bytes, 0, result, length - bytes.size, bytes.size)
        } else {
            System.arraycopy(bytes, 0, result, 0, length)
        }

        return result
    }

    fun decryptValidate(ciphertext: String): Boolean {
        try {
            val cipherBytes: ByteArray = ciphertext.getBytes(StandardCharsets.UTF_8)
            val cipherB64Bytes: ByteArray = Base64.getDecoder().decode(ciphertext)

            val left = cipherBytes.size.toDouble() / 4
            val middle = cipherB64Bytes.size.toDouble() / 3
            val right = cipherBytes.size.toDouble() / 4 - 1

            return left >= middle && middle >= right
        } catch (e: Exception) {
            return false
        }
    }

    fun decryptStringWithKey(key: ByteArray?, nonce: ByteArray?, encryptedContent: String): String? {
        try {
            var content: String?
            try {
                content = com.volcengine.ark.runtime.utils.KeyAgreementUtil.aesGcmDecryptBase64String(key, nonce, encryptedContent)
            } catch (e: Exception) {
                content = ""
            }

            if (content!!.isEmpty() || !com.volcengine.ark.runtime.utils.KeyAgreementUtil.decryptValidate(encryptedContent)) {
                content = com.volcengine.ark.runtime.utils.KeyAgreementUtil.aesGcmDecryptBase64List(key, nonce, encryptedContent)
            }

            return content
        } catch (e: Exception) {
            return ""
        }
    }

    fun encryptStringWithKey(key: ByteArray?, nonce: ByteArray?, plaintext: String): String? {
        try {
            val cipher: Cipher = javax.crypto.Cipher.getInstance("AES/GCM/NoPadding")
            val parameterSpec: GCMParameterSpec = GCMParameterSpec(128, nonce)
            val secretKeySpec: SecretKeySpec = SecretKeySpec(key, "AES")

            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKeySpec, parameterSpec)
            val encryptedData: ByteArray? = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8))

            val result: String? = Base64.getEncoder().encodeToString(encryptedData)
            return result
        } catch (e: Exception) {
            throw RuntimeException("Failed to encrypt data", e)
        }
    }

    fun aesGcmDecryptBase64String(key: ByteArray?, nonce: ByteArray?, ciphertext: String): String {
        try {
            var cleaned: String? = ciphertext.replaceAll("\\s", "")

            val cleanedBuilder = StringBuilder(cleaned!!)
            while (cleanedBuilder.length() % 4 !== 0) {
                cleanedBuilder.append("=")
            }
            cleaned = cleanedBuilder.toString()

            val cipherBytes: ByteArray? = Base64.getDecoder().decode(cleaned)

            return com.volcengine.ark.runtime.utils.KeyAgreementUtil.aesGcmDecrypt(key, nonce, cipherBytes)
        } catch (e: Exception) {
            throw RuntimeException("Base64字符串解密失败: " + e.getMessage(), e)
        }
    }

    @Throws(GeneralSecurityException::class)
    fun aesGcmDecrypt(key: ByteArray?, iv: ByteArray?, cipherBytes: ByteArray?): String {
        try {
            val decryptor: Cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC")
            val spec: GCMParameterSpec = GCMParameterSpec(128, iv)
            decryptor.init(Cipher.DECRYPT_MODE, SecretKeySpec(key, "AES"), spec)

            val decrypted: ByteArray? = decryptor.doFinal(cipherBytes)
            return String(decrypted, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            throw GeneralSecurityException("AES-GCM解密失败", e)
        }
    }

    fun aesGcmDecryptBase64List(key: ByteArray?, nonce: ByteArray?, ciphertext: String?): String {
        val result: List<String?> = ArrayList()

        val base64Array: List<String?> = com.volcengine.ark.runtime.utils.KeyAgreementUtil.extractBase64Blocks(ciphertext)

        for (b64 in base64Array) {
            try {
                val decrypted: String = com.volcengine.ark.runtime.utils.KeyAgreementUtil.aesGcmDecryptBase64String(key, nonce, b64)
                result.add(decrypted)
            } catch (e: Exception) {
                val cornerCaseResult: String = com.volcengine.ark.runtime.utils.KeyAgreementUtil.decryptCornerCase(key, nonce, b64)
                result.add(cornerCaseResult)
            }
        }

        return String.join("", result)
    }

    fun extractBase64Blocks(ciphertext: String?): List<String?> {
        val blocks: List<String?> = ArrayList()

        val base64Pattern = "(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{4})"
        val pattern: Pattern = Pattern.compile(base64Pattern)
        val matcher: Matcher = pattern.matcher(ciphertext)

        while (matcher.find()) {
            val block: String = matcher.group()
            if (!block.isEmpty()) {
                blocks.add(block)
            }
        }

        return blocks
    }

    private fun decryptCornerCase(key: ByteArray?, nonce: ByteArray?, data: String): String {
        var i = 20
        while (i < data.length()) {
            try {
                val decrypted: String = com.volcengine.ark.runtime.utils.KeyAgreementUtil.aesGcmDecryptBase64String(key, nonce, data.substring(0, i + 4))
                if (i + 4 == data.length()) {
                    return decrypted
                }
                return decrypted + com.volcengine.ark.runtime.utils.KeyAgreementUtil.decryptCornerCase(key, nonce, data.substring(i + 4))
            } catch (e: Exception) {
                i += 4
                continue
            }
            i += 4
        }
        return ""
    }

    /**
     * 会话令牌数据容器类
     * 包含加密密钥、随机数和会话令牌
     */
    class SessionData(val cryptoKey: ByteArray?, val cryptoNonce: ByteArray?, val sessionToken: String?)
}