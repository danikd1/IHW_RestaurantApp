package Functions.VisitorFunctions

import Command
import ConsoleUI
import Methods
import Entities.Order
import Entities.Visitor
import Tools.GetUnfinishedOrders

class ChangeDish : Command {
    override fun execute(userLog: String?, program: ConsoleUI) {
        var selectedOrder: Order? = null
        val user = program.currentUser as Visitor
        val orderChecker = GetUnfinishedOrders()
        while (true) {
            if (user.orderList.isEmpty()) {
                println("${AnsiColor.YELLOW}Нет заказов...\n${AnsiColor.RESET}")
                return
            }
            if (orderChecker.getUnfinished(user.orderList) > 0) {
                Methods.printUnfinished(user)
                println("Выберите заказ для внесения изменений: ")
                if (Methods.findOrder(program, user)) {
                    break
                }
            } else {
                // Если нет незавершенных заказов, выводим соответствующее сообщение и выходим из цикла
                println("${AnsiColor.YELLOW}Все ваши заказы выполнены${AnsiColor.RESET}\n")
                return
            }
        }

        selectedOrder = program.currentOrder
        selectedOrder.timer.pauseTimer()
        println(selectedOrder.timer.isPaused)

        while (true) {
            program.showOrderOptions()
            val input = readln()

            when (input) {
                "1" -> {
                    program.commandExecutor.executeCommand("$input-Order", program)
                }

                "2" -> {
                    if (selectedOrder.dishList.isEmpty()) {
                        println("${AnsiColor.YELLOW}Нет заказов...\n${AnsiColor.RESET}")
                    } else {
                        if (selectedOrder.status == "Creating") {
                            program.commandExecutor.executeCommand("$input-Order", program)
                        } else {
                            selectedOrder.price = Methods.getOrderPrice(selectedOrder.dishList)
                            selectedOrder.timer.time = Methods.getTimeToCook(selectedOrder.dishList)
                            selectedOrder.timer.pauseTimer()
                        }
                    }
                    break
                }

                "3" -> break
                else -> println("${AnsiColor.RED}Неправильный ввод...${AnsiColor.RESET}")
            }
        }

    }
}