package Builders

import ConsoleUI
import Tools.DB.HashingPassword
import Entities.Visitor
import java.util.*

class VisitorBuilder(val program: ConsoleUI) : Builder {
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

    fun setMoney(amount: Int) {
        this.wallet = amount
    }

    override fun newUser(): Visitor {
        return Visitor(id, login, password, wallet)
    }
}