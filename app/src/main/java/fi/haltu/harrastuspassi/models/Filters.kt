package fi.haltu.harrastuspassi.models

import fi.haltu.harrastuspassi.utils.minutesToTime
import java.io.Serializable

class Filters : Serializable {
    var categories: HashSet<Int> = HashSet()
    var dayOfWeeks: HashSet<Int> = HashSet()
    var startTimeFrom: Int = 0 // 0:00
    var startTimeTo: Int = 24 * 60 - 1 // 23:59

    fun isEmpty(): Boolean {
        return categories.isEmpty() && dayOfWeeks.isEmpty()
    }

    override fun toString(): String {
        return "categories: $categories\ndayOfWeeks:${dayOfWeeks}\n" +
               "startTimeFrom:${minutesToTime(startTimeFrom)}\n" +
               "startTimeTo:${minutesToTime(startTimeTo)}"
    }
}