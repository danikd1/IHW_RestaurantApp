package Entities

import java.util.*

class Admin(userID : UUID, login : String, password : String) : User(userID, login, password) {
    override fun toString() : String {
        return "Admin,$userID,$userName,$userPassword"
    }
}