package Builders

import ConsoleUI
import Entities.Admin
import Tools.DB.HashingPassword
import java.util.*

class AdminBuilder(val program: ConsoleUI) : Builder {
    var login: String = ""
    var password: String = ""
    lateinit var id: UUID
    val hasher: HashingPassword = HashingPassword(program)
    private var wallet: Int = 0

    override fun setUserLogin(login: String) {
        this.login = login
    }

    override fun setUserPassword(password: String, isHashed: Boolean) {
        this.password = if (isHashed) password else hasher.hashPassword(password)
    }

    override fun setUUID(id: String?) {
        this.id = id?.let { UUID.nameUUIDFromBytes(it.toByteArray()) } ?: UUID.randomUUID()
    }

    override fun newUser(): Admin {
        return Admin(id, login, password)
    }
}