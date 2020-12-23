package fiks.agloser

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.buttonSignUp
import kotlinx.android.synthetic.main.activity_main.emailRg
import kotlinx.android.synthetic.main.activity_main.passwordRg



class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

        mAuth  = FirebaseAuth.getInstance()

        buttonLogin.setOnClickListener{
            val email   = emailRg.text.toString().trim()
            val password = passwordRg.text.toString().trim()

            if (email.isEmpty()){
                emailRg.error = " Email harus diisi!"
                emailRg.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emailRg.error = " Email Tidak Valid!"
                emailRg.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6){
                passwordRg.error = "Password harus lebih dari 6 karakter"
                passwordRg.requestFocus()
                return@setOnClickListener
            }
            loginUser(email,password)
        }



        buttonSignUp.setOnClickListener{
            val intent = Intent(this@MainActivity, register::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
    mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this){
            if (it.isSuccessful){
                Intent(this@MainActivity, mainPage::class.java).also {intent->
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            } else{
                Toast.makeText(this,"${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
 //mAuth.currentUser jika user sudah login, maka akan di direct ke halaman main page. tidak perlu login lagi
    override fun onStart() {
        super.onStart()
        if (mAuth.currentUser !=null){
            Intent(this@MainActivity, mainPage::class.java).also {intent->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

        }
    }

}