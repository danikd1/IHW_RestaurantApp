package Entities

import Tools.CookingTimer

class Order (var dishList: MutableList<Dish>, var status : String, var price : Int, val user : Visitor, var timer : CookingTimer) {
    override fun toString() : String {
        return "${user.userName}, $price"
    }
}