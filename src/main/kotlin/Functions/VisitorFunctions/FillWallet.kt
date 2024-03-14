package Functions.VisitorFunctions

import Command
import ConsoleUI
import Entities.Visitor
import Tools.DB.CsvWriter

class FillWallet : Command {
    val writer: CsvWriter = CsvWriter()
    override fun execute(userLog: String?, program: ConsoleUI) {
        val user = program.currentUser as Visitor
        println("Сколько денег нужно внести на депозит?")
        val input = Methods.validateInput()
        user.wallet += input
        writer.writeInCsv(program.users)
        println("${AnsiColor.GREEN}Вы перевели ${input} рублей на свой кошелек. Остаток по счету: ${user.wallet}${AnsiColor.RESET} рублей")
    }
}