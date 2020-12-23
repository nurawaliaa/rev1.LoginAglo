package fiks.agloser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_data__page.*
import kotlinx.android.synthetic.main.activity_main.*

class addData_Page : AppCompatActivity(), View.OnClickListener {

    private lateinit var etNama: EditText
    private lateinit var etJumlah: EditText
    private lateinit var etPrices: EditText
   //private lateinit var etId: EditText
    private lateinit var btnSave: ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data__page)

        etNama = findViewById(R.id.addName)
        etJumlah = findViewById(R.id.addAmount)
        etPrices = findViewById(R.id.addPrices)
        //etId = findViewById(R.id.addId)
        btnSave = findViewById(R.id.buttonSave)

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
        val ref = FirebaseDatabase.getInstance().getReference("ItemName")

        val itemNameId = ref.push().key
        val iname = ItemName(itemNameId,nama,jumlah,price)

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