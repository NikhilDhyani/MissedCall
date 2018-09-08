package com.example.nikhil.detectcall;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.nikhil.detectcall.Config.Config;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.example.nikhil.detectcall.MainActivity.Passw;
import static com.example.nikhil.detectcall.MainActivity.passx;
import static com.example.nikhil.detectcall.MainActivity.senderE;
import static com.example.nikhil.detectcall.MainActivity.senderx;

/**
 * Created by NIKHIL on 31-08-2018.
 */

public class SendMail extends AsyncTask<Void,Void,Void> {

    //Declaring Variables
    private Context context;
    private Session session;

    //Information to send email
    private String email;
    private String subject;
    private String message;

    private String senderEmail;
    private String password;

    //SharedPreferences Spreferences ;

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;



    //Class Constructor
    public SendMail(Context context, String email, String subject, String message,String sender,String pass){
        //Initializing variables
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;

        this.senderEmail = sender;
        this.password  = pass;

        Log.d("Boss","boss");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Log.d("Bosspe","bosspe");


    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Log.d("Bosspo","bosspost");

    }

    @Override
    protected Void doInBackground(Void... params) {
        //Creating properties
        Properties props = new Properties();
        Log.d("Bossdo","bossdo");

     //   Spreferences =  PreferenceManager.getDefaultSharedPreferences(context);

      //  final String senderxx = Spreferences.getString(senderE, "");

       // final String passxx = Spreferences.getString(Passw, "");


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

                    //    return new PasswordAuthentication(senderxx, passxx);


                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            Log.d("SenderMailXx",senderEmail);
            //Setting sender address

            mm.setFrom(new InternetAddress(senderEmail));

            //  mm.setFrom(new InternetAddress(senderEmail));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Adding subject
            mm.setSubject(subject);
            //Adding message
            mm.setText(message);

            //Sending email
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}