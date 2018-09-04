package com.example.nikhil.detectcall;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.nikhil.detectcall.MainActivity.AccessPin;
import static com.example.nikhil.detectcall.MainActivity.MyPREFERENCES;
import static com.example.nikhil.detectcall.MainActivity.Passw;
import static com.example.nikhil.detectcall.MainActivity.emailx;
import static com.example.nikhil.detectcall.MainActivity.passx;
import static com.example.nikhil.detectcall.MainActivity.receiverE;

public class VerifyPin extends AppCompatActivity {

EditText vpinField;
Button vbutton;

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_pin);

        vpinField = findViewById(R.id.Vpin);
        vbutton = findViewById(R.id.vbtn);


         sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(AccessPin)) {
            Toast.makeText(getApplicationContext(),"You have a password",Toast.LENGTH_LONG).show();
            Log.d("VPassx",AccessPin);

        }
        else
            {
                Toast.makeText(getApplicationContext(),"You dont have a password",Toast.LENGTH_LONG).show();
                Intent i = new Intent(VerifyPin.this,MainActivity.class);
                startActivity(i);



            }



        vbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String pin = vpinField.getText().toString();

                if (pin.equals(sharedpreferences.getString(AccessPin,"")))
                {
                    Intent i = new Intent(VerifyPin.this,MainActivity.class);
                    startActivity(i);

                    Toast.makeText(getApplicationContext(),"Correct Pin",Toast.LENGTH_LONG).show();


                }
                else
                    {
                        Toast.makeText(getApplicationContext(),"Incorrect Pin",Toast.LENGTH_LONG).show();

                    }
            }
        });
    }

}
