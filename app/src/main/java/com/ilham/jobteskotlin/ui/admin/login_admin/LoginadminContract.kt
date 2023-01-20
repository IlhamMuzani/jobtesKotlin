package com.ilham.jobteskotlin.ui.admin.login_admin

import com.ilham.jobteskotlin.data.model.DataUser
import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.data.prefs.PrefsManager

interface LoginadminContract {

    interface Presenter {
        fun doLogin(username: String, password: String)
        fun setPrefs(prefsManager: PrefsManager, dataUser: DataUser)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onResult(responseUser: ResponseUser)
        fun showSuccessLogin(message: String)
        fun showSuccess(message: String)
        fun showError(message: String)
        fun showAlert(message: String)
    }
}