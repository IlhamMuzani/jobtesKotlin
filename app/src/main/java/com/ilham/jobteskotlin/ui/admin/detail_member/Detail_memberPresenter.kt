package com.ilham.jobteskotlin.ui.admin.detail_member

import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class Detail_memberPresenter(val view: Detail_memberContract.View) : Detail_memberContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun detail_member(id: String) {
        view.onLoading(true, "Loading ...")
        ApiService.endpoint.memberdetail(id).enqueue(object : Callback<ResponseUser> {
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