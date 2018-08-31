package com.example.nikhil.detectcall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendEmail()
    {
        //Getting content for email
        String email = "Where-You-Want-To-Receive-Mail";
        String subject = "Missed Call Alert";
        String message = "Hey You have got a missed call";

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
        Log.d("After Execute","Executed");
    }
}
