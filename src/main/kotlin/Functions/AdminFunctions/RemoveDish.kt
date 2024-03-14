package Functions.AdminFunctions

import Command
import ConsoleUI
import Methods
import Tools.DB.CsvWriter

class RemoveDish : Command {
    private val writer: CsvWriter = CsvWriter()

    override fun execute(userLog: String?, program: ConsoleUI) {
        if (program.dishes.isEmpty()) {
            println("Меню пустое...")
            return
        }

        program.dishes.forEachIndexed { index, dish ->
            println("${index + 1} ${dish.dishName} с суммой ${dish.dishAmount}, ценой ${dish.dishPrice}")
        }

        println("Выберите блюдо для удаления:")
        val input = Methods.validateInput()

        if (input in 1..program.dishes.size) {
            program.dishes.removeAt(input - 1)
            writer.writeInCsv(program.dishes)
            println("${AnsiColor.GREEN}Блюдо успешно удалено!${AnsiColor.RESET}")
        } else {
            println("${AnsiColor.RED}Неправильный ввод...${AnsiColor.RESET}")
        }
    }
}