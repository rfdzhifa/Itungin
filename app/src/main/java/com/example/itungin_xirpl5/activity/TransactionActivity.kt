package com.example.itungin_xirpl5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import com.example.itungin_xirpl5.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

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


        val title = inputTitle.text.toString()
        val desc = inputDesc.text.toString()
        val nominal = inputNominal.text.toString()

        val user = Users(title,desc, nominal)
        val userId = ref.push().key.toString()

        ref.child(userId).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()

        }
    }
}
