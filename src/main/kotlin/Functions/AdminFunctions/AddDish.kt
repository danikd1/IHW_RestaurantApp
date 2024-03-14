package Functions.AdminFunctions

import Command
import Methods
import ConsoleUI
import Entities.Dish
import Tools.DB.CsvWriter

class AddDish : Command {
    private val writer: CsvWriter = CsvWriter()
    override fun execute(userLog: String?, consoleUI: ConsoleUI) {
        print("Введите название блюда: ")
        val name = readln()
        print("Введите количество блюд: ")
        val amount = Methods.validateInput()
        print("Введите цену блюда: ")
        val price = Methods.validateInput()
        print("Введите время приготовления блюда: (в секундах) ")
        val time = Methods.validateInput()

        consoleUI.dishes.add(Dish(name, amount, price, time))
        writer.writeInCsv(consoleUI.dishes)
        print("${AnsiColor.GREEN}Блюдо '$name' успешно добавлено!${AnsiColor.RESET}\n")
    }
}