package Tools

import Entities.Order

class GetUnfinishedOrders {
    fun getUnfinished(orderList: MutableList<Order>): Int {
        var count = 0
        for (order in orderList) {
            if (order.status != "Finished") {
                count++
            }
        }
        return count
    }
}