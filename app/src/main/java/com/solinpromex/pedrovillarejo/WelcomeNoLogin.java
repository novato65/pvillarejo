package com.solinpromex.pedrovillarejo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;

public class WelcomeNoLogin extends Activity {
	GridView gv;
	Context context;
	ArrayList prgmName;
	ImageButton homeButton;
	public static String [] prgmNameList={"Llámeme","Autos Nuevos","Seminuevos","Descuento Proveedores","Citas a servicio","Mapa","Enviar Email","Enviar Mensaje","Compartir App"};
	public static int [] prgmImages={R.mipmap.llamar,R.mipmap.nuevos,R.mipmap.seminuevos,R.mipmap.descuento,R.mipmap.citas,R.mipmap.mapa,R.mipmap.email,R.mipmap.sms,R.mipmap.compartir};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



		setContentView(R.layout.welcome_no_login);
		gv=(GridView) findViewById(R.id.grid);
		gv.setAdapter(new CustomAdapter(this, prgmNameList, prgmImages));

		addListenerHomeButton();
	}


	public void addListenerHomeButton() {

		homeButton = (ImageButton) findViewById(R.id.homeButton);

		homeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent myIntent = new Intent(WelcomeNoLogin.this,LoginSignupActivity.class);

				WelcomeNoLogin.this.startActivity(myIntent);

			}

		});

	}
}