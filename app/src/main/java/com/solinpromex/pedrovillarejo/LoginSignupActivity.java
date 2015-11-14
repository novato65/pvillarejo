package com.solinpromex.pedrovillarejo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginSignupActivity extends Activity {
	// Declare Variables
	Button loginbutton;
	Button signup;
	Button nologin;
	String usernametxt;
	String passwordtxt;
	EditText password;
	EditText username;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from main.xml
		setContentView(com.solinpromex.pedrovillarejo.R.layout.loginsignup);
		// Locate EditTexts in main.xml
		username = (EditText) findViewById(com.solinpromex.pedrovillarejo.R.id.username);
		password = (EditText) findViewById(com.solinpromex.pedrovillarejo.R.id.password);

		// Locate Buttons in main.xml
		loginbutton = (Button) findViewById(com.solinpromex.pedrovillarejo.R.id.login);
		signup = (Button) findViewById(com.solinpromex.pedrovillarejo.R.id.signup);
		nologin = (Button) findViewById(com.solinpromex.pedrovillarejo.R.id.nologin);

		// Login Button Click Listener
		loginbutton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// Retrieve the text entered from the EditText
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();

				// Send data to Parse.com for verification
				ParseUser.logInInBackground(usernametxt, passwordtxt,
						new LogInCallback() {
							public void done(ParseUser user, ParseException e) {
								if (user != null) {
									// If user exist and authenticated, send user to Welcome.class
									Intent intent = new Intent(
											LoginSignupActivity.this,
											Welcome.class);
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Successfully Logged in",
											Toast.LENGTH_LONG).show();
									finish();
								} else {
									Toast.makeText(
											getApplicationContext(),
											"No such user exist, please signup",
											Toast.LENGTH_LONG).show();
								}
							}
						});
			}
		});
		// Sign up Button Click Listener
		signup.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// Retrieve the text entered from the EditText
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();
				
				// Force user to fill up the form
				if (usernametxt.equals("") && passwordtxt.equals("")) {
					Toast.makeText(getApplicationContext(),
							"Please complete the sign up form",
							Toast.LENGTH_LONG).show();

				} else {
					// Save new user data into Parse.com Data Storage
					ParseUser user = new ParseUser();
					user.setUsername(usernametxt);
					user.setPassword(passwordtxt);
					user.signUpInBackground(new SignUpCallback() {
						public void done(ParseException e) {
							if (e == null) {
								// Show a simple Toast message upon successful registration
								Toast.makeText(getApplicationContext(),
										"Successfully Signed up, please log in.",
										Toast.LENGTH_LONG).show();
							} else {
								Toast.makeText(getApplicationContext(),
										"Sign up Error", Toast.LENGTH_LONG)
										.show();
							}
						}
					});
				}

			}
		});
		// nologin Button Click Listener
		nologin.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				Intent myIntent = new Intent(LoginSignupActivity.this,WelcomeNoLogin.class);

				LoginSignupActivity.this.startActivity(myIntent);


			}
		});

	}
}
