package Functions.AdminFunctions

import Command
import ConsoleUI

class ShowDishes : Command {
    override fun execute(userLog: String?, program: ConsoleUI) {
        if (program.dishes.isEmpty()) {
            println("В меню нет блюд\n")
        } else {
            program.dishes.forEachIndexed { index, dish ->
                println("${index + 1} ${dish.dishName} с количеством ${dish.dishAmount}, ценой ${dish.dishPrice} и сложностью ${dish.dishDifficulty}")
            }
            println()
        }
    }
}
