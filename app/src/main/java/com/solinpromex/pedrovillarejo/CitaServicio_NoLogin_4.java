package com.solinpromex.pedrovillarejo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    String nombre, email, cel, tel,fecha,hora;


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


        final Spinner country = (Spinner) findViewById(R.id.spinner);
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
                    country.setAdapter(adapter);
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






                Log.d("CITA A SERVICIO", "NOMBRE: " + nombre);
                Log.d("CITA A SERVICIO", "EMAIL: " + email);
                Log.d("CITA A SERVICIO", "CEL: " + cel);
                Log.d("CITA A SERVICIO", "TEL: " + tel);

                Intent myIntent = new Intent(CitaServicio_NoLogin_4.this, CitaServicio_NoLogin_4.class);


                myIntent.putExtra("nombre", nombre);
                myIntent.putExtra("email", email);
                myIntent.putExtra("celular", cel);
                myIntent.putExtra("tel", tel);


                CitaServicio_NoLogin_4.this.startActivity(myIntent);

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
