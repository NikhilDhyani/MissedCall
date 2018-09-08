package com.example.nikhil.detectcall.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.example.nikhil.detectcall.MainActivity.MyPREFERENCES;
import static com.example.nikhil.detectcall.MainActivity.Passw;
import static com.example.nikhil.detectcall.MainActivity.receiverE;
import static com.example.nikhil.detectcall.MainActivity.senderE;

/**
 * Created by NIKHIL on 06-09-2018.
 */

public class CallService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String numb = "xxxxx";

        if (intent!=null) {
            Bundle bundle =  intent.getExtras();
<<<<<<< HEAD
            numb = bundle.getString("KEY1");
=======
            numb = bundle.getString("num");
>>>>>>> pin-enter

            BackgroundThread thread = new BackgroundThread(numb);
            thread.start();

        }
<<<<<<< HEAD
        else
            if (intent==null){
            numb = "999999";
            Log.d("callxx", numb);

        }
=======
>>>>>>> pin-enter

        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public class BackgroundThread extends Thread
    {
        String  number;
        public BackgroundThread(String num) {

            this.number = num;
        }

        @Override
        public void run() {

            SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            Session session;

            final String senderEmail = sharedpreferences.getString(senderE, "");
            final String password = sharedpreferences.getString(Passw, "");
            final String email = sharedpreferences.getString(receiverE, "");


            String subject = "Missed Call Alert";
            String message = "Hey You have got a missed call from : " + number;


            //Creating properties
            Properties props = new Properties();
            Log.d("servicex", "bossdo");


            //Configuring properties for gmail
            //If you are not using gmail you may need to change the values
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            //Creating a new session
            session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        //Authenticating the password
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(senderEmail, password);


                        }
                    });

            try {
                //Creating MimeMessage object
                MimeMessage mm = new MimeMessage(session);


<<<<<<< HEAD
                Log.d("ReceiverMailXx",email);
                Log.d("PassMailXx",password);


                Log.d("SenderMailXx",senderEmail);
=======
>>>>>>> pin-enter
                //Setting sender address

                mm.setFrom(new InternetAddress(senderEmail));

<<<<<<< HEAD
                //  mm.setFrom(new InternetAddress(senderEmail));
                //Adding receiver
                mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                //Adding subject
                mm.setSubject(subject);
=======
                //Adding receiver Email Address

                mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

                //Adding subject
                mm.setSubject(subject);

>>>>>>> pin-enter
                //Adding message
                mm.setText(message);

                //Sending email
<<<<<<< HEAD

=======
>>>>>>> pin-enter
                Transport.send(mm);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> pin-enter
