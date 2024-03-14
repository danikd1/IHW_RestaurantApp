package Functions.AdminFunctions

import Command
import ConsoleUI
import Entities.Admin
import Tools.AuthenticationForm
import Tools.RegistrationForm

class StartAsAdmin : Command {
    override fun execute(userLog: String?, program: ConsoleUI) {
        val logged = AuthenticationForm()
        val registered = RegistrationForm()
        val admin = getAdmin(registered, logged, program)
        println("${AnsiColor.GREEN}Вы вошли в систему как Администратор.${AnsiColor.RESET}")
        performAdminActions(admin, program)
    }

    private fun getAdmin(register: RegistrationForm, log: AuthenticationForm, program: ConsoleUI): Admin {
        while (true) {
            try {
                program.showLoginMenu()
                val input = readln()
                return when (input) {
                    "1" -> log.loginUser(program, "Admin") as Admin
                    "2" -> register.signIn(program, "Admin") as Admin
                    else -> throw IllegalArgumentException("${AnsiColor.RED}Неверный номер. Введите еще раз...${AnsiColor.RESET}")
                }
            } catch (ex: IllegalArgumentException) {
                println("${ex.message}\n")
            }
        }
    }

    private fun performAdminActions(admin: Admin, program: ConsoleUI) {
        while (true) {
            try {
                program.showAdminOptions()
                val input = readln()
                if (input == "7") return
                program.commandExecutor.executeCommand("$input-Admin", program)
            } catch (ex: Exception) {
                println("${AnsiColor.RED}An error occurred: ${ex.message}\n${AnsiColor.RESET}")
            }
        }
    }
}