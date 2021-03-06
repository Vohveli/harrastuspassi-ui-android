package fi.haltu.harrastuspassi.models

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import fi.haltu.harrastuspassi.utils.getOptionalJSONObject
import org.json.JSONObject
import java.io.Serializable

class Hobby(json: JSONObject? = null) : Serializable, ClusterItem {
    var id: Int = 0
    var name: String = ""
    var imageUrl: String? = null
    //var category: Int = 0
    //var categories: List<Int> = listOf()
    var description: String = ""
    var organizer: Organizer? = null
    var location: Location = Location()
    var municipality: String? = null
    var priceType: String = ""
    var priceAmount: Double=0.00

    init {
        if(json != null) {
            id = json.getInt("id")
            name = json.getString("name")
            imageUrl = json.getString("cover_image")
            //val categoriesString = json.getString("categories")
            //categories = categoriesString.removeSurrounding("[", "]").split(",").map { it.toInt() }

            //category = json.getInt("category")

            description = json.getString("description")

            val organizerObject = getOptionalJSONObject(json, "organizer")

            if(organizerObject != null) {
                organizer = Organizer(organizerObject)
            }
            val locationObject = getOptionalJSONObject(json, "location")
            if (locationObject != null) {
                location = Location(locationObject)
            }

            //municipality = json.getString("municipality")
            priceType = json.getString("price_type")
            priceAmount = json.getDouble("price_amount")
            Log.d("Hobby $id", this.toString())
        }
    }

    override fun getSnippet(): String {
        return ""
    }

    override fun getTitle(): String {
        return ""
    }

    override fun getPosition(): LatLng {
        return LatLng(this.location.lat!!, this.location.lon!!)
    }

    override fun equals(other: Any?): Boolean {
        val hobby = other as Hobby
        return this.location.id == hobby.location.id
    }

    override fun toString(): String {
        return "{id:$id, name:$name, location:$location, price_type: $priceType, price_amount: $priceAmount}"
    }

    override fun hashCode(): Int {
        return this.location.id.hashCode()
    }
}