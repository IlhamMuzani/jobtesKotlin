package com.ilham.jobteskotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilham.jobteskotlin.R
import com.ilham.jobteskotlin.ui.admin.login_admin.LoginadminActivity
import com.ilham.jobteskotlin.ui.member.login.LoginActivity
import kotlinx.android.synthetic.main.activity_masuk.*

class MasukActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)
        fungsi()
    }

    fun fungsi() {
        btn_member.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        btn_admin.setOnClickListener {
            startActivity(Intent(this, LoginadminActivity::class.java))
        }
    }
}