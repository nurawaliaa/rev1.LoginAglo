package fiks.agloser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main_page.*


class mainPage : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)


        mAuth = FirebaseAuth.getInstance()

        buttonLogOut.setOnClickListener{
            mAuth.signOut()
            Intent(this@mainPage, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

        buttonAdd.setOnClickListener{
            val intent = Intent (this@mainPage, add::class.java)
            startActivity(intent)

        }

        buttonCekStock.setOnClickListener{
            val intent = Intent (this@mainPage, cekStock::class.java)
            startActivity(intent)

        }

    }
}