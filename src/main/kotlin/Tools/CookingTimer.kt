package Tools

import Entities.Order
import java.util.*
import kotlin.concurrent.schedule

class CookingTimer(var time: Long, var order: Order?) {
    private var currentTime = 0L
    private var timer: Timer? = null
    var isPaused = false
    fun startTimer() {
        timer = Timer()
        timer?.schedule(0, 1000) {
            if (!isPaused) {
                currentTime++
            }
            if (currentTime == time) {
                order?.status = "Finished"
                timer!!.cancel()
            }
        }
    }
    fun cancelTimer() {
        timer?.cancel()
    }
    fun pauseTimer() {
        isPaused = !isPaused
    }
}