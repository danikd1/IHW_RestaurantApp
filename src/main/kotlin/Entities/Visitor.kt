package Entities

import Entities.Order
import Entities.User
import java.util.UUID

data class Visitor(
    val id: UUID,
    val login: String,
    var password: String,
    var wallet: Int,
    var orderList: MutableList<Order> = mutableListOf()
) : User(id, login, password) {

    override fun toString(): String {
        return "Visitor,$userID,$userName,$userPassword,$wallet"
    }
}