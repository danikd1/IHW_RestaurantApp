package Functions.VisitorFunctions

import Command
import ConsoleUI
import Methods
import Entities.Order
import Entities.Visitor
import Tools.GetUnfinishedOrders

class CancelOrder : Command {
    override fun execute(userLog: String?, program: ConsoleUI) {
        val user = program.currentUser as Visitor
        var selectedOrder: Order? = null
        val orderChecker = GetUnfinishedOrders()
        while (true) {
            if (user.orderList.isEmpty()) {
                println("${AnsiColor.YELLOW}Нет заказов...\n${AnsiColor.RESET}")
                return
            }
            if (orderChecker.getUnfinished(user.orderList) > 0) {
                Methods.printUnfinished(user)
                println("Выберите заказ для отмены: ")
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
        selectedOrder.timer.cancelTimer()
        user.orderList.remove(selectedOrder)
        println("${AnsiColor.GREEN}Ваш заказ был успешно удален!${AnsiColor.RESET}")
    }
}