package com.coolscrap.hyderabadcustoms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button b1;
    String fName, cname, obj, rating;
    String date;
    ImageButton ib;
    EditText name;
    EditText flno;
    EditText pass;
    EditText comments;
    ImageButton angry;
    ImageButton satisfied;
    ImageButton happy;
    ImageButton toDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner mr = (Spinner) findViewById(R.id.mister);
        final Spinner country = (Spinner) findViewById(R.id.country);
        new SpannableString("HAPPY TO HELP YOU");
        name = (EditText) findViewById(R.id.editText1);
        flno = (EditText) findViewById(R.id.editText2);
        pass = (EditText) findViewById(R.id.editText3);
        comments = (EditText) findViewById(R.id.editText6);
        angry = (ImageButton) findViewById(R.id.angry);
        satisfied = (ImageButton) findViewById(R.id.satis);
        happy = (ImageButton) findViewById(R.id.happy);
        toDb = (ImageButton) findViewById(R.id.toDb);
        b1 = (Button) findViewById(R.id.submit);

        if (country != null) {
            country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    cname = adapterView.getItemAtPosition(i).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

        toDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, login.class);
                startActivity(i);
            }
        });
        happy.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view) {
                rating = "HAPPY";
                angry.setImageResource(R.drawable.s3);
                happy.setImageResource(R.drawable.hapfaded);
                satisfied.setImageResource(R.drawable.n2);
                Toast.makeText(MainActivity.this, rating, Toast.LENGTH_SHORT).show();
            }

        });

        satisfied.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view) {
                rating = "NEUTRAL";
                angry.setImageResource(R.drawable.s3);
                happy.setImageResource(R.drawable.hap);
                satisfied.setImageResource(R.drawable.n2faded);
                Toast.makeText(MainActivity.this, rating, Toast.LENGTH_SHORT).show();
            }

        });

        angry.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View view) {
                rating = "SAD";
                satisfied.setImageResource(R.drawable.n2);
                happy.setImageResource(R.drawable.hap);
                angry.setImageResource(R.drawable.s3faded);
                Toast.makeText(MainActivity.this, rating, Toast.LENGTH_SHORT).show();
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nme = name.getText().toString().toUpperCase();
                String fno = flno.getText().toString().toUpperCase();
                String passport = pass.getText().toString().toUpperCase();
                String comment = comments.getText().toString().toUpperCase();
                date = DateFormat.getDateTimeInstance().format(new Date());
                if(TextUtils.isEmpty(nme) || TextUtils.isEmpty(fno) || TextUtils.isEmpty(rating)) {
                    Toast.makeText(MainActivity.this, "Please fill the required fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    fName = (new StringBuilder()).append(nme).toString();
                    Toast.makeText(MainActivity.this, (new StringBuilder()).append("Hello ").append(fName).append("\n").append("From ").append(cname).append("\nFight ").append(((String) (obj))).append("\nHolding ").append(passport).append("\nYou were ").append(rating).append("\n").toString(), Toast.LENGTH_LONG).show();
                    FeedbackDatabase o;
                    o = new FeedbackDatabase(MainActivity.this);
                    o.open();
                    o.createEntry(fName, fno, cname, passport, rating, comment, date);
                    o.close();
                    name.setText("");
                    flno.setText("");
                    pass.setText("");
                    comments.setText("");
                    Intent i = new Intent(MainActivity.this, Thankyou.class);
                    startActivity(i);
                }
            }

        });
    }
}
