package Functions.VisitorFunctions

import Command
import ConsoleUI
import Entities.Order
import Methods
import Tools.CookingTimer

class StartCooking : Command {
    override fun execute(userLog: String?, program: ConsoleUI) {
        val order: Order = program.currentOrder
        val time: Long = Methods.getTimeToCook(order.dishList)
        program.currentOrder.timer = CookingTimer(time, order)
        order.status = "В процессе..."
        order.price = Methods.getOrderPrice(order.dishList)
        program.currentOrder.timer.startTimer()
    }

}