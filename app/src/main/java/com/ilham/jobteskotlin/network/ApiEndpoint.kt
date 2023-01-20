package com.ilham.jobteskotlin.network

import com.ilham.jobteskotlin.data.model.ResponseUser
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface ApiEndpoint {

    @FormUrlEncoded
    @POST("orangtua/auth/register")
    fun register(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("telp") telp: String,
        @Field("gender") gender: String,
        @Field("alamat") alamat: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
//        @Field("fcm") fcm: String
    ): Call<ResponseUser>

    @FormUrlEncoded
    @POST("admin/login")
    fun loginadmin(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Call<ResponseUser>

    @FormUrlEncoded
    @POST("member/login")
    fun loginmember(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Call<ResponseUser>

    @POST("member-detail/{id}")
    fun memberdetail(
        @Path("id") id: String
    ) : Call<ResponseUser>

    @POST("member-delete/{id}")
    fun deletemember(
        @Path("id") id: Long
    ): Call<ResponseUser>

    @POST("member-list")
    fun memberlist(
    ): Call<ResponseUser>

    @POST("admin-detail/{id}")
    fun admindetail(
        @Path("id") id: String
    ) : Call<ResponseUser>

    @POST("admin-password/{id}")
    fun insertpasswordadmin(
        @Path("id") id: Long,
        @Query("password") password: String,
        @Query("password_confirmation") password_confirmation: String,
    ): Call<ResponseUser>

    @POST("member-password/{id}")
    fun insertpasswordmember(
        @Path("id") id: Long,
        @Query("password") password: String,
        @Query("password_confirmation") password_confirmation: String,
    ): Call<ResponseUser>

    @POST("admin-update/{id}")
    fun updateprofiladmin(
        @Path("id") id: String,
        @Query("nama") nama: String,
        @Query("lahir") lahir: String,
        @Query("alamat") alamat: String,
        @Query("gender") gender: String,
        @Query("username") username: String,
    ): Call<ResponseUser>

    @POST("member-update/{id}")
    fun updateprofilmember(
        @Path("id") id: String,
        @Query("nama") nama: String,
        @Query("lahir") lahir: String,
        @Query("alamat") alamat: String,
        @Query("gender") gender: String,
        @Query("username") username: String,
    ): Call<ResponseUser>

    @POST("tambah-member")
    fun createmember(
        @Query("nama") nama: String,
        @Query("lahir") lahir: String,
        @Query("alamat") alamat: String,
        @Query("gender") gender: String,
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("password_confirmation") password_confirmation: String,
        @Query("role") role: String,
    ): Call<ResponseUser>

}