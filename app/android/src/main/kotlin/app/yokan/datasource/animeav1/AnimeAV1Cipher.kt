package app.yokan.datasource.animeav1

import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AnimeAV1Cipher {
    private const val AES_KEY_STRING = "kiemtienmua911ca"
    private const val AES_IV_STRING = "1234567890oiuytr"

    fun decryptHex(hexData: String): String {
        val cleanHex = hexData.trim()
        val cipherBytes = hexToBytes(cleanHex)
        val keySpec = SecretKeySpec(AES_KEY_STRING.toByteArray(StandardCharsets.UTF_8), "AES")
        val ivSpec = IvParameterSpec(AES_IV_STRING.toByteArray(StandardCharsets.UTF_8))

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        val decryptedBytes = cipher.doFinal(cipherBytes)
        return String(decryptedBytes, StandardCharsets.UTF_8)
    }

    private fun hexToBytes(hex: String): ByteArray {
        val len = hex.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            val high = Character.digit(hex[i], 16)
            val low = Character.digit(hex[i + 1], 16)
            if (high == -1 || low == -1) {
                throw IllegalArgumentException("Carácter hexadecimal no válido en posición $i: ${hex.substring(i, i + 2)}")
            }
            data[i / 2] = ((high shl 4) + low).toByte()
            i += 2
        }
        return data
    }
}
