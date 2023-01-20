package com.ilham.jobteskotlin.ui.member.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilham.jobteskotlin.R
import com.ilham.jobteskotlin.data.model.DataUser
import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.data.prefs.PrefsManager
import com.ilham.jobteskotlin.ui.admin.login_admin.LoginadminPresenter
import com.ilham.jobteskotlin.ui.fragment.UserActivity
import com.ilham.jobteskotlin.ui.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_loginadmin.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
    lateinit var presenter: LoginPresenter
    lateinit var prefsManager: PrefsManager

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        prefsManager = PrefsManager(this)
    }

    override fun initActivity() {
        sLoading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Berhasil")
        sError = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal!")
        sAlert = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Perhatian!")

    }

    override fun initListener() {

        btnLoginmember.setOnClickListener {
            if (edit_textEmailmember.text!!.isEmpty()) {
                showError("Masukan Email !!")
            } else if (edit_textPasswordmember.text!!.isEmpty()) {
                showError("Masukan Password !!")
            } else
                presenter.doLogin(edit_textEmailmember.text.toString(), edit_textPasswordmember.text.toString())
        }
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when(loading){
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun onResult(responseUser: ResponseUser) {
        val status: Boolean = responseUser.status
        val message: String = responseUser.message[0]

        if (status){
            val user: DataUser = responseUser.data[0]
            presenter.setPrefs(prefsManager, user)
            showSuccessLogin(message)
        } else {
            if (status == false) {
                showError(message)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun showSuccessLogin(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismissWithAnimation()
                finish()
                startActivity(Intent(this, UserActivity::class.java))
            }
            .show()
    }

    override fun showSuccess(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismissWithAnimation()
            }
            .show()
    }

    override fun showError(message: String) {
        sError
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismiss()
            }
            .show()
    }

    override fun showAlert(message: String) {
        sAlert
            .setContentText(message)
            .setConfirmText("Ya")
            .setConfirmClickListener {
                it.dismissWithAnimation()
//                startPhoneNumberVerification(phone)
            }
            .setCancelText("Nanti")
            .setCancelClickListener {
                it.dismiss()
            }
            .show()
        sAlert.setCancelable(true)
    }
}