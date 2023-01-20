package com.ilham.jobteskotlin.ui.admin.detail_member

import com.ilham.jobteskotlin.data.model.ResponseUser

interface Detail_memberContract {

    interface Presenter {
        fun detail_member(id: String)
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