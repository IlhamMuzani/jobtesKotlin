package com.ilham.jobteskotlin.ui.admin.create_member

import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class Create_memberPresenter(val view: Create_memberContract.View) : Create_memberContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun insertMember(
        nama: String,
        lahir: String,
        alamat: String,
        gender: String,
        username: String,
        passoword: String,
        password_confirmation: String,
        role: String
    ) {
        view.onLoading(true, "Loading ...")
        ApiService.endpoint.createmember(
            nama,
            lahir,
            alamat,
            gender,
            username,
            passoword,
            password_confirmation,
            role,
        ).enqueue(object : Callback<ResponseUser> {
            override fun onResponse(
                call: Call<ResponseUser>,
                response: Response<ResponseUser>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseUser: ResponseUser? = response.body()
                    view.onResult(responseUser!!)
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }
}