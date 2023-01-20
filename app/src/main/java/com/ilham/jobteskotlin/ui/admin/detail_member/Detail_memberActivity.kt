package com.ilham.jobteskotlin.ui.admin.detail_member


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ilham.jobteskotlin.R
import com.ilham.jobteskotlin.data.model.Constant
import com.ilham.jobteskotlin.data.model.DataUser
import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.ui.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_detailmember.*

class Detail_memberActivity : AppCompatActivity(), Detail_memberContract.View {

    lateinit var presenter: Detail_memberPresenter
    lateinit var anak: DataUser

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailmember)
        presenter = Detail_memberPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.detail_member(Constant.USER_ID.toString())
    }

    override fun initActivity() {

        sLoading = SweetAlertDialog(
            this,
            SweetAlertDialog.PROGRESS_TYPE
        )
        sSuccess = SweetAlertDialog(
            this,
            SweetAlertDialog.SUCCESS_TYPE
        ).setTitleText("Berhasil")
        sError = SweetAlertDialog(
            this,
            SweetAlertDialog.ERROR_TYPE
        ).setTitleText("Gagal")
        sAlert = SweetAlertDialog(
            this,
            SweetAlertDialog.WARNING_TYPE
        ).setTitleText("Perhatian!")

    }

    override fun initListener() {

    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun onResult(responseUser: ResponseUser) {
        anak = responseUser.data[0]
        txv_nama.setText(anak.nama)
        txv_alamat.setText(anak.alamat)
        txv_tanggallahir.setText(anak.lahir)
        txv_username.setText(anak.username)

        if (responseUser.data[0].gender == "L"){
            txv_lakilakidetail.visibility = View.VISIBLE
            txv_perempuandetail.visibility = View.GONE
        }else{
            txv_lakilakidetail.visibility = View.GONE
            txv_perempuandetail.visibility = View.VISIBLE
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