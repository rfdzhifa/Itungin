package com.example.itungin_xirpl5.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ListView
import com.example.itungin_xirpl5.R
import com.example.itungin_xirpl5.model.Adapter
import com.example.itungin_xirpl5.model.Transaksi
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.*

class DashboardActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Transaksi>
    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


        ref = FirebaseDatabase.getInstance().getReference("Transaction")
        list = mutableListOf()
        listView = findViewById(R.id.lvTransaksi)

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    list.clear()
                    for (h in p0.children){
                        val transaksi = h.getValue(Transaksi::class.java)
                        list.add(transaksi!!)
                    }

                    val adapter = Adapter(this@DashboardActivity, R.layout.listview_income, list)
                    listView.adapter = adapter
                }
            }
        })

        val addTransaction : View = findViewById(R.id.buttonAdd)
        addTransaction.setOnClickListener { addTransaction() }
    }

    private fun addTransaction() {
        val intent = Intent(this, TransactionActivity::class.java)
        startActivity(intent)
    }
}