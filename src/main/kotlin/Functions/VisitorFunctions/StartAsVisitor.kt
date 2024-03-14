package Functions.VisitorFunctions

import Command
import ConsoleUI
import Entities.Visitor
import Tools.AuthenticationForm
import Tools.RegistrationForm

class StartAsVisitor : Command {
    override fun execute(userLog: String?, program: ConsoleUI) {
        val logged = AuthenticationForm()
        val registered = RegistrationForm()

        val visitor = getVisitor(registered, logged, program)
        performVisitorActions(visitor, program)
    }

    private fun getVisitor(register: RegistrationForm, enter: AuthenticationForm, program: ConsoleUI): Visitor {
        while (true) {
            try {
                program.showLoginMenu()
                val input = readln()
                return when (input) {
                    "1" -> enter.loginUser(program, "Visitor") as Visitor
                    "2" -> register.signIn(program, "Visitor") as Visitor
                    else -> throw IllegalArgumentException("${AnsiColor.RED}Неверный номер. Введите еще раз...${AnsiColor.RESET}")
                }
            } catch (ex: IllegalArgumentException) {
                println("${ex.message}\n")
            }
        }
    }

    private fun performVisitorActions(visitor: Visitor, program: ConsoleUI) {
        while (true) {
            try {
                program.showVisitorOptions()
                val input = readln()
                if (input == "7") return
                program.commandExecutor.executeCommand("$input-User", program)
            } catch (ex: Exception) {
                println("${AnsiColor.RED}An error occurred: ${ex.message}\n${AnsiColor.RESET}")
            }
        }
    }
}
