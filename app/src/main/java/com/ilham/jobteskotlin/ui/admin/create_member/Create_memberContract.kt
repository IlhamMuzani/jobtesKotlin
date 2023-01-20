package com.ilham.jobteskotlin.ui.admin.create_member

import com.ilham.jobteskotlin.data.model.ResponseUser

interface Create_memberContract {

    interface Presenter {
        fun insertMember(nama: String, lahir: String, alamat:String, gender:String, username: String, password: String, password_confirmation: String, role: String)
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