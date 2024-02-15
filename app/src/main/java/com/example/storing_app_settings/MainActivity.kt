package com.example.storing_app_settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Switch

class MainActivity : AppCompatActivity() {

    val PREF_NAME = "perfs"
    val PREF_DARK_THEME = "dark_theme"

    var my_file = "MySharedFile"
    var my_file2 = "MySharedFile2"
    lateinit var saved_var: EditText
    lateinit var saved_var2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        val SP = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val useDarkTheme = SP.getBoolean(PREF_DARK_THEME, false)
        if(useDarkTheme) {
            setTheme(com.google.android.material.R.style.ThemeOverlay_AppCompat_Dark)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toggle = findViewById<Switch>(R.id.switch1)
        toggle.isChecked = useDarkTheme
        toggle.setOnCheckedChangeListener{
            view, isChecked -> toggleTheme(isChecked)
        }
        saved_var = findViewById(R.id.idStuff1)
        saved_var2 = findViewById(R.id.idStuff2)
    }

    private fun toggleTheme(darkTheme: Boolean) {
        val editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit()

        editor.apply{
            putBoolean(PREF_DARK_THEME, darkTheme)
            apply()
        }

        val intent = intent
        finish()
        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()
        val SP = getSharedPreferences(my_file, MODE_PRIVATE)
        val SP2 = getSharedPreferences(my_file2, MODE_PRIVATE)
        val key1 = SP.getString("name", "")
        val key2 = SP2.getString("username", "")


        saved_var.setText(key1)
        saved_var2.setText(key2)
    }

    override fun onPause() {
        super.onPause()
        val SP = getSharedPreferences(my_file, MODE_PRIVATE)
        val SP2 = getSharedPreferences(my_file2, MODE_PRIVATE)

        val myEdit = SP.edit()
        val myEdit2 = SP2.edit()
        myEdit.putString("name", saved_var!!.text.toString())
        myEdit2.putString("username", saved_var2!!.text.toString())

        myEdit.commit()
        myEdit2.commit()
    }
}