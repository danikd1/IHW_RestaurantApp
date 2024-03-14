package Tools.DB

import ConsoleUI
import java.security.MessageDigest

class HashingPassword(val handler: ConsoleUI) {
    fun hashPassword(password: String): String {
        val algorithm = "SHA-256"
        val digest = MessageDigest.getInstance(algorithm)
        val hash = digest.digest(password.toByteArray())
        return hash.joinToString("") { "%02x".format(it) }
    }
}