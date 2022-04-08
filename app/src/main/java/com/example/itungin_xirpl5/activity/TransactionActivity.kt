package com.example.itungin_xirpl5.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.itungin_xirpl5.R
import com.example.itungin_xirpl5.model.Transaksi
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val transaction_drop = resources.getStringArray(R.array.Transaction)
        val arrayAdapter = ArrayAdapter(this, R.layout.list_item, transaction_drop)
        val dropitem = findViewById<AutoCompleteTextView>(R.id.drop_items)
        dropitem.setAdapter(arrayAdapter)

        ref = FirebaseDatabase.getInstance().getReference("Transaction")

        btn_save.setOnClickListener{
            saveData()
            val intent = Intent (this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveData() {
        val transaksi = drop_items.text.toString()
        val title = inputTitle.text.toString()
        val nominal = inputNominal.text.toString()

        val transaksiId = ref.push().key.toString()
        val transaction = Transaksi(transaksiId, transaksi, title, nominal)

        ref.child(transaksiId).setValue(transaction).addOnCompleteListener {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
            drop_items.setText("")
            inputTitle.setText("")
            inputNominal.setText("")
        }
    }
}