import Tools.DB.CsvHelper

fun main() {
    val consoleUI = ConsoleUI()
    val reader = CsvHelper(consoleUI)
    reader.readCsv("DataFiles/Users.csv")
    reader.readCsv("DataFiles/Dishes.csv")

    var inputLog: String? = ""
    while (true) {
        consoleUI.showStartMenu()
        inputLog = readln()
        when (inputLog) {
            "1", "2" -> consoleUI.commandExecutor.executeCommand("$inputLog-Start", consoleUI)
            "3" -> {
                println("Выход..."); break
            }

            else -> println("${AnsiColor.RED}Введены некорректные данные...${AnsiColor.RESET}")
        }
    }
}
//              ${AnsiColor.RED}
//           ${AnsiColor.RESET}

//          ${AnsiColor.GREEN}