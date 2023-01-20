package com.ilham.jobteskotlin.ui.admin.update_password

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilham.jobteskotlin.R
import com.ilham.jobteskotlin.data.model.Constant
import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.ui.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_update_password.*

class UpdatepasswordActivity : AppCompatActivity(), UpdatepasswordContract.View {

    lateinit var presenter: UpdatepasswordPresenter

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)
        presenter = UpdatepasswordPresenter(this)
    }

    override fun initActivity() {
        sLoading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Berhasil")
        sError = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal!")
        sAlert = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Perhatian!")

    }

    override fun initListener() {

        btnsandibaruadmin.setOnClickListener {
            if (edit_textPasswordadmin.text!!.isEmpty()) {
                showError("Masukan password !!")
            } else if (edit_textKonfirmasiPasswordadmin.text!!.isEmpty()) {
                showError("Masukan password konfirmasi !!")
            } else
                presenter.insertpasswordbaru(
                    Constant.USER_ID,
                    edit_textPasswordadmin.text.toString(),
                    edit_textKonfirmasiPasswordadmin.text.toString()
                )
        }
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun onResult(responseUser: ResponseUser) {
        val status: Boolean = responseUser.status
        val message: String = responseUser.message[0]

        if (status) {
            showSuccessOk(message)
        } else {
            if (status == false) {
                showError(message)
            }
        }
    }

    override fun showSuccessOk(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismissWithAnimation()
                finish()
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
            }
            .setCancelText("Nanti")
            .setCancelClickListener {
                it.dismiss()
            }
            .show()
        sAlert.setCancelable(true)
    }
}