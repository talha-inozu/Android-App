package net.deneme.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuGUI extends AppCompatActivity {
    Button btnDestiny, btnPdfReader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        btnDestiny = findViewById(R.id.destiny);
        btnPdfReader = findViewById(R.id.pdfreader);
        Intent getterIntent = getIntent();
        btnDestiny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(getApplicationContext(), AfterLogin.class);
                k.putExtra("ka", getterIntent.getStringExtra("ka"));
                startActivity(k);
            }
        });

        btnPdfReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(getApplicationContext(), PDFReader.class);
                k.putExtra("ka", getterIntent.getStringExtra("ka"));
                startActivity(k);
            }
        });

    }

}
