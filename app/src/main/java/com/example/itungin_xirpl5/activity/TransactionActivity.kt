package com.example.itungin_xirpl5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
//import androidx.core.content.ContentProviderCompat.requireContext
import com.example.itungin_xirpl5.R
//import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference

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

        ref = FirebaseDatabase.getInstance().getReference("USERS")
        save.setOnClickListener {
            savedata()

        }
    }

    private fun savedata() {
        val inputTitle = findViewById<EditText>(R.id.inputTitle)
        val inputDesc = findViewById<EditText>(R.id.inputDesc)
        val inputNominal = findViewById<EditText>(R.id.inputNominal)


        val Title = inputTitle.text.toString()
        val Desc = inputTitle.text.toString()
        val Nominal = inputTitle.text.toString()
        val user = Users(Title,Desc, Nominal)
        val userId = ref.push().key.toString()

        ref.child(userId).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()
            inputTitle.setText("")
            inputDesc.setText("")
            inputNominal.setText("")
        }
    }
}