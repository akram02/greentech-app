package com.akramkhan.badhan.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("activated")
    @Expose
    var activated = true

    var email: String? = null

    @SerializedName("login")
    @Expose
    var phone: String? = null

    @SerializedName("firstName")
    @Expose
    var name: String? = null


    val lastName: String? = null
    var authorities: ArrayList<String>? = null
    var role: String? = null
    var role_user: Boolean? = null
    var role_moderator: Boolean? = null
    var role_admin: Boolean? = null

    constructor() {}

    constructor(uid: String, email: String, phone: String, name: String, role: String, role_user: Boolean, role_moderator: Boolean, role_admin: Boolean) {
        this.email = email
        this.phone = phone
        this.name = name
        this.role = role
        this.role_user = role_user
        this.role_moderator = role_moderator
        this.role_admin = role_admin
    }
}
