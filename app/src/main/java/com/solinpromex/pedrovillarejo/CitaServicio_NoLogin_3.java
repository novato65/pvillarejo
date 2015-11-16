package com.solinpromex.pedrovillarejo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
public class CitaServicio_NoLogin_3 extends Activity{


    Button continuarButton, cancelarButton;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    String nombre, email, cel, tel,fecha,hora,vehiculo;
    Spinner spinner1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita_no_login_3);

        Intent intent = getIntent();
        nombre= intent.getStringExtra("nombre");
        email= intent.getStringExtra("email");
        cel= intent.getStringExtra("celular");
        tel= intent.getStringExtra("tel");
        fecha= intent.getStringExtra("fecha");
        hora= intent.getStringExtra("hora");


       spinner1 = (Spinner) findViewById(R.id.spinner);
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("autos");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ArrayList<String> nameList = new ArrayList<>();
                    for(ParseObject object : list) {
                        nameList.add(object.getString("modelo"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(
                            getApplicationContext(),android.R.layout.simple_list_item_1 ,nameList);
                    spinner1.setAdapter(adapter);

                } else {

                }
            }
        });




        addListenerContinuarButton();
        addListenerCancelerButton();



    }



    public void addListenerContinuarButton() {

        continuarButton = (Button) findViewById(R.id.seguir);

        continuarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {








                vehiculo = spinner1.getSelectedItem().toString();


                Log.d("CITA A SERVICIO3", "NOMBRE: " + nombre);
                Log.d("CITA A SERVICIO3", "EMAIL: " + email);
                Log.d("CITA A SERVICIO3", "CEL: " + cel);
                Log.d("CITA A SERVICIO3", "TEL: " + tel);
                Log.d("CITA A SERVICIO3", "FECHA: " + fecha);
                Log.d("CITA A SERVICIO3", "HORA: " + hora);
                Log.d("CITA A SERVICIO3", "VEHICULO: " + vehiculo);

                Intent myIntent = new Intent(CitaServicio_NoLogin_3.this, CitaServicio_NoLogin_4.class);


                myIntent.putExtra("nombre", nombre);
                myIntent.putExtra("email", email);
                myIntent.putExtra("celular", cel);
                myIntent.putExtra("tel", tel);
                myIntent.putExtra("fecha", fecha);
                myIntent.putExtra("hora", hora);


                CitaServicio_NoLogin_3.this.startActivity(myIntent);

            }

        });

    }
    public void addListenerCancelerButton() {

        cancelarButton = (Button) findViewById(R.id.cancelar);

        cancelarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CitaServicio_NoLogin_3.this, WelcomeNoLogin.class);




                CitaServicio_NoLogin_3.this.startActivity(myIntent);

            }

        });
    }
}
