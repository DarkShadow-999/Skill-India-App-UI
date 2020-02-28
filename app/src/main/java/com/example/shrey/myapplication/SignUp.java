package com.example.shrey.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);

        final Button button1 = (Button) findViewById(R.id.button13);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUp.this, student_form2.class);
                SignUp.this.startActivity(myIntent);
            }
        });

        final Button button2 = (Button) findViewById(R.id.button14);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUp.this, training_partner.class);
                SignUp.this.startActivity(myIntent);
            }
        });

        final Button button3 = (Button) findViewById(R.id.button15);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        final Button button4 = (Button) findViewById(R.id.button16);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(SignUp.this, Login.class);
                SignUp.this.startActivity(myIntent);
            }
        });
    }

}
