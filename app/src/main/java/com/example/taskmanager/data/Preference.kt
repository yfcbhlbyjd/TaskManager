package com.example.taskmanager.data

import android.content.Context


class Preference(context: Context) {
    private val preference = context.getSharedPreferences("data", Context.MODE_PRIVATE)

    fun showBoarding() {
        preference.edit().putBoolean("isBoardShown", false).apply()
    }

    fun isBoardShown(): Boolean {
        return preference.getBoolean("isBoardShown", true)
    }

    fun setEditText(name: String) {
        preference.edit().putString("text", name).apply()
    }

    fun getEditText(): String {
        return preference.getString("text", "").toString()
    }

    fun setImageView(url: String) {
        preference.edit().putString("image", url).apply()
    }

    fun getImageView(): String {
        return preference.getString("image", "").toString()
    }

    fun setProfileAge(age: String) {
        preference.edit().putString("age", age).apply()
    }

    fun getProfileAge(): String? = preference.getString("age", "").toString()

}
