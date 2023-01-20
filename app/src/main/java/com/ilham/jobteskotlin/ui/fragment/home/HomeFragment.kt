package com.ilham.jobteskotlin.ui.fragment.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilham.jobteskotlin.R
import com.ilham.jobteskotlin.data.model.Constant
import com.ilham.jobteskotlin.data.model.DataUser
import com.ilham.jobteskotlin.data.model.ResponseUser
import com.ilham.jobteskotlin.data.prefs.PrefsManager
import com.ilham.jobteskotlin.databinding.FragmentHomeBinding
import com.ilham.jobteskotlin.ui.admin.create_member.Create_memberActivity
import com.ilham.jobteskotlin.ui.fragment.UserActivity
import com.ilham.jobteskotlin.ui.fragment.akun.AkunPresenter
import com.ilham.jobteskotlin.ui.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.fragment_home.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment(), HomeContract.View {

    lateinit var presenter: HomePresenter
    lateinit var prefsManager: PrefsManager
    lateinit var memberAdapter: MemberAdapter
    lateinit var datamember: DataUser

    lateinit var sLoading: SweetAlertDialog
    lateinit var sAlert: SweetAlertDialog
    lateinit var sError: SweetAlertDialog
    lateinit var sSuccess: SweetAlertDialog

    lateinit var calendar: Calendar
    lateinit var simpleDateFormat: SimpleDateFormat
    lateinit var date: String
    lateinit var tanggal: TextView
    lateinit var tanggal2: TextView
    lateinit var rcvMember: RecyclerView
    lateinit var tambahmember: CardView

    lateinit var layoutadmin: LinearLayout
    lateinit var layoutmember: LinearLayout
    lateinit var user : TextView
    lateinit var textlogin : TextView

    lateinit var nama1: TextView
    lateinit var tanggal1: TextView
    lateinit var lakilaki: TextView
    lateinit var perempuan: TextView
    lateinit var alamat1: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        prefsManager = PrefsManager(requireActivity())
        presenter = HomePresenter(this)
        initFragment(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        if (prefsManager.prefs_is_role == "admin") {
            presenter.detailadmin(prefsManager.prefsId)
            layoutadmin.visibility = View.VISIBLE
            layoutmember.visibility = View.GONE
            presenter.listmember()
            tanggal2.text = date
            textlogin.visibility = View.GONE
        } else if (prefsManager.prefs_is_role == "member") {
            presenter.detailmember(prefsManager.prefsId)
            layoutadmin.visibility = View.GONE
            layoutmember.visibility = View.VISIBLE
            tanggal.text = date
            textlogin.visibility = View.GONE
        } else {
            user.visibility = View.GONE
            textlogin.visibility = View.VISIBLE
        }
    }

    override fun initFragment(view: View) {
        sLoading = SweetAlertDialog(requireActivity(), SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(
            requireActivity(),
            SweetAlertDialog.SUCCESS_TYPE
        ).setTitleText("Berhasil")
        sError =
            SweetAlertDialog(requireActivity(), SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal")
        sAlert = SweetAlertDialog(
            requireActivity(),
            SweetAlertDialog.WARNING_TYPE
        ).setTitleText("Perhatian!")

        calendar = Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("EEEE, dd-MM-yyyy")
        date = simpleDateFormat.format(calendar.time)

        user = view.findViewById(R.id.user2)
        layoutmember = view.findViewById(R.id.layout_member)
        layoutadmin = view.findViewById(R.id.layout_admin)
        tanggal = view.findViewById(R.id.tanggal)
        tanggal2 = view.findViewById(R.id.tanggal2)
        rcvMember = view.findViewById(R.id.rcvlistmember)
        tambahmember = view.findViewById(R.id.tambah_member)
        textlogin = view.findViewById(R.id.textlogin)
        nama1 = view.findViewById(R.id.namamember1)
        alamat1 = view.findViewById(R.id.alamat1)
        lakilaki = view.findViewById(R.id.lakilaki1)
        perempuan = view.findViewById(R.id.perempuan1)
        tanggal1 = view.findViewById(R.id.tanggal1)

        memberAdapter = MemberAdapter(
            requireActivity(),
            arrayListOf()
        ) { dataMember: DataUser, position: Int, type: String ->
            Constant.USER_ID = dataMember.id!!.toLong()

            datamember = dataMember

            when (type) {

                "Delete" -> showDialogDelete(dataMember, position)
            }
        }

        rcvMember.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = memberAdapter
        }

        tambahmember.setOnClickListener {
            startActivity(Intent(requireActivity(), Create_memberActivity::class.java))
        }
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun onResult(responseUser: ResponseUser) {
        if (responseUser.data[0].role == "member"){
            nama1.text = responseUser.data[0].nama
            alamat1.text = responseUser.data[0].alamat
            tanggal1.text = responseUser.data[0].lahir

            if (responseUser.data[0].gender == "L") {
                lakilaki.visibility = View.VISIBLE
                perempuan.visibility = View.GONE
            } else {
                lakilaki.visibility = View.VISIBLE
                perempuan.visibility = View.GONE
            }
        }else if(responseUser.data[0].role == "admin"){

        }else{

        }
    }

    override fun onResultDelete(responseUser: ResponseUser) {
        showSuccessOk(responseUser.message[0])
    }

    override fun showDialogDelete(dataUser: DataUser, position: Int) {
        val dialog = AlertDialog.Builder(requireActivity())
        dialog.setTitle("Konfirmasi")
        dialog.setMessage("Hapus ${dataUser.nama}?")

        dialog.setPositiveButton("Hapus") { dialog, which ->
            presenter.deletemember(Constant.USER_ID)
            memberAdapter.removeuser(position)
            dialog.dismiss()
        }

        dialog.setNegativeButton("Batal") { dialog, which ->
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onResultList(responseUser: ResponseUser) {
        if (responseUser.data != null) {
            val datamember: List<DataUser> = responseUser.data
            memberAdapter.setData(datamember)
        } else {
            rcvMember.visibility = View.GONE
        }
    }

    override fun showSuccessOk(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismiss()
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