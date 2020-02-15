package tech.coderhub.auth.profileDetailsScreen

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Profile {
    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("login")
    @Expose
    val login: String? = null

    @SerializedName("firstName")
    @Expose
    val firstName: String? = null

    @SerializedName("lastName")
    @Expose
    val lastName: String? = null

    @SerializedName("email")
    @Expose
    val email: String? = null

    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String? = null

    @SerializedName("activated")
    @Expose
    val activated: Boolean? = null

    @SerializedName("langKey")
    @Expose
    val langKey: String? = null

    @SerializedName("createdBy")
    @Expose
    val createdBy: String? = null

    @SerializedName("createdDate")
    @Expose
    val createdDate: Any? = null

    @SerializedName("lastModifiedBy")
    @Expose
    val lastModifiedBy: String? = null

    @SerializedName("lastModifiedDate")
    @Expose
    val lastModifiedDate: Any? = null

    @SerializedName("authorities")
    @Expose
    val authorities: List<String>? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("full_name")
    @Expose
    var fullName: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("designation")
    @Expose
    var designation: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("supervisor")
    @Expose
    var supervisor: String? = null
}