package com.ilham.jobteskotlin.ui.member.updateprofil

import com.ilham.jobteskotlin.data.model.ResponseUser
interface Update_profilContract {

    interface Presenter {
        fun getDetail(id: String)
        fun Updateprofilmember(id: String, nama: String, lahir: String, alamat:String, gender:String, username: String)
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