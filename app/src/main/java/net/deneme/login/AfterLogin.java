package net.deneme.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AfterLogin extends AppCompatActivity {
    List<String> probabilites = new ArrayList<String>();
    Button ex1;
    TextView ki1;
    Button addButton;
    TextView addProbablity;
    ListView changeList;
    Button random;
    TextView last;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        ki1 = (TextView) findViewById(R.id.ki);
        ex1 = (Button) findViewById(R.id.exit);
        addButton = (Button) findViewById(R.id.addList);
        addProbablity = (TextView) findViewById(R.id.addProbability);
        changeList = (ListView) findViewById(R.id.probList);
        random = (Button) findViewById(R.id.executeRand);
        last = (TextView) findViewById(R.id.randedChoice);
        Intent k = getIntent();
        ki1.setText(k.getStringExtra("ka"));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, probabilites);
        changeList.setAdapter(arrayAdapter);

        ex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("tekrar",ki1.getText());
                startActivity(i);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                probabilites.add(addProbablity.getText().toString());
                arrayAdapter.notifyDataSetChanged();
            }
        });

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Random rand = new Random();
            int randint = rand.nextInt(probabilites.size()-1);
            last.setText(probabilites.get(randint));
            }
        });



    }
}