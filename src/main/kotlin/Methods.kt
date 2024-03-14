import Entities.*
import Tools.GetUnfinishedOrders

class Methods {
    companion object {
        fun validateInput(): Int {
            while (true) {
                val input = readln().toIntOrNull()
                if (input != null && input > 0) {
                    return input
                } else {
                    print("${AnsiColor.YELLOW}Значение должно быть положительным числом. Введите еще раз... ${AnsiColor.RESET}")
                }
            }
        }

        fun getAverageRating(list: MutableList<Review>): Double {
            return list.map { it.rating.toDouble() }.average()
        }


        fun getMostPopularDish(list: MutableList<Order>): Dish? {
            return list.flatMap { it.dishList }
                .groupingBy { it }
                .eachCount()
                .maxByOrNull { it.value }
                ?.key
        }

        fun printUnfinished(user: Visitor): Boolean {
            val unfinishedOrders = user.orderList.filter { it.status != "Finished" }

            unfinishedOrders.forEachIndexed { index, order ->
                println("${index + 1}. Пользователь ${order.user.login} сделал заказ по цене «${order.price} рублей», заказ имеет статус: ${AnsiColor.GREEN}${order.status}${AnsiColor.RESET}")
            }
            return true
        }


        fun findOrder(program: ConsoleUI, user: Visitor): Boolean {
            val checker = GetUnfinishedOrders()
            val input = validateInput()
            val count = checker.getUnfinished(user.orderList)
            return if (input in 1..count) {
                program.currentOrder = user.orderList[input - 1]
                true
            } else {
                ("${AnsiColor.RED}Такого заказа нет... Попробуйте еще раз${AnsiColor.RESET}")
                false
            }
        }

        fun getTimeToCook(list: MutableList<Dish>): Long {
            var time: Long = 0;
            for (item in list) {
                time += item.dishDifficulty;
            }
            return time
        }

        fun getOrderPrice(list: MutableList<Dish>): Int {
            var sum: Int = 0
            for (item in list) {
                sum += item.dishPrice
            }
            return sum
        }
    }
}