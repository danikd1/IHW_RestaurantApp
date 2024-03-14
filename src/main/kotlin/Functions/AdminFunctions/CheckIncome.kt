package Functions.AdminFunctions

import Command
import ConsoleUI

class CheckIncome : Command {
    override fun execute(userLog: String?, program: ConsoleUI) {
        println("Ваш текущий доход составляет ${program.income}")
    }
}