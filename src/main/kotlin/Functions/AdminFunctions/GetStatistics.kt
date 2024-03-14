package Functions.AdminFunctions

import Command
import ConsoleUI
import Methods

class GetStatistics : Command {
    override fun execute(userLog: String?, program: ConsoleUI) {
        while (true) {
            program.showStatisticMenu()
            val input = readln()
            when (input) {
                "1" -> println(program.finishedOrders.size)
                "2" -> println(Methods.getAverageRating(program.reviews))
                "3" -> println(Methods.getMostPopularDish(program.finishedOrders)?.dishName ?: "Заказов пока нет")
                "4" -> break
                else -> println("${AnsiColor.RED}Неправильный ввод...${AnsiColor.RESET}")
            }
        }
    }
}