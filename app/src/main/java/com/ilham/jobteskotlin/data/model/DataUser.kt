package com.ilham.jobteskotlin.data.model

import com.google.gson.annotations.SerializedName

class DataUser(
    @SerializedName("id") val id: String?,
    @SerializedName("nama") val nama: String?,
    @SerializedName("lahir") val lahir: String?,
    @SerializedName("alamat") val alamat: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("role") val role: String?
)