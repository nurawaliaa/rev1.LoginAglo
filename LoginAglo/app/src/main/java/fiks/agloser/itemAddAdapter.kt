package fiks.agloser

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase


class itemAddAdapter(
    val mCtx: Context,
    val LayoutResId: Int,
    val stockList: List<itemAdd>
):ArrayAdapter<itemAdd>(mCtx, LayoutResId, stockList ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater :LayoutInflater = LayoutInflater.from(mCtx)
        val view : View = layoutInflater.inflate(LayoutResId, null)

        val tvAmount : TextView = view.findViewById(R.id.tv_amount)
        val  tvNama : TextView = view.findViewById(R.id.tv_Nama)
        val tvPrices : TextView = view.findViewById(R.id.tv_prices)
        val tvEdit : TextView = view.findViewById(R.id.tv_edit)

        val ItemName = stockList [position]

        tvEdit.setOnClickListener{
            showUpdateDialog(ItemName)
        }

        tvAmount.text = ItemName.jumlah
        tvNama.text = ItemName.nama
        tvPrices.text  = ItemName.price

        return view
    }
    fun  showUpdateDialog(ItemName: itemAdd) {
        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Edit Data")

        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.update_dialog, null)

        val add_Nama = view.findViewById<EditText>(R.id.addName2)
        val add_Amount = view.findViewById<EditText>(R.id.addAmount2)
        val add_Prices = view.findViewById<EditText>(R.id.addPrices2)

        //menangkap data di database
        add_Nama.setText(ItemName.nama)
        add_Amount.setText(ItemName.jumlah)
        add_Prices.setText(ItemName.price)

        val dbItem = FirebaseDatabase.getInstance().getReference("ItemName")

        builder.setView(view)

        //membuat button update dan No
        builder.setPositiveButton("Update"){p0,p1 ->

            //menangkap nama yang akan diinput agar masuk ke database
            val  nama = add_Nama.text.toString().trim()
            val jumlah = add_Amount.text.toString().trim()
            val price = add_Prices.text.toString().trim()

            if (nama.isEmpty()){
                add_Nama.error = "Name Can not Empty!!"
                add_Nama.requestFocus()
                return@setPositiveButton
            }

            if (jumlah.isEmpty()){
                add_Amount.error = "Amount Can not Empty!!"
                add_Amount.requestFocus()
                return@setPositiveButton
            }

            if (price.isEmpty()){
                add_Prices.error = "Price Can not Empty!!"
                add_Prices.requestFocus()
                return@setPositiveButton
            }

            val ItemName = itemAdd(ItemName.id, nama, jumlah, price)
            dbItem.child(ItemName.id!!).setValue(ItemName)

            //after data succesfully added
            Toast.makeText(mCtx, "Data Updated Successfully", Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("DELETE"){p0,p1 ->
            //ref ke database. menggunakan child dengan ItemName.id agar data yang terhapus adalah id yg dipih
            val dbItem = FirebaseDatabase.getInstance().getReference("ItemName").child(ItemName.id)

            dbItem.removeValue()
            Toast.makeText(mCtx, "Data Deleted Successfully", Toast.LENGTH_SHORT).show()
        }
        builder.setNeutralButton("No"){p0,p1 ->
        }

        //show update dialog
        val alert  = builder.create()
        alert.show()

    }
}



