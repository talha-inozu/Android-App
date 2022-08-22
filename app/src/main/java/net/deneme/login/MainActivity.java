package net.deneme.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button lg1,ky2;
EditText a1,a2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a1 = (EditText) findViewById(R.id.inputkname);
        a2 = (EditText) findViewById(R.id.inputps);
        lg1 = (Button) findViewById(R.id.login);
        ky2 = (Button) findViewById(R.id.kayıtsayfa);

        Intent l = getIntent();
        String tkrr =  l.getStringExtra("tekrar");
        a1.setText(tkrr);
        final Database db = new Database(getApplicationContext());

        lg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String control1 = a1.getText().toString();
                String control2 = a2.getText().toString();
                String control3sifre = db.searchPass(control1);

                if (control2.equals(control3sifre)){
                    Intent k = new Intent(getApplicationContext(),AfterLogin.class);
                    k.putExtra("ka",a1.getText().toString());
                    startActivity(k);

                }
                else {
                    Toast.makeText(getApplicationContext(),"Şifre yanlis",Toast.LENGTH_LONG).show();

                }

            }
        });

                ky2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), Register.class);
                        startActivity(i);
                    }
                });

            }
        }