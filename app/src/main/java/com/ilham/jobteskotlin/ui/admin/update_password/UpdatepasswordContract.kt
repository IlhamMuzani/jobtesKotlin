package com.ilham.jobteskotlin.ui.admin.update_password

import com.ilham.jobteskotlin.data.model.ResponseUser

interface UpdatepasswordContract{

    interface Presenter {
        fun insertpasswordbaru ( id: Long, password: String, password_confirmation: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onResult(responseUser: ResponseUser)
        fun showSuccessOk(message: String)
        fun showSuccess(message: String)
        fun showError(message: String)
        fun showAlert(message: String)
    }
}