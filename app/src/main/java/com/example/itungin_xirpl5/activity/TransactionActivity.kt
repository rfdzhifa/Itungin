package com.example.itungin_xirpl5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.itungin_xirpl5.R
import com.google.android.material.textfield.TextInputLayout

class TransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val transaction = resources.getStringArray(R.array.Transaction)
        val arrayAdapter = ArrayAdapter(this, R.layout.list_item, transaction)
        val dropitem = findViewById<AutoCompleteTextView>(R.id.drop_items)
        dropitem.setAdapter(arrayAdapter)
    }
}