package agloseries.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;



public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        String message = intent.getStringExtra("PESAN_MAIN_ACTIVITY");

        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}