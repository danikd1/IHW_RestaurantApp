package Tools.DB

import Builders.AdminBuilder
import Builders.Director
import Builders.VisitorBuilder
import ConsoleUI
import Entities.*
import java.io.*

class CsvHelper(val program: ConsoleUI) {
    fun readCsv(path: String) {
        val hasher: HashingPassword = HashingPassword(program)
        val csvDelimiter = ","
        BufferedReader(FileReader(path)).use { reader ->
            var line = reader.readLine()
            line = reader.readLine()
            while (line != null) {
                val tokens = line.split(csvDelimiter)
                if (path == "DataFiles/Users.csv") {
                    if ((tokens.size == 4 || tokens.size == 5) && (tokens[0] == "Admin" || tokens[0] == "Visitor")) {
                        if (tokens[0] == "Visitor" && tokens.size == 5 && isNumericToX(tokens[4])) {
                            val builder = VisitorBuilder(program)
                            val director = Director(builder, tokens[1], tokens[2], tokens[3], true, tokens[4].toInt())
                            director.make()
                            program.users.add(builder.newUser())
                        } else if (tokens[0] == "Admin" && tokens.size == 4) {
                            val builder = AdminBuilder(program)
                            val director = Director(builder, tokens[1], tokens[2], tokens[3], true)
                            director.make()
                            program.users.add(builder.newUser())
                        }
                    }
                } else {
                    if (tokens.size == 4 && isNumericToX(tokens[1]) && isNumericToX(tokens[2]) && isNumericToX(tokens[3])) {
                        val dishName: String = tokens[0]
                        val amount = tokens[1].toInt()
                        val price = tokens[2].toInt()
                        val difficulty = tokens[3].toInt()
                        program.dishes.add(Dish(dishName, amount, price, difficulty))
                    }
                }
                line = reader.readLine()
            }
        }
        if (path == "DataFiles/Users.csv") {
            try {
                FileWriter(path, false).use { writer ->
                    if (File(path).length() == 0L) {
                        writer.append("Role,ID,Login,Password,MoneyAmount\n")
                    }
                    for (item in program.users) {
                        writer.append("$item\n")
                        writer.flush()
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun isNumericToX(toCheck: String): Boolean {
        return toCheck.toIntOrNull() != null
    }
}