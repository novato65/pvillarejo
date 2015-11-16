package com.solinpromex.pedrovillarejo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by modestovascofornas on 11/15/15.
 */
public class ConsultaLogin extends Activity {

    Button crearCuenta,crearCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);


        addListenerCrearCuentaButton();
        addListenerCrearCitaButton();

    }

    public void addListenerCrearCuentaButton() {

        crearCuenta = (Button) findViewById(R.id.crearCuenta);

        crearCuenta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(ConsultaLogin.this, LoginSignupActivity.class);

                ConsultaLogin.this.startActivity(myIntent);

            }

        });

    }

    public void addListenerCrearCitaButton() {

        crearCita = (Button) findViewById(R.id.crearCita);

        crearCita.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(ConsultaLogin.this, CitaServicio_NoLogin_1.class);

                ConsultaLogin.this.startActivity(myIntent);

            }

        });

    }
}
