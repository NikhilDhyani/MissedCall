package com.example.nikhil.detectcall;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {

    EditText sender, pass, receiverEmail,pin;
    TextView textView;
    Button btn;
<<<<<<< HEAD
    public SharedPreferences sharedpreferences ;//= getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

//    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



    String xo = "nikhildhyani365@gmail.com";
=======

    // Shared Preference
    public  static  SharedPreferences sharedpreferences;
>>>>>>> pin-enter


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String senderE = "SenderEmail";
    public static final String Passw = "Password";
    public static final String receiverE = "ReceiverEmail";
    public static final String AccessPin = "MasterPin";

    public static String emailx;
    public static String senderx;
    public static String passx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sender = findViewById(R.id.SenderEmail);
        pass = findViewById(R.id.Email_Password);
        receiverEmail = findViewById(R.id.ReceiverEmail);
        btn = findViewById(R.id.Btn);
        pin = findViewById(R.id.Mpin);


        textView = findViewById(R.id.tv);


        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_PHONE_STATE)
                .withListener(new PermissionListener() {

                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        /* On Permission Granted */

                        sender.setEnabled(true);
                        pass.setEnabled(true);
                        receiverEmail.setEnabled(true);
                        btn.setEnabled(true);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response)
                    {
                        /*  Make Input Field Inaccessible*/
                        sender.setEnabled(false);
                        pass.setEnabled(false);
                        receiverEmail.setEnabled(false);
                        btn.setEnabled(false);

                        textView.setText(" Please Grant Permission.\nEither Uninstall or Grant Permission From Settings");


                    }


                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token)
                    {

                        Toast.makeText(getApplicationContext(),"Need Read Phone State Permission To Detect Call",Toast.LENGTH_LONG).show();
                        textView.setText(" Please Grant Permission.\nEither Uninstall or Grant Permission From Settings");

                        sender.setEnabled(false);
                        pass.setEnabled(false);
                        receiverEmail.setEnabled(false);
                        btn.setEnabled(false);


                    }
                }).check();


        //Code For Shared Preference
       sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Log.d("BossCreate","Checki");
        if (sharedpreferences.contains(receiverE)) {
            receiverEmail.setText(sharedpreferences.getString(receiverE, ""));

<<<<<<< HEAD
=======
            // Getting Receiver Email Address From Shared Preference

>>>>>>> pin-enter
            emailx = sharedpreferences.getString(receiverE, "");
            Log.d("ReceiversEmail", emailx);
        }
        if (sharedpreferences.contains(senderE)) {
            sender.setText(sharedpreferences.getString(senderE, ""));

            // Getting Sender Email Address From Shared Preference
            senderx = sharedpreferences.getString(senderE, "");

        }
        if (sharedpreferences.contains(Passw)) {
            pass.setText(sharedpreferences.getString(Passw, ""));

            // Getting Password From Shared Preference

            passx = sharedpreferences.getString(Passw, "");
        }
        if (sharedpreferences.contains(AccessPin)) {
            pin.setText(sharedpreferences.getString(AccessPin, ""));

            // Getting Access-Pin From Shared Preference

            passx = sharedpreferences.getString(Passw, "");

        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String senderEmail = sender.getText().toString();
                String password = pass.getText().toString();
                String receiver = receiverEmail.getText().toString();
                String getpin = pin.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(senderE, senderEmail);
                editor.putString(Passw, password);
                editor.putString(receiverE, receiver);
                editor.putString(AccessPin,getpin);

                editor.commit();

                Toast.makeText(MainActivity.this, "Details Saved In Your Mobile", Toast.LENGTH_LONG).show();


            }
        });


    }


<<<<<<< HEAD
       // sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String emm =  xo;
        //Creating SendMail object

        Log.d("Bossb4main","before");

        Log.d("Boss4mainCrash",emm);

//        SendMail sm = new SendMail(this, emailx, subject, message, senderx, passx);

        SendMail sm = new SendMail(this, emailx, subject, message, xo, passx);

        String em = emailx;
        Log.d("Bossmain",em);
        //Executing sendmail to send email
        sm.execute();
        Log.d("After Execute", "Executed");
    }
=======
>>>>>>> pin-enter
}
