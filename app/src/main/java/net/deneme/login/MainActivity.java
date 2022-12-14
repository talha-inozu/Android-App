package net.deneme.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button lg1, ky2;
    EditText a1, a2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a1 = (EditText) findViewById(R.id.inputkname);
        a2 = (EditText) findViewById(R.id.inputps);
        lg1 = (Button) findViewById(R.id.login);
        ky2 = (Button) findViewById(R.id.kayıtsayfa);
        final Database db = new Database(getApplicationContext());
        List<Kullanicilar> usersList = null;
        usersList = db.users();


        if (!usersList.isEmpty()) {
            a1.setText(usersList.get(0).getKullaniciadi());
            if (db.findUser(usersList.get(0).getKullaniciadi()).getOturum() == 1) {
                a2.setText(usersList.get(0).getSifreler());
                ;
            } else {
                a2.setText("");
            }

        }


        Intent l = getIntent();
        String tkrr = l.getStringExtra("tekrar");
        if (tkrr != null) {
            a1.setText(tkrr);
        }


        lg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String control1 = a1.getText().toString();
                String control2 = a2.getText().toString();
                String control3sifre = db.searchPass(control1);

                if (control2.equals(control3sifre)) {
                    Intent k = new Intent(getApplicationContext(), MenuGUI.class);
                    k.putExtra("ka", a1.getText().toString());
                    if (!db.isOpenOturum(a1.getText().toString())) db.updateOturum(control1);
                    startActivity(k);

                } else {
                    Toast.makeText(getApplicationContext(), "Şifre yanlis", Toast.LENGTH_LONG).show();

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