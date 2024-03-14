package Functions.VisitorFunctions

import Command
import ConsoleUI
import Methods
import Entities.Review
import Tools.DB.CsvWriter

class GetFeedback : Command {
    private val writer: CsvWriter = CsvWriter()

    override fun execute(userLog: String?, program: ConsoleUI) {
        println("Оцените заказ от 1 до 5:")
        val rating = generateSequence {
            Methods.validateInput().also {
                if (it < 1 || it > 5) println("${AnsiColor.RED}Неверный ввод! Попробуйте снова${AnsiColor.RESET}")
            }
        }.first { it in 1..5 }

        println("Вы можете добавить комментарий к вашему отзыву (оставьте строку пустой, чтобы пропустить):")
        val text = readln()

        val review = Review(program.currentUser, rating, text)
        program.reviews.add(review)
        writer.writeInCsv(program.reviews)
        println("${AnsiColor.GREEN}Ваш отзыв успешно добавлен! Спасибо!\n${AnsiColor.RESET}")
    }
}
