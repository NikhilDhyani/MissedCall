package com.example.nikhil.detectcall.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * Created by NIKHIL on 30-08-2018.
 */

public class MyReceiver extends BroadcastReceiver {

    private  static int wasRinging =0;
    private  static int wasPicked =0;
    private  static int isIdle =0;

    Handler handler;

    @Override
    public void onReceive(Context context, Intent intent) {

        handler = new Handler();

        Bundle extras = intent.getExtras();

        if (extras!=null)
        {
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            Log.w("MY_DEBUG_TAG", state);

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING))
            {
                wasRinging = 1;
                Log.d("wasRinging",Integer.toString(wasRinging));
                Log.d("wasPicked",Integer.toString(wasPicked));
                Log.d("isIdle",Integer.toString(isIdle));

            }
            if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
            {
                wasPicked = 1;
            }

            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE))
            {
                isIdle =1;

            }
        }

        if (wasRinging==1 && wasPicked ==0 && isIdle ==1)
        {

            Toast.makeText(context,"You didn't piclk the call",Toast.LENGTH_LONG).show();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    sendEmail();


                }
            };


            wasRinging=0;
            wasPicked=0;
            isIdle=0;
        }
        else
        if (wasRinging==1 && wasPicked ==1 && isIdle ==1)
        {

            Toast.makeText(context,"Picked  call",Toast.LENGTH_LONG).show();
            wasRinging=0;
            wasPicked=0;
            isIdle=0;

        }

    }



    private void sendEmail() {

        final String username = "email_Id";
        final String password = "password";

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress(username));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress("starcoder017@gmail.com"));
            //Adding subject
            mm.setSubject("test");
            //Adding message
            mm.setText("msg text");

            //Sending email
            Transport.send(mm);

            System.out.println("Done");
            Log.d("DeliveryStatus","successful");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
