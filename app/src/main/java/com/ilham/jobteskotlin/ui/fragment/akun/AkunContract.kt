package com.ilham.jobteskotlin.ui.fragment.akun

import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.data.prefs.PrefsManager

interface AkunContract {

    interface Presenter{
        fun doLogin(prefsManager: PrefsManager)
        fun doLogout(prefsManager: PrefsManager)

        fun detailprofilmember(id: String)
        fun detailprofiladmin(id: String)
    }

    interface View {
        fun initFragment(view: android.view.View)
        fun onResultLogin(prefsManager: PrefsManager)
        fun onResultLogout()
        fun onResult(responseUser: ResponseUser)
        fun showSuccessOk(message: String)
        fun showSuccess(message: String)
        fun showError(message: String)
        fun showAlert(message: String)
    }
}