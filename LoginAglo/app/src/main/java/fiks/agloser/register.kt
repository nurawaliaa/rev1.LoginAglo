package fiks.agloser

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.buttonSignUp
import kotlinx.android.synthetic.main.activity_main.emailRg
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern


class register : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance();

        buttonSignUp.setOnClickListener{

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

            registerUser(email, password)
        }

        buttonSignIn.setOnClickListener{
            //intent to login page
            val intent = Intent(this@register, MainActivity::class.java).also {
                startActivity(it)
            }

        }
    }

    private fun registerUser(email: String, password: String){

            mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Intent(this@register, MainActivity::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                        }
                    }
                    else {
                        Toast.makeText(this,it.exception?.message, Toast.LENGTH_SHORT).show()

                    }
                }
    }

    override fun onStart() {
        super.onStart()
        if (mAuth.currentUser != null){
            Intent(this@register, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}





