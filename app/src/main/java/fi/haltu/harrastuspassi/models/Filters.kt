package fi.haltu.harrastuspassi.models

import com.google.gson.Gson
import fi.haltu.harrastuspassi.utils.minutesToTime
import java.io.Serializable

class Filters : Serializable {
    var categories: HashSet<Int> = HashSet()
    var dayOfWeeks: HashSet<Int> = HashSet()
    // Insert time as minutes and use minutesToTime() -method to convert
    var startTimeFrom: Int = 0 // 0:00
    var startTimeTo: Int = 24 * 60 - 1 // 23:59
    //Location
    var latitude: Double = 64.9600
    var longitude: Double = 27.5900
    //Whether filter is modified or not
    var isModified = false
        set(value) {
            if (value) {
                isMapUpdated = false
                isListUpdated = false
            }
            field = value
        }
    var isMapUpdated = true
        set(value) {
            if (value && isListUpdated) {
                isModified = false
            }
            field = value
        }
    var isListUpdated = true
        set(value) {
            if (value && isMapUpdated) {
                isModified = false
            }
            field = value
        }
    // Whether show only free hobbies or not
    var showFree: Boolean = false
    var searchText: String = ""
    //var showHobbiesWithPromotion = false
    override fun toString(): String {
        return "categories: $categories\ndayOfWeeks:$dayOfWeeks\n" +
                "startTimeFrom:${minutesToTime(startTimeFrom)}\n" +
                "startTimeTo:${minutesToTime(startTimeTo)}\n" +
                "Location(lat/long): $latitude, $longitude\n" +
                "showFree: $showFree\n"
    }

    fun clone(): Filters {
        val stringFilters = Gson().toJson(this, Filters::class.java)
        return Gson().fromJson<Filters>(stringFilters, Filters::class.java)
    }

    fun isSameValues(compareFilters: Filters): Boolean {
        val isSameCategories = categories == compareFilters.categories
        val isSameDayOfWeeks = dayOfWeeks == compareFilters.dayOfWeeks
        val isSameStartTimeFrom = startTimeFrom == compareFilters.startTimeFrom
        val isSameStartTimeTo = startTimeTo == compareFilters.startTimeTo
        val isSameLatitude = latitude == compareFilters.latitude
        val isSameLongitude = longitude == compareFilters.longitude

        return isSameCategories && isSameDayOfWeeks && isSameStartTimeFrom && isSameStartTimeTo && isSameLatitude && isSameLongitude
    }
}