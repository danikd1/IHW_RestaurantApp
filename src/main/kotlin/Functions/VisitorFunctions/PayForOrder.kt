package Functions.VisitorFunctions

import Command
import ConsoleUI
import Methods
import Entities.Visitor
import Tools.DB.CsvWriter

class PayForOrder : Command {
    private val writer: CsvWriter = CsvWriter()

    override fun execute(userLog: String?, program: ConsoleUI) {
        val user: Visitor = program.currentUser as Visitor
        val finishedOrders = user.orderList.filter { it.status == "Finished" }

        if (finishedOrders.isEmpty()) {
            println("${AnsiColor.YELLOW}Готовых заказов нет\n${AnsiColor.RESET}")
            return
        }

        finishedOrders.forEachIndexed { index, order ->
            println("${index + 1}. Заказ пользователя ${order.user.login}${AnsiColor.GREEN} готов${AnsiColor.RESET}, к оплате «${order.price} рублей»")
        }
        println("Выберите заказ для оплаты:")
        val input = Methods.validateInput()

        val selectedOrder = finishedOrders.getOrNull(input - 1)
        if (selectedOrder == null) {
            println("${AnsiColor.RED}Неправильный ввод...${AnsiColor.RESET}")
            return
        }

        if (user.wallet < selectedOrder.price) {
            println("${AnsiColor.YELLOW}Недостаточно денег для оплаты${AnsiColor.RESET}")
            return
        }

        user.wallet -= selectedOrder.price
        println("${AnsiColor.GREEN}Оплата прошла успешно!${AnsiColor.RESET}")
        program.income += selectedOrder.price
        user.orderList.remove(selectedOrder)
        writer.writeInCsv(mutableListOf(selectedOrder))

        program.showReviewOption()
        when (readln()) {
            "1" -> program.commandExecutor.executeCommand("1-Review", program)
            "2" -> println("${AnsiColor.GREEN}Спасибо за ваш заказ!${AnsiColor.RESET}")
            else -> println("${AnsiColor.RED}Неправильный ввод...${AnsiColor.RESET}")
        }
    }
}
