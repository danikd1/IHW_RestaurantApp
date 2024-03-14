package Tools.DB

import Entities.*
import java.io.File
import java.io.FileWriter
import java.io.IOException

class CsvWriter {
    private var header = ""
    private var path = ""
    fun writeInCsv(data: MutableList<*>) {
        var append = true
        try {
            val (path, header, append) = when {
                data.any { it is Visitor || it is Admin } -> Triple(
                    "DataFiles/Users.csv",
                    "Role,ID,Login,Password,MoneyAmount\n",
                    false
                )

                data.any { it is Order } -> Triple("DataFiles/PaidOrders.csv", "Order by,Price\n", true)
                data.any { it is Dish } -> Triple("DataFiles/Dishes.csv", "Name,Amount,Price,Difficulty\n", false)
                data.any { it is Review } -> Triple("DataFiles/Reviews.csv", "Reviewed by,Rating,Description\n", true)
                else -> Triple("DataFiles/Dishes.csv", "Name,Amount,Price,Difficulty\n", false)
            }

            FileWriter(path, append).use { writer ->
                if (File(path).length() == 0L) {
                    writer.append(header)
                }
                for (item in data) {
                    writer.append("$item\n")
                    writer.flush()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}