package com.ilham.jobteskotlin.ui.member.updateprofil

import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Update_profilPresenter(val view: Update_profilContract.View) : Update_profilContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun getDetail(id: String) {
        view.onLoading(true,"Loading...")
        ApiService.endpoint.memberdetail(id).enqueue( object : Callback<ResponseUser> {
            override fun onResponse(
                call: Call<ResponseUser>,
                response: Response<ResponseUser>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseUser:ResponseUser? = response.body()
                    view.onResultDetail( responseUser!! )
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun Updateprofilmember(
        id: String,
        nama: String,
        lahir: String,
        alamat: String,
        gender: String,
        username: String,
    ) {
        view.onLoading(true, "Loading ...")
        ApiService.endpoint.updateprofilmember(
            id,
            nama,
            lahir,
            alamat,
            gender,
            username,
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