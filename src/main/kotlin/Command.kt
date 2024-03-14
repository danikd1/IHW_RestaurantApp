internal interface Command {
    fun execute(userLog: String?, consoleUI: ConsoleUI)
}