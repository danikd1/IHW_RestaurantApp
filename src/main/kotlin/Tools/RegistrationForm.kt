package Tools

import ConsoleUI
import Builders.Director
import Builders.VisitorBuilder
import Builders.AdminBuilder
import Entities.User
import Entities.Visitor
import Entities.Admin
import Tools.DB.CsvWriter
import java.util.*

class RegistrationForm {

    fun signIn(program: ConsoleUI, userType: String): User {
        val writer: CsvWriter = CsvWriter()

        val login = requestUniqueLogin(program, userType)

        print("Введите ваш пароль: ")
        val password = readln(); println()
        println("${AnsiColor.GREEN}Аккаунт успешно создан!${AnsiColor.RESET}")
        val builder = when (userType.lowercase(Locale.getDefault())) {
            "visitor" -> VisitorBuilder(program)
            "admin" -> AdminBuilder(program)
            else -> throw IllegalArgumentException("${AnsiColor.RED}Неподдерживаемый тип пользователя${AnsiColor.RESET}")
        }
        val director = Director(builder, null, login, password, false)
        director.make()
        val user = builder.newUser()
        program.users.add(user)
        program.currentUser = user

        writer.writeInCsv(program.users)

        return user
    }

    //Запрос уникального логина у пользователя
    fun requestUniqueLogin(program: ConsoleUI, userType: String): String {
        var login: String
        do {
            print("Введите ваш логин: ")
            login = readln()
            val exists = program.users.any { user ->
                user.userName == login &&
                        ((userType == "Visitor" && user is Visitor) || (userType == "Admin" && user is Admin))
            }
            if (exists) {
                println("${AnsiColor.YELLOW}Учетная запись с логином $login уже существует. Выберите другой логин.${AnsiColor.RESET}")
            }
        } while (exists)
        return login
    }
}
