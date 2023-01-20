package com.ilham.jobteskotlin.ui.fragment.akun

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ilham.jobteskotlin.R
import com.ilham.jobteskotlin.data.model.Constant
import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.data.prefs.PrefsManager
import com.ilham.jobteskotlin.ui.admin.update_password.UpdatepasswordActivity
import com.ilham.jobteskotlin.ui.admin.update_profil.UpdateprofilActivity
import com.ilham.jobteskotlin.ui.fragment.UserActivity
import com.ilham.jobteskotlin.ui.member.update_passwordmem.Update_passwordActivity
import com.ilham.jobteskotlin.ui.member.updateprofil.Update_profilActivity
import com.ilham.jobteskotlin.ui.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.fragment_akun.*
import org.w3c.dom.Text

class AkunFragment : Fragment(), AkunContract.View {

    lateinit var presenter: AkunPresenter
    lateinit var prefsManager: PrefsManager

    lateinit var sLoading: SweetAlertDialog
    lateinit var sAlert: SweetAlertDialog
    lateinit var sError: SweetAlertDialog
    lateinit var sSuccess: SweetAlertDialog

    lateinit var layoutadmin: RelativeLayout
    lateinit var layoutmember: RelativeLayout

    lateinit var namaAdmin: TextView
    lateinit var tanggalLahir: TextView
    lateinit var laki_laki: TextView
    lateinit var perempuan: TextView
    lateinit var username: TextView
    lateinit var alamat: TextView
    lateinit var ubahpassword: RelativeLayout
    lateinit var ubahprofil: RelativeLayout
    lateinit var logoutadmin: LinearLayout

    lateinit var namaMember: TextView
    lateinit var tanggalLahirmember: TextView
    lateinit var laki_lakimember: TextView
    lateinit var perempuanmember: TextView
    lateinit var usernamemember: TextView
    lateinit var alamatmember: TextView
    lateinit var ubahpasswordmember: RelativeLayout
    lateinit var ubahprofilmember: RelativeLayout
    lateinit var logoutmember: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_akun, container, false)
        prefsManager = PrefsManager(requireActivity())
        presenter = AkunPresenter(this)
        presenter.doLogin(prefsManager)
        initFragment(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.detailprofiladmin(prefsManager.prefsId)
        presenter.detailprofilmember(prefsManager.prefsId)
    }

    override fun initFragment(view: View) {
        sLoading = SweetAlertDialog(requireActivity(), SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(
            requireActivity(), SweetAlertDialog.SUCCESS_TYPE
        ).setTitleText("Berhasil")
        sError =
            SweetAlertDialog(requireActivity(), SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal")
        sAlert = SweetAlertDialog(
            requireActivity(), SweetAlertDialog.WARNING_TYPE
        ).setTitleText("Perhatian!")

        layoutadmin = view.findViewById(R.id.layout_profiladmin)
        layoutmember = view.findViewById(R.id.layout_profilmember)
        namaAdmin = view.findViewById(R.id.txv_namaadmin)
        username = view.findViewById(R.id.username)
        tanggalLahir = view.findViewById(R.id.labeldate)
        laki_laki = view.findViewById(R.id.labellakilaki)
        perempuan = view.findViewById(R.id.labelperempuan)
        alamat = view.findViewById(R.id.alamat)
        ubahprofil = view.findViewById(R.id.updateprofil)
        ubahpassword = view.findViewById(R.id.ubahpassword)
        logoutadmin = view.findViewById(R.id.linelogoutadmin)
        namaMember = view.findViewById(R.id.txv_namamember)
        usernamemember = view.findViewById(R.id.labelusernamemember)
        tanggalLahirmember = view.findViewById(R.id.labeldatemember)
        laki_lakimember = view.findViewById(R.id.labellakilakimember)
        perempuanmember = view.findViewById(R.id.labelperempuanmember)
        alamatmember = view.findViewById(R.id.labelalamatmember)
        ubahprofilmember = view.findViewById(R.id.updateprofilmember)
        ubahpasswordmember = view.findViewById(R.id.ubahpasswordmember)
        logoutadmin = view.findViewById(R.id.linelogoutadmin)
        logoutmember = view.findViewById(R.id.linelogoutmember)

        ubahprofil.setOnClickListener {
            startActivity(Intent(requireActivity(), UpdateprofilActivity::class.java))
        }

        ubahpassword.setOnClickListener {
            Constant.USER_ID = prefsManager.prefsId.toLong()
            startActivity(Intent(requireActivity(), UpdatepasswordActivity::class.java))
        }

        logoutadmin.setOnClickListener {
            presenter.doLogout(prefsManager)
        }

        ubahprofilmember.setOnClickListener {
            startActivity(Intent(requireActivity(), Update_profilActivity::class.java))
        }

        ubahpasswordmember.setOnClickListener {
            Constant.USER_ID = prefsManager.prefsId.toLong()
            startActivity(Intent(requireActivity(), Update_passwordActivity::class.java))
        }

        logoutmember.setOnClickListener {
            presenter.doLogout(prefsManager)
        }
    }

    override fun onResultLogin(prefsManager: PrefsManager) {

    }

    override fun onResultLogout() {
        showSuccessOk("Berhasil Logout")
    }

    override fun onResult(responseUser: ResponseUser) {
        if (responseUser.data[0].role == "admin") {
            layoutadmin.visibility = View.VISIBLE
            layoutmember.visibility = View.GONE

            val admin = responseUser.data[0]
            namaAdmin.setText(admin!!.nama)
            username.setText(admin!!.username)
            alamat.setText(admin!!.alamat)
            tanggalLahir.setText(admin!!.lahir)
            if (responseUser.data[0].gender == "L") {
                laki_laki.visibility = View.VISIBLE
                perempuan.visibility = View.GONE
            } else {
                perempuan.visibility = View.VISIBLE
                laki_laki.visibility = View.GONE
            }
        } else {
            layoutadmin.visibility = View.GONE
            layoutmember.visibility = View.VISIBLE
        }
        val admin = responseUser.data[0]
        namaMember.setText(admin!!.nama)
        usernamemember.setText(admin!!.username)
        alamatmember.setText(admin!!.alamat)
        tanggalLahirmember.setText(admin!!.lahir)
        if (responseUser.data[0].gender == "L") {
            laki_lakimember.visibility = View.VISIBLE
            perempuanmember.visibility = View.GONE
        } else {
            perempuanmember.visibility = View.VISIBLE
            laki_lakimember.visibility = View.GONE
        }
    }

    override fun showSuccessOk(message: String) {
        sSuccess.setContentText(message).setConfirmText("OK").setConfirmClickListener {
            it.dismissWithAnimation()
            startActivity(Intent(requireActivity(), UserActivity::class.java))
        }.show()
    }

    override fun showSuccess(message: String) {
        sSuccess.setContentText(message).setConfirmText("OK").setConfirmClickListener {
            it.dismissWithAnimation()
        }.show()
    }

    override fun showError(message: String) {
        sError.setContentText(message).setConfirmText("OK").setConfirmClickListener {
            it.dismiss()
        }.show()
    }

    override fun showAlert(message: String) {
        sAlert.setContentText(message).setConfirmText("Ya").setConfirmClickListener {
            it.dismissWithAnimation()
        }.setCancelText("Nanti").setCancelClickListener {
            it.dismiss()
        }.show()
        sAlert.setCancelable(true)
    }
}