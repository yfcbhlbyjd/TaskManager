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

    fun setEditText(name: String?) {
        preference.edit().putString("text", name).apply()
    }

    fun getEditText(): String? {
        return preference.getString("text", "").toString()
    }

    fun setImageView(image: String?) {
        preference.edit().putString("image", image).apply()
    }

    fun getImageView(): String? {
        return preference.getString("image", "").toString()
    }
}