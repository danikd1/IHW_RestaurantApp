package Builders

import Entities.User

interface Builder {
    fun setUUID(id: String?)
    fun setUserLogin(login: String)
    fun setUserPassword(password: String, isHashed: Boolean)
    fun newUser(): User
}