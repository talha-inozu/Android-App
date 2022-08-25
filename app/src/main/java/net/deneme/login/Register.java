package net.deneme.login;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    Button ky1;
    EditText ka1;
    EditText em1;
    EditText lc1;
    EditText sf1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ky1 = (Button) findViewById(R.id.register);
        ka1 = (EditText) findViewById(R.id.kullaniciadi);
        em1 = (EditText) findViewById(R.id.email);
        lc1 = (EditText) findViewById(R.id.lucknumber);
        sf1 = (EditText) findViewById(R.id.passawroder);


        ky1.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {
                String kaa = ka1.getText().toString();
                String emm = em1.getText().toString();
                Integer lcc= Integer.parseInt((lc1.getText().toString()));
                String sff = sf1.getText().toString();
                if(kaa.isEmpty() || emm.isEmpty() || sff.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Alanları boş geçmeyiniz!",Toast.LENGTH_LONG).show();
                    return;
                }

                Kullanicilar kullanici = new Kullanicilar(kaa,emm,lcc,sff);
                Database db = new Database(getApplicationContext());
                long id =  db.ekleKullanici(kullanici);

                if(id>0) {
                    Toast.makeText(getApplicationContext(),"Kayıt Başarılı !" + id,Toast.LENGTH_LONG).show();
                }
                String ara = db.searchKa(kaa);
                Intent i = new Intent(getApplicationContext(),AfterLogin.class);
                i.putExtra("ka",ara);
                startActivity(i);
            }
        });


    }
}