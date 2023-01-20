package com.ilham.jobteskotlin.ui.admin.update_password

import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatepasswordPresenter(val view: UpdatepasswordContract.View) : UpdatepasswordContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)

    }

    override fun insertpasswordbaru(id: Long, password: String, password_confirmation: String) {
        view.onLoading(true, "Loading...")
        ApiService.endpoint.insertpasswordadmin(id, password, password_confirmation)
            .enqueue(object : Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val responseUser: ResponseUser? = response.body()
                        view.onResult( responseUser!! )
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    view.onLoading(false)
                }

            })
    }
}