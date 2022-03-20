package com.example.itungin_xirpl5.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ListView
import com.example.itungin_xirpl5.R
import com.example.itungin_xirpl5.Users
import com.example.itungin_xirpl5.adapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Users>
    lateinit var listView: ListView
    var listTransaksi = ArrayList<Users>()
    var adapter: adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        ref = FirebaseDatabase.getInstance().getReference("USERS")
        list = mutableListOf()
        listView = findViewById(R.id.lvTransaksi)

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    for (h in p0.children){
                        val transaksi = h.getValue(Users::class.java)
                        list.add(transaksi!!)
                    }

                    adapter = adapter(this@DashboardActivity, listTransaksi)
                    lvTransaksi.adapter = adapter
                }
            }
        })


        //intent

        val addTransaction : View = findViewById(R.id.buttonAdd)
        addTransaction.setOnClickListener { AddTransaction() }
    }

    private fun AddTransaction() {
        val intent = Intent(this, TransactionActivity::class.java)
        startActivity(intent)
    }
}