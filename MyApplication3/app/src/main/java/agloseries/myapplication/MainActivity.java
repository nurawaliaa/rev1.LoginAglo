package agloseries.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
        }

        public void sendMessage(View view) {
                Intent intent = new Intent(this, MainActivity2.class);
                EditText editText = (EditText) findViewById(R.id.editText);
                String message =  editText.getText().toString();
                intent.putExtra("PESAN_MAIN_ACTIVITY",message);
                startActivity(intent);

        }
}
