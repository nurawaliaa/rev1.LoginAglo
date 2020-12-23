package AgloSeries.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class landing : AppCompatActivity(), View.OnClickListener {

    //val cekstock_Button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        //cekstock_Button = findViewById(R.id.cekstockButton)
        //cekstock_Button.setOnClickListener(this)

        val cekstok: Button = findViewById(R.id.cekstockButton)
        cekstok.setOnClickListener(this)

    }


    override fun onClick(v:View) {
        when (v.id){
            R.id.cekstockButton -> run {
                val Intentlogin = Intent(this@landing, cekStock::class.java)
                startActivity(Intentlogin)
            }
        }
    }


}
