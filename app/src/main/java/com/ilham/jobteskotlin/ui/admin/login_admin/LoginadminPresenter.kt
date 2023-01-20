package com.ilham.jobteskotlin.ui.admin.login_admin

import com.ilham.jobteskotlin.data.model.DataUser
import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.data.prefs.PrefsManager
import com.ilham.jobteskotlin.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginadminPresenter(val view: LoginadminContract.View) : LoginadminContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }
    override fun doLogin(username: String, password: String) {
        view.onLoading(true, "Loading..")
        ApiService.endpoint.loginadmin(username, password).enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                view.onLoading(false)
                if (response.isSuccessful){
                    val responseUser: ResponseUser? = response.body()
                    view.onResult(responseUser!!)
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun setPrefs(prefsManager: PrefsManager, dataUser: DataUser) {
        prefsManager.prefIsLogin = true
        prefsManager.prefsId = dataUser.id!!
        prefsManager.prefs_is_username = dataUser.username!!
        prefsManager.prefs_is_nama = dataUser.nama!!
        prefsManager.prefs_is_lahir = dataUser.lahir!!
        prefsManager.prefs_is_alamat = dataUser.alamat!!
        prefsManager.prefs_is_gender = dataUser.gender!!
        prefsManager.prefs_is_role = dataUser.role!!
    }
}