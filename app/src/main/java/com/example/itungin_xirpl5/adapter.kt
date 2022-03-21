package com.example.itungin_xirpl5

import android.content.Context
import android.content.LocusId
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.listview_income.view.*
import kotlinx.android.synthetic.main.listview_spending.view.*
import org.w3c.dom.Text
import java.text.FieldPosition

class adapter : BaseAdapter {
    var listTransaksi = ArrayList<Users>()
    var context: Context? = null

    constructor(context: Context, listOfTransaksi: ArrayList<Users>):super(){
        this.listTransaksi = listOfTransaksi
        this.context = context
    }

    override fun getCount(): Int {
        return listTransaksi.size
    }

    override fun getItem(p0: Int): Any {
        return listTransaksi[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val transaksi = listTransaksi[p0]
        if (transaksi.type == "Income") {
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var View = inflator.inflate(R.layout.listview_income, null)
            View.titleIncome.text = transaksi.title!!
            View.nomIncome.text = transaksi.nominal!!

            return View
        }else{
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var View = inflator.inflate(R.layout.listview_spending, null)
            View.titleSpending.text = transaksi.title!!
            View.nomSpending.text = transaksi.nominal!!

            return View
        }
    }
}