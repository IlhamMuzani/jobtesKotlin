package com.ilham.jobteskotlin.ui.admin.create_member

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.core.view.isEmpty
import com.ilham.jobteskotlin.R
import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.ui.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_createmember.*
import java.text.SimpleDateFormat
import java.util.*

class Create_memberActivity : AppCompatActivity(), Create_memberContract.View {

    private var gender: String? = null
    lateinit var presenter: Create_memberPresenter
    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createmember)
        presenter = Create_memberPresenter(this)
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

        radio_lakilakicreate.isChecked = true

    }

    override fun initListener() {

        btncreatemember.setOnClickListener {
            val radioID = radio_jk_create.checkedRadioButtonId
            val radiobutton = findViewById<RadioButton>(radioID)
            gender = radiobutton.text.toString()

            if (edit_textNamacreate.text!!.isEmpty()) {
                showError("Kolom nama tidak boleh kosong!")
            } else if (edt_tanggalcreate.text!!.isEmpty()) {
                showError("Pilih tanggal lahir!")
            } else if (edit_textALamatcreate.text!!.isEmpty()) {
                showError("Kolom alamat tidak boleh kosong!")
            } else if (gender == null) {
                showError("Pilih Gender!")
            } else if (edit_textusernamecreate.text!!.isEmpty()) {
                showError("Kolom username tidak boleh kosong!")
            } else if (edit_textPasswordcreate.text!!.isEmpty()) {
                showError("Kolom password tidak boleh kosong!")
            } else if (edit_textKonfirmasiPasswordcreate.text!!.isEmpty()) {
                showError("Kolom alamat tidak boleh kosong!")
            } else

                presenter.insertMember(
                    edit_textNamacreate.text.toString(),
                    edt_tanggalcreate.text.toString(),
                    edit_textALamatcreate.text.toString(),
                    gender!!,
                    edit_textusernamecreate.text.toString(),
                    edit_textPasswordcreate.text.toString(),
                    edit_textKonfirmasiPasswordcreate.text.toString(),
                    "member"
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
            showSuccessLogin(responseUser.message[0])
        }else{
            showError(responseUser.message[0])
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

        edt_tanggalcreate.setOnClickListener {
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
        edt_tanggalcreate.setText(sdf.format(myCalendar.time))
    }

    override fun showSuccessLogin(message: String) {
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