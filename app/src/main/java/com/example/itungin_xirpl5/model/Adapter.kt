package com.example.itungin_xirpl5.model

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.itungin_xirpl5.R
import com.example.itungin_xirpl5.activity.DashboardActivity
import com.google.firebase.database.FirebaseDatabase
import kotlin.reflect.typeOf

class Adapter (val mCtx: Context, val layoutResId: Int, val list: List<Transaksi> )
    : ArrayAdapter<Transaksi>(mCtx,layoutResId,list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val type = view.findViewById<TextView>(R.id.drop_items)
        val title = view.findViewById<TextView>(R.id.titleIncome)
        val nominal = view.findViewById<TextView>(R.id.nomIncome)

        val update = view.findViewById<ImageButton>(R.id.updateButton)
        val delete = view.findViewById<ImageButton>(R.id.deleteButton)

        val transaction = list[position]

        title.text = transaction.title
        nominal.text = transaction.nominal

        update.setOnClickListener{
            showUpdateDialog(transaction)
        }

        delete.setOnClickListener {
            Deleteinfo(transaction)
        }

        return view
    }

    @SuppressLint("ResourceType")
    private fun showUpdateDialog(transaction: Transaksi) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.update, null)

        val typeT = view.findViewById<TextView>(R.id.drop_items)
        val titleT = view.findViewById<TextView>(R.id.inputTitle)
        val nominalT = view.findViewById<TextView>(R.id.nomIncome)

        typeT.setText(transaction.type)
        titleT.setText(transaction.title)
        nominalT.setText(transaction.nominal)

        builder.setView(view)

        builder.setPositiveButton("Update") {dialog, which ->

            val dbTransaksi = FirebaseDatabase.getInstance().getReference("Transaction")

            val dropType = typeT.text.toString().trim()
            val textTitle = titleT.text.toString().trim()
            val textNominal = nominalT.text.toString().trim()

            if (dropType.isEmpty()) {
                typeT.error = "please enter type"
                typeT.requestFocus()
                return@setPositiveButton
            }

            if (textTitle.isEmpty()){
                titleT.error = "please enter title"
                titleT.requestFocus()
                return@setPositiveButton
            }

            if (textNominal.isEmpty()){
                nominalT.error = "please enter title"
                nominalT.requestFocus()
                return@setPositiveButton
            }

            val transaksi = Transaksi(transaction. id, dropType, textTitle, textNominal)
            
            dbTransaksi.child(transaksi.id).setValue(transaksi).addOnCompleteListener {
                Toast.makeText(mCtx, "Updated", Toast.LENGTH_SHORT)
            }
        }
        
        builder.setNegativeButton("No") {dialog, which ->
            
        }
        
        val alert = builder.create()
        alert.show()
    }

    private fun Deleteinfo(transaction: Transaksi) {
        val progressDialog = ProgressDialog(context, com.google.android.material.R.style.Theme_MaterialComponents_Light_Dialog)

        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting...")
        progressDialog.show()
        
        val dbDatabase = FirebaseDatabase.getInstance().getReference("Transaction")
        dbDatabase.child(transaction.id).removeValue()
        Toast.makeText(mCtx, "Deleted", Toast.LENGTH_SHORT).show()

        val intent = Intent(context, DashboardActivity::class.java)
        context.startActivity(intent)
    }
}
