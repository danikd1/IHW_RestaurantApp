package Functions.VisitorFunctions

import Command
import ConsoleUI
import Tools.DB.CsvWriter

class AddDishes : Command {
    private val writer: CsvWriter = CsvWriter()

    override fun execute(userLog: String?, program: ConsoleUI) {
        if (program.dishes.isEmpty()) {
            println("${AnsiColor.YELLOW}Меню пустое...${AnsiColor.RESET}")
            return
        }

        println("Список доступных блюд:")
        program.dishes.forEachIndexed { index, dish ->
            println("${index + 1}. ${dish.dishName} с количеством ${dish.dishAmount}. Цена: ${dish.dishPrice}")
        }

        val input = Methods.validateInput() - 1
        if (input !in program.dishes.indices) {
            println("${AnsiColor.RED}Неправильный номер блюда...${AnsiColor.RESET}")
            return
        }

        val selectedDish = program.dishes[input]
        if (selectedDish.dishAmount <= 0) {
            println("${AnsiColor.YELLOW}Нет в наличии.${AnsiColor.RESET}")
            return
        }

        program.currentOrder.dishList.add(selectedDish)
        program.currentOrder.price += selectedDish.dishPrice
        selectedDish.dishAmount--
        writer.writeInCsv(program.dishes)
        println("${AnsiColor.GREEN}${selectedDish.dishName} был добавлен в ваш заказ.${AnsiColor.RESET}")
    }
}
