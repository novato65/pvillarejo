package com.solinpromex.pedrovillarejo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by modestovascofornas on 11/15/15.
 */
public class CitaServicio_NoLogin_4 extends Activity{


    Button continuarButton, cancelarButton;

    String nombre, email, cel, tel,fecha,hora,vehiculo,tipo,ano,comentarios;
    TextView tvNombre, tvEmail,tvCel,tvTel,tvFecha,tvHora,tvVehiculo,tvTipo,tvAno;
    EditText tvComentarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita_no_login_4);

        Intent intent = getIntent();
        nombre= intent.getStringExtra("nombre");
        email= intent.getStringExtra("email");
        cel= intent.getStringExtra("celular");
        tel= intent.getStringExtra("tel");
        fecha= intent.getStringExtra("fecha");
        hora= intent.getStringExtra("hora");
        vehiculo= intent.getStringExtra("vehiculo");
        tipo= intent.getStringExtra("tipo");
        ano= intent.getStringExtra("ano");

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvCel = (TextView) findViewById(R.id.tvCelular);
        tvTel = (TextView) findViewById(R.id.tvTel);
        tvFecha = (TextView) findViewById(R.id.tvFecha);
        tvHora = (TextView) findViewById(R.id.tvHora);
        tvVehiculo = (TextView) findViewById(R.id.tvVehiculo);
        tvTipo = (TextView) findViewById(R.id.tvTipo);
        tvAno = (TextView) findViewById(R.id.tvAno);
        tvComentarios = (EditText) findViewById(R.id.tvComentarios);

        tvNombre.setText(nombre);
        tvEmail.setText(email);
        tvCel.setText(cel);
        tvTel.setText(tel);
        tvFecha.setText(fecha);
        tvHora.setText(hora);
        tvVehiculo.setText(vehiculo);
        tvTipo.setText(tipo);
        tvAno.setText(ano);




        addListenerContinuarButton();
        addListenerCancelerButton();



    }



    public void addListenerContinuarButton() {

        continuarButton = (Button) findViewById(R.id.seguir);

        continuarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                comentarios = tvComentarios.getText().toString();
                Log.d("CITA A SERVICIO3", "COMENTARIOS: " + comentarios);


                ParseObject cita_servicio = new ParseObject("citas_servicio");
                cita_servicio.put("Nombre_cliente", nombre);
                cita_servicio.put("email_cliente", email);
                cita_servicio.put("celular_cliente", cel);
                cita_servicio.put("tel_cliente", tel);
                cita_servicio.put("vehiculo_cliente", vehiculo);
                cita_servicio.put("ano_vehiculo", ano);
                cita_servicio.put("tipo_cita", tipo);
                cita_servicio.put("fecha_cita", fecha);
                cita_servicio.put("comentarios", comentarios);
                cita_servicio.saveInBackground();


            }

        });

    }
    public void addListenerCancelerButton() {

        cancelarButton = (Button) findViewById(R.id.cancelar);

        cancelarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CitaServicio_NoLogin_4.this, WelcomeNoLogin.class);




                CitaServicio_NoLogin_4.this.startActivity(myIntent);

            }

        });
    }
}
