package com.ilham.jobteskotlin.ui.admin.update_profil

import com.ilham.jobteskotlin.data.model.ResponseUser
interface UpdateprofilContract {

    interface Presenter {
        fun getDetail(id: String)
        fun Updateprofiladmin(id: String, nama: String, lahir: String, alamat:String, gender:String, username: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onResult(responseUser: ResponseUser)
        fun onResultDetail(responseUser:ResponseUser)
        fun showSuccessOk(message: String)
        fun showSuccess(message: String)
        fun showError(message: String)
        fun showAlert(message: String)
    }
}