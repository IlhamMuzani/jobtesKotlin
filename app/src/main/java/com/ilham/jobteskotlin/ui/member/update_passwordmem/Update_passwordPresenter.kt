package com.ilham.jobteskotlin.ui.member.update_passwordmem

import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Update_passwordPresenter(val view: Update_passwordContract.View) : Update_passwordContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)

    }

    override fun insertpasswordbaru(id: Long, password: String, password_confirmation: String) {
        view.onLoading(true, "Loading...")
        ApiService.endpoint.insertpasswordmember(id, password, password_confirmation)
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