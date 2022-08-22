package net.deneme.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AfterLogin extends AppCompatActivity {
    Button ex1;
    TextView ki1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        ki1 = (TextView) findViewById(R.id.ki);
        ex1 = (Button) findViewById(R.id.exit);

        Intent k = getIntent();

        final String ara = k.getStringExtra("ka");
        ki1.setText(ara);

        ex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("tekrar",ara);
                startActivity(i);
            }
        });

    }
}