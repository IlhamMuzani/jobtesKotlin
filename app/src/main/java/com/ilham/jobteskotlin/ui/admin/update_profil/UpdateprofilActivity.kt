package com.ilham.jobteskotlin.ui.admin.update_profil

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.ilham.jobteskotlin.R
import com.ilham.jobteskotlin.data.model.DataUser
import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.data.prefs.PrefsManager
import com.ilham.jobteskotlin.ui.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_updateprofiladmin.*
import java.text.SimpleDateFormat
import java.util.*

class UpdateprofilActivity : AppCompatActivity(), UpdateprofilContract.View {

    private var gender: String? = null
    lateinit var admin: List<DataUser>
    lateinit var presenter: UpdateprofilPresenter
    lateinit var prefsManager: PrefsManager
    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updateprofiladmin)
        presenter = UpdateprofilPresenter(this)
        prefsManager = PrefsManager(this)
        presenter.getDetail(prefsManager.prefsId)
    }

    override fun onStart() {
        super.onStart()
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

        tanggal()

    }

    override fun initListener() {

        btnupdateadmin.setOnClickListener {

            val radioID = radio_JKadmin.checkedRadioButtonId
            val radiobutton = findViewById<RadioButton>(radioID)
            gender = radiobutton.text.toString()

            if (edit_textNamaupdateadmin.text!!.isEmpty()) {
                showError("Kolom Nama tidak boleh kosong!")
            } else if (edit_textUsernameupdateadmin.text!!.isEmpty()) {
                showError("Kolom Username tidak boleh kosong!")
            } else if (edt_tanggalupdate.text!!.isEmpty()) {
                showError("Masukkan Tanggal !!!")
            } else if (edit_textAlamatupdateadmin.text!!.isEmpty()) {
                showError("Kolom Alamat tidak boleh kosong!")
            } else if (gender == null) {
                showError("Pilih gender!")
            } else
                presenter.Updateprofiladmin(
                    prefsManager.prefsId,
                    edit_textNamaupdateadmin.text.toString(),
                    edt_tanggalupdate.text.toString(),
                    edit_textAlamatupdateadmin.text.toString(),
                    gender!!,
                    edit_textUsernameupdateadmin.text.toString(),
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
        if (responseUser.status) {
//            responseUser.user
            showSuccessOk(responseUser.message[0])
        } else {
            showError(responseUser.message[0])
        }
    }

    override fun onResultDetail(responseUser: ResponseUser) {
        admin = responseUser.data
        edit_textNamaupdateadmin.setText(admin[0].nama)
        edit_textUsernameupdateadmin.setText(admin[0].username)
        edt_tanggalupdate.setText(admin[0].lahir)
        edit_textAlamatupdateadmin.setText(admin[0].alamat)

        if (admin[0].gender == "L") {
            radio_lakilakiadmin.isChecked = true
        } else {
            radio_perempuanadmin.isChecked = true
        }
    }

    fun tanggal() {
        val myCalender = Calendar.getInstance()
        val datePicter = DatePickerDialog.OnDateSetListener { view, year, month,
                                                              dayofmonth ->
            myCalender.set(Calendar.YEAR, year)
            myCalender.set(Calendar.MONTH, month)
            myCalender.set(Calendar.DAY_OF_MONTH, dayofmonth)
            updateLable(myCalender)
        }

        edt_tanggalupdate.setOnClickListener {
            DatePickerDialog(
                this, datePicter, myCalender.get(Calendar.YEAR),
                myCalender.get(Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale.UK)
        edt_tanggalupdate.setText(sdf.format(myCalendar.time))
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