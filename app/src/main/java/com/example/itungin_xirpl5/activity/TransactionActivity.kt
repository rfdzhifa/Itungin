package com.example.itungin_xirpl5.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.itungin_xirpl5.R
import com.example.itungin_xirpl5.Users
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_transaction.*
import kotlinx.android.synthetic.main.listview_income.*

class TransactionActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        ref = FirebaseDatabase.getInstance().getReference("USERS")

        btn_save.setOnClickListener {
            savedata()
            val intent = Intent (this, DashboardActivity::class.java)
            startActivity(intent)
        }

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

    private fun savedata() {
        val type = drop_items.text.toString()
        val title = inputTitle.text.toString()
        val nominal = inputNominal.text.toString()
        val id = ref.push().key.toString()
        val transaction = Users(id, type, title, nominal)

        ref.child(id).setValue(transaction).addOnCanceledListener {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            drop_items.setText("")
            inputTitle.setText("")
            inputNominal.setText("")
        }
    }
}