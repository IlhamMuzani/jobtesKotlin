package com.ilham.jobteskotlin.ui.fragment.home

import com.ilham.jobteskotlin.data.model.DataUser
import com.ilham.jobteskotlin.data.model.ResponseUser

interface HomeContract {

    interface Presenter {
        fun detailmember(id: String)
        fun detailadmin(id: String)
        fun listmember()
        fun deletemember(id: Long)
    }

    interface View {
        fun initFragment(view: android.view.View)
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onResult(responseUser: ResponseUser)
        fun onResultDelete(responseUser: ResponseUser)
        fun showDialogDelete(dataUser: DataUser, position: Int)
        fun onResultList(responseUser: ResponseUser)
        fun showSuccessOk(message: String)
        fun showSuccess(message: String)
        fun showError(message: String)
        fun showAlert(message: String)
    }
}