package Functions.VisitorFunctions

import Command
import ConsoleUI
import Entities.Order
import Entities.Visitor
import Tools.CookingTimer
import Tools.DB.CsvWriter

class MakeOrder : Command {
    private val writer: CsvWriter = CsvWriter()

    override fun execute(userLog: String?, program: ConsoleUI) {
        val currentUser = program.currentUser as Visitor
        val order = Order(mutableListOf(), "Creating", 0, currentUser, CookingTimer(0, null))
        program.currentOrder = order

        var continueLoop = true

        while (continueLoop) {
            program.showOrderOptions()
            val input = readln()

            when (input) {
                "1" -> {
                    program.commandExecutor.executeCommand("$input-Order", program)
                }

                "2" -> {
                    continueLoop = if (order.dishList.isEmpty()) {
                        println("${AnsiColor.YELLOW}Ваш заказ пустой. Закажите минимум одно блюдо... ${AnsiColor.RESET}")
                        true
                    } else {
                        program.finishedOrders.add(order)
                        program.commandExecutor.executeCommand("$input-Order", program)
                        currentUser.orderList.add(order)
                        false
                    }
                }

                "3" -> {
                    if (order.dishList.isEmpty()) {
                        continueLoop = false
                    } else {
                        println("${AnsiColor.YELLOW}Вы уже добавили блюда. Удалить заказ можно после его оформления... .${AnsiColor.RESET}")
                        continueLoop = true
                    }
                }

                else -> println("${AnsiColor.RED}Неправильный ввод...${AnsiColor.RESET}")
            }
        }

        if (!continueLoop) {
            if (!order.dishList.isEmpty()) {
                println("${AnsiColor.GREEN}Ваш заказ обработан и начал готовиться.${AnsiColor.RESET}")
            }
        }
    }
}
