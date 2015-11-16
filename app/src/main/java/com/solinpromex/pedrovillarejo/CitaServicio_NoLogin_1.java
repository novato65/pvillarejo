package com.solinpromex.pedrovillarejo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by modestovascofornas on 11/15/15.
 */
public class CitaServicio_NoLogin_1 extends Activity{

    EditText nombreTV, emailTV, celTV,telTV;
    Button continuarButton, cancelarButton;
    String nombre, email, cel, tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita_no_login_1);


        addListenerContinuarButton();
        addListenerCancelerButton();



    }

    public void addListenerContinuarButton() {

        continuarButton = (Button) findViewById(R.id.seguir);

        continuarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                nombreTV = (EditText) findViewById(R.id.editnombre);
                emailTV = (EditText) findViewById(R.id.editemail);
                celTV = (EditText) findViewById(R.id.editcelular);
                telTV = (EditText) findViewById(R.id.edittel);



                nombre = nombreTV.getText().toString();
                email = emailTV.getText().toString();
                cel = celTV.getText().toString();
                tel = telTV.getText().toString();


                Log.d("CITA A SERVICIO", "NOMBRE: " + nombre);
                Log.d("CITA A SERVICIO", "EMAIL: " + email);
                Log.d("CITA A SERVICIO", "CEL: " + cel);
                Log.d("CITA A SERVICIO", "TEL: " + tel);

                Intent myIntent = new Intent(CitaServicio_NoLogin_1.this, CitaServicio_NoLogin_2.class);


                myIntent.putExtra("nombre", nombre);
                myIntent.putExtra("email", email);
                myIntent.putExtra("celular", cel);
                myIntent.putExtra("tel", tel);


                CitaServicio_NoLogin_1.this.startActivity(myIntent);

            }

        });

    }
    public void addListenerCancelerButton() {

        cancelarButton = (Button) findViewById(R.id.cancelar);

        cancelarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CitaServicio_NoLogin_1.this, WelcomeNoLogin.class);




                CitaServicio_NoLogin_1.this.startActivity(myIntent);

            }

        });
    }
}
