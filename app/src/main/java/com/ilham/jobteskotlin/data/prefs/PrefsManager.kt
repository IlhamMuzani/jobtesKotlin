package com.ilham.jobteskotlin.data.prefs

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.stringPref

class PrefsManager(context: Context) : Krate {

    private val PREFS_IS_LOGIN: String = "prefs_is_login"
    private val PREFS_ID: String = "prefs_id"
    private val PREFS_IS_NAMA: String = "prefs_is_nama"
    private val PREFS_IS_LAHIR: String = "prefs_is_lahir"
    private val PREFS_IS_ALAMAT: String = "prefs_is_alamat"
    private val PREFS_IS_GENDER: String = "prefs_is_gender"
    private val PREFS_IS_USERNAME: String = "prefs_is_username"
    private val PREFS_IS_PASSWORD: String = "prefs_is_password"
    private val PREFS_IS_PASSWORD_CONFIRMATION: String = "prefs_is_password_confirmation"
    private val PREFS_IS_ROLE: String = "prefs_is_role"


    override val sharedPreferences: SharedPreferences

    init {
        sharedPreferences =context.applicationContext.getSharedPreferences(
            "Jobtes",Context.MODE_PRIVATE
        )
    }

    var prefIsLogin by booleanPref(PREFS_IS_LOGIN, false)
    var prefsId by stringPref(PREFS_ID, "")
    var prefs_is_nama by stringPref(PREFS_IS_NAMA, "")
    var prefs_is_lahir by stringPref(PREFS_IS_LAHIR, "")
    var prefs_is_alamat by stringPref(PREFS_IS_ALAMAT, "")
    var prefs_is_gender by stringPref(PREFS_IS_GENDER, "")
    var prefs_is_username by stringPref(PREFS_IS_USERNAME, "")
    var prefs_is_password by stringPref(PREFS_IS_PASSWORD, "")
    var prefs_is_password_confirmation by stringPref(PREFS_IS_PASSWORD_CONFIRMATION, "")
    var prefs_is_role by stringPref(PREFS_IS_ROLE, "")

    fun logout(){
        sharedPreferences.edit().clear().apply()
    }
}