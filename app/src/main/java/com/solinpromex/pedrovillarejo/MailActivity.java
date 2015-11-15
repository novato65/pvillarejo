package com.solinpromex.pedrovillarejo;

import android.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by modestovascofornas on 11/13/15.
 */
public class MailActivity extends Activity {

    private EditText recipient;
    private EditText subject;
    private EditText body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        Log.d("EMAIL FINAL", "EMAIL: " + email);
        recipient = (EditText) findViewById(R.id.recipient);
        subject = (EditText) findViewById(R.id.subject);
        body = (EditText) findViewById(R.id.body);
        recipient.setText(email);
        subject.setText("Email desde la Android App de Pedro Villarejo");
        body.setText("");


        Button sendBtn = (Button) findViewById(R.id.sendEmail);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();


            }

        });
    }


        protected void sendEmail() {

            String[] recipients = {recipient.getText().toString()};
            Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
            // prompts email clients only
            email.setType("message/rfc822");

            email.putExtra(Intent.EXTRA_EMAIL, email);
            email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
            email.putExtra(Intent.EXTRA_TEXT, body.getText().toString());

            try {
                // the user can choose the email client
                startActivity(Intent.createChooser(email, "Choose an email client from..."));

            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MailActivity.this, "No email client installed.",
                        Toast.LENGTH_LONG).show();
            }
        }


}