package fiks.agloser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*

class add : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        addnewData.setOnClickListener{
            val intent = Intent (this@add, addData_Page::class.java)
            startActivity(intent)

        }
    }
}