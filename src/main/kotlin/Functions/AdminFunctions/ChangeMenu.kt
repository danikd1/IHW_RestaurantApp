package Functions.AdminFunctions

import Command
import ConsoleUI
import Methods
import Tools.DB.CsvWriter

class ChangeMenu : Command {
    private val writer = CsvWriter()

    override fun execute(userLog: String?, program: ConsoleUI) {
        if (program.dishes.isEmpty()) {
            println("Меню пустое...")
            return
        }

        program.dishes.forEachIndexed { index, dish ->
            println("${index + 1} ${dish.dishName} с суммой ${dish.dishAmount}, ценой ${dish.dishPrice}")
        }

        println("Выберите блюдо для внесения изменений:")
        val input = Methods.validateInput()

        if (input in 1..program.dishes.size) {
            val selectedDish = program.dishes[input - 1]
            program.showChangingOption()
            when (val choose = readln()) {
                "1" -> {
                    println("Введите новую сумму:")
                    selectedDish.dishAmount = Methods.validateInput()
                }

                "2" -> {
                    println("Введите новую цену:")
                    selectedDish.dishPrice = Methods.validateInput()
                }

                "3" -> {
                    println("Введите новую сложность (в секундах):")
                    val diff = Methods.validateInput()
                    selectedDish.setDifficulty(diff)
                }

                "4" -> return
                else -> println("${AnsiColor.RED}Неправильный ввод...${AnsiColor.RESET}")
            }
            writer.writeInCsv(program.dishes)
            println("${AnsiColor.GREEN}Блюдо успешно обновлено.${AnsiColor.RESET}")
        } else {
            println("${AnsiColor.RED}Неверный номер блюда...${AnsiColor.RESET}")
        }
    }
}
