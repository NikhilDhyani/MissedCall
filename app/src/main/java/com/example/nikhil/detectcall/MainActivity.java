package com.example.nikhil.detectcall;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText sender, pass, receiverEmail;
    Button btn;
    SharedPreferences sharedpreferences;


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String senderE = "SenderEmail";
    public static final String Passw = "Password";
    public static final String receiverE = "ReceiverEmail";

    static String emailx;
    static String senderx;
    static String passx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sender = findViewById(R.id.SenderEmail);
        pass = findViewById(R.id.Email_Password);
        receiverEmail = findViewById(R.id.ReceiverEmail);
        btn = findViewById(R.id.Btn);


        //Code For Shared Preference
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(receiverE)) {
            receiverEmail.setText(sharedpreferences.getString(receiverE, ""));

            emailx = sharedpreferences.getString(receiverE, ""); // getting String
            Log.d("ReceiversEmail", emailx);
        }
        if (sharedpreferences.contains(senderE)) {
            sender.setText(sharedpreferences.getString(senderE, ""));
            senderx = sharedpreferences.getString(senderE, ""); // getting String


        }
        if (sharedpreferences.contains(Passw)) {
            pass.setText(sharedpreferences.getString(Passw, ""));

            passx = sharedpreferences.getString(Passw, ""); // getting String

        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String senderEmail = sender.getText().toString();
                String password = pass.getText().toString();
                String receiver = receiverEmail.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(senderE, senderEmail);
                editor.putString(Passw, password);
                editor.putString(receiverE, receiver);
                emailx = receiver;
                editor.commit();

                Toast.makeText(MainActivity.this, "Details Saved In Your Mobile", Toast.LENGTH_LONG).show();


            }
        });


    }

    public void sendEmail(String number) {

        String subject = "Missed Call Alert";
        String message = "Hey You have got a missed call from : "+number;
        Log.d("TestEmail", emailx);

        //Creating SendMail object
        SendMail sm = new SendMail(this, emailx, subject, message,senderx,passx);

        //Executing sendmail to send email
        sm.execute();
        Log.d("After Execute", "Executed");
    }
}
