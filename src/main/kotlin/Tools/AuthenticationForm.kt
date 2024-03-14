package Tools

import ConsoleUI
import Entities.Admin
import Entities.User
import Entities.Visitor
import Tools.DB.HashingPassword

class AuthenticationForm {
    fun loginUser(program: ConsoleUI, userType: String): User {
        val hasher = HashingPassword(program)

        print("Введите логин: ");
        val userName = readln()

        val currentUser = program.users.find {
            it.userName == userName && ((userType == "Visitor" && it is Visitor) || (userType == "Admin" && it is Admin))
        } ?: throw IllegalArgumentException("${AnsiColor.YELLOW}Нет пользователя с таким именем.${AnsiColor.RESET}")

        while (true) {
            print("Введите пароль: ")
            val password = readln()
            if (currentUser.userPassword == hasher.hashPassword(password)) {
                println("${AnsiColor.GREEN}Вход в систему выполнен успешно.${AnsiColor.RESET}")
                break
            }
            println("${AnsiColor.RED}Неверный пароль!${AnsiColor.RESET}")
        }
        program.currentUser = currentUser
        return currentUser
    }
}