package com.ilham.jobteskotlin.ui.fragment.akun

import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.data.prefs.PrefsManager
import com.ilham.jobteskotlin.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AkunPresenter(val view: AkunContract.View) : AkunContract.Presenter {

    override fun doLogin(prefsManager: PrefsManager) {
        if (prefsManager.prefIsLogin) view.onResultLogin(prefsManager)
    }

    override fun doLogout(prefsManager: PrefsManager) {
        prefsManager.logout()
        view.showSuccessOk("Berhasil Logout")
        view.onResultLogout()
    }

    override fun detailprofilmember(id: String) {
        ApiService.endpoint.memberdetail(id).enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful){
                    val responseUser: ResponseUser? = response.body()
                    if (responseUser!!.status)
                        view.onResult(responseUser!!)
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
            }

        })
    }

    override fun detailprofiladmin(id: String) {
        ApiService.endpoint.admindetail(id).enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful){
                    val responseUser: ResponseUser? = response.body()
                    if (responseUser!!.status)
                        view.onResult(responseUser!!)
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
            }

        })
    }
}