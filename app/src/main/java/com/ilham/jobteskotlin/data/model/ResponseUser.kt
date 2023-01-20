package com.ilham.jobteskotlin.data.model

import com.google.gson.annotations.SerializedName

class ResponseUser(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: List<String>,
    @SerializedName("data") val data: List<DataUser>
)