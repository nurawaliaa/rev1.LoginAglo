package fiks.agloser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_data__page.*
import kotlinx.android.synthetic.main.activity_main.*


class addData_Page : AppCompatActivity(), View.OnClickListener {

    private lateinit var etNama: EditText
    private lateinit var etJumlah: EditText
    private lateinit var etPrices: EditText
    //private lateinit var etId: EditText
    private lateinit var btnSave: ImageButton
    //private lateinit var item : ListView
    private lateinit var  ref : DatabaseReference
    //private lateinit var stockList : MutableList<itemAdd>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data__page)

        ref = FirebaseDatabase.getInstance().getReference("ItemName")

        etNama = findViewById(R.id.addName)
        etJumlah = findViewById(R.id.addAmount)
        etPrices = findViewById(R.id.addPrices)
        btnSave = findViewById(R.id.buttonSave)
        //item = findViewById(R.id.lv_item)
        //etId = findViewById(R.id.addId)


        //stockList = mutableListOf()

       /*ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(h in snapshot.children ){
                        val ItemName: cekStock?  = h.getValue(cekStock::class.java)
                        if (ItemName != null) {
                            stockList.add(ItemName)
                        }

                    }
                    val adapter = cekStockAdapter(applicationContext, R.layout.item_stock, stockList)
                    item.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })*/

        buttonCancel.setOnClickListener{
            val intent = Intent (this@addData_Page, add::class.java)
            startActivity(intent)
        }
        btnSave.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        saveData()
    }
    private fun  saveData(){
        //menangkap inputan
        //val id = etId.text.toString().trim()
        val nama = etNama.text.toString().trim()
        val jumlah = etJumlah.text.toString().trim()
        val price = etPrices.text.toString().trim()

        //kondisi
        /*if (id.isEmpty()){
            etId.error = " Please Enter ID!"
            etId.requestFocus()
            return
        }
        if (id.toPattern().matcher(id).matches()){
            etId.error = " ID sudah dipakai/tidak valid!"
            etId.requestFocus()
            return
        }

         */

        if (nama.isEmpty()){
            etNama.error = "Please enter a name!"
            etNama.requestFocus()
            return
        }

        if (jumlah.isEmpty()){
            etJumlah.error = "Please set the amount!"
            etJumlah.requestFocus()
            return
        }
        if (price.isEmpty()){
            etPrices.error = "Please set the Price!"
            etPrices.requestFocus()
            return
        }


        val itemNameId = ref.push().key
        val iname = itemAdd(itemNameId!!,nama,jumlah,price)

        if (itemNameId != null){
            ref.child(itemNameId).setValue(iname).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(applicationContext, "Data added successfully", Toast.LENGTH_SHORT).show()
                    Intent(this@addData_Page, addData_Page::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }
                else {
                    Toast.makeText(this,it.exception?.message, Toast.LENGTH_SHORT).show()

                }

            }
        }
    }


}


