package Entities

class Dish(val dishName: String, var dishAmount: Int, var dishPrice: Int, var dishDifficulty: Int) {

    fun setAmount(amount: Int) {
        if (amount <= 0) {
            println("Вы не можете задать неположительное число")
        } else {
            dishAmount = amount
        }
    }

    fun setPrice(price: Int) {
        if (price <= 0) {
            println("Вы не можете задать неположительное число")
        } else {
            dishPrice = price
        }
    }

    fun setDifficulty(dif: Int) {
        if (dif <= 0) {
            println("Вы не можете задать неположительное число")
        } else {
            dishDifficulty = dif
        }
    }

    override fun toString(): String {
        return "$dishName,$dishAmount,$dishPrice,$dishDifficulty"
    }
}