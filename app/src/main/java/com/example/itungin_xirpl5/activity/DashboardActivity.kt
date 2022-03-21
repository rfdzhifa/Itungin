package com.example.itungin_xirpl5.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.itungin_xirpl5.R
import com.google.android.material.textfield.TextInputLayout

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val addTransaction : View = findViewById(R.id.buttonAdd)
        addTransaction.setOnClickListener { AddTransaction() }
    }

    private fun AddTransaction() {
        val intent = Intent(this, TransactionActivity::class.java)
        startActivity(intent)
    }
}