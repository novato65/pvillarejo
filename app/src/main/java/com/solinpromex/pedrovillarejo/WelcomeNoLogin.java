package com.solinpromex.pedrovillarejo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class WelcomeNoLogin extends Activity {
	GridView gv;
	Context context;
	ArrayList prgmName;
	ImageButton homeButton, llamarButton;
	public static String[] prgmNameList = {"Llámeme", "Autos Nuevos", "Seminuevos", "Descuento Proveedores", "Citas a servicio", "Mapa", "Enviar Email", "Enviar Mensaje", "Compartir App"};
	public static int[] prgmImages = {R.mipmap.llamar, R.mipmap.nuevos, R.mipmap.seminuevos, R.mipmap.descuento, R.mipmap.citas, R.mipmap.mapa, R.mipmap.email, R.mipmap.sms, R.mipmap.compartir};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.welcome_no_login);
		gv = (GridView) findViewById(R.id.grid);
		gv.setAdapter(new CustomAdapter(this, prgmNameList, prgmImages));

		addListenerHomeButton();
		addListenerLlamarButton();
	}


	public void addListenerHomeButton() {

		homeButton = (ImageButton) findViewById(R.id.homeButton);

		homeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent myIntent = new Intent(WelcomeNoLogin.this, LoginSignupActivity.class);

				WelcomeNoLogin.this.startActivity(myIntent);

			}

		});

	}

	public void addListenerLlamarButton() {

		llamarButton = (ImageButton) findViewById(R.id.llamarButton);

		llamarButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
				query.whereEqualTo("tipo_contacto", "celular");
				query.findInBackground(new FindCallback<ParseObject>() {
					public void done(List<ParseObject> scoreList, ParseException e) {
						if (e == null) {
							int len = scoreList.size();
							for (int i = 0; i < len; i++) {
								ParseObject p = scoreList.get(i);
								String numero = p.getString("dato_contacto");

								Log.d("score", "Celular: " + numero);

								Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero));

								startActivity(intent);

							}
						} else {
							Log.d("score", "Error: " + e.getMessage());
						}
					}
				});


			}

		});

	}
}