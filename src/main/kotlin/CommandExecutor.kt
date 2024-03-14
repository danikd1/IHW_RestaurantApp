import Functions.AdminFunctions.*
import Functions.VisitorFunctions.*

class CommandExecutor {
    private var map: MutableMap<String, Command>

    init {
        map = HashMap()
        map["1-Start"] = StartAsVisitor()
        map["2-Start"] = StartAsAdmin()

        map["1-User"] = MakeOrder()
        map["2-User"] = ChangeDish()
        map["3-User"] = CancelOrder()
        map["4-User"] = CheckOrder()
        map["5-User"] = PayForOrder()
        map["6-User"] = FillWallet()

        map["1-Admin"] = ShowDishes()
        map["2-Admin"] = AddDish()
        map["3-Admin"] = ChangeMenu()
        map["4-Admin"] = RemoveDish()
        map["5-Admin"] = CheckIncome()
        map["6-Admin"] = GetStatistics()

        map["1-Order"] = AddDishes()
        map["2-Order"] = StartCooking()
        map["1-Review"] = GetFeedback()
    }

    fun executeCommand(userLog: String, consoleUI: ConsoleUI) {
        val command = map[userLog]
        if (command != null) {
            command.execute(userLog, consoleUI)
        } else {
            throw IllegalArgumentException("${AnsiColor.RED}Неправильная команда... Попробуйте еще раз${AnsiColor.RESET}")
        }
    }
}

object AnsiColor {
    const val RESET = "\u001B[0m"
    const val RED = "\u001B[31m"
    const val GREEN = "\u001B[32m"
    const val YELLOW = "\u001B[33m"
}