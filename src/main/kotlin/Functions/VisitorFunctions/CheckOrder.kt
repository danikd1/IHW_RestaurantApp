package Functions.VisitorFunctions

import Command
import ConsoleUI
import Entities.Visitor

class CheckOrder : Command {
    override fun execute(userLog: String?, program: ConsoleUI) {
        val user = program.currentUser as Visitor
        val orderList = user.orderList
        orderList.forEachIndexed { index, order ->
            println("${index + 1}. Пользователь ${order.user.login} сделал заказ по цене «${order.price} рублей», заказ имеет статус: ${AnsiColor.GREEN}${order.status}${AnsiColor.RESET}")
        }
        if (user.orderList.isEmpty()) {
            println("${AnsiColor.YELLOW}Сейчас у вас нет заказов.${AnsiColor.RESET}\n")
        }
    }
}