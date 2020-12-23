package AgloSeries.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.activity_main.*

/*class MainActivity : AppCompatActivity() {//, View.OnClickListener {

    //var login_Button : Button = findViewyId(R.Id.Button)
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.loginButton)
        button.setOnClickListener{
            startActivity(Intent(this, landing::class.java))
        }

        //val btnLogin: Button = findViewById(R.id.loginButton)

    }
    /*override fun onClick(v:View) {
        when (v.id){
            R.id.loginButton -> run {
                val Intentlogin = Intent(this@MainActivity, landing::class.java)
                startActivity(Intentlogin)
            }
        }
    }

     */


}

 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    //var login_Button : Button = findViewyId(R.Id.Button)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //login_Button = findViewById(R.id.loginButton)
        //login_Button.setOnClickListener(this)

        //CEK ID LOGIN BUTTONNYA BENER BELOM
        val btnLogin: Button = findViewById(R.id.loginButton)
    }
    override fun onClick(v:View?) {
        when (v?.id){
            R.id.loginButton ->  {
                val Intentlogin = Intent(this@MainActivity, landing::class.java)
                startActivity(Intentlogin)
            }
        }
    }
}