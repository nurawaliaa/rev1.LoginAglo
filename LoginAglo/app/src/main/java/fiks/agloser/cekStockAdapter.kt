package fiks.agloser

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_data__page.*
import kotlinx.android.synthetic.main.item_stock.*


class cekStockAdapter(val mCtx: Context, val LayoutResId : Int, val stockList :List<cekStock>):ArrayAdapter<cekStock>(mCtx, LayoutResId, stockList ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater :LayoutInflater = LayoutInflater.from(mCtx)
        val view : View = layoutInflater.inflate(LayoutResId, null)

        val  tvNama : TextView = view.findViewById(R.id.addName)
        val tvAmount : TextView = view.findViewById(R.id.addAmount)
        val tvPrices : TextView = view.findViewById(R.id.addPrices)
        val ItemName = stockList [position]

        tvNama.text = ItemName.nama
        tvAmount.text = ItemName.jumlah
        tvPrices.text  = ItemName.prices

        return view
    }
}