package com.solinpromex.pedrovillarejo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by modestovascofornas on 11/15/15.
 */
public class CitaServicio_NoLogin_2 extends Activity{


    Button continuarButton, cancelarButton;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    String nombre, email, cel, tel, fecha, hora;
    private TextView displayTime;
    private Button pickTime;



    private int pHour;
    private int pMinute;
    /** This integer will uniquely define the dialog to be used for displaying time picker.*/
    static final int TIME_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita_no_login_2);

        Intent intent = getIntent();
        nombre= intent.getStringExtra("nombre");
        email= intent.getStringExtra("email");
        cel= intent.getStringExtra("celular");
        tel= intent.getStringExtra("tel");


        dateView = (TextView) findViewById(R.id.editfecha);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);


        /** Capture our View elements */
        displayTime = (TextView) findViewById(R.id.edithora);
        pickTime = (Button) findViewById(R.id.buttonTime);

        /** Listener for click event of the button */
        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(998);
            }
        });

        /** Get the current time */
        final Calendar cal = Calendar.getInstance();
        pHour = cal.get(Calendar.HOUR_OF_DAY);
        pMinute = cal.get(Calendar.MINUTE);

        /** Display the current time in the TextView */
        updateDisplay();

        addListenerContinuarButton();
        addListenerCancelerButton();



    }


    /** Callback received when the user "picks" a time in the dialog */
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;
                    updateDisplay();
                    displayToast();
                }
            };

    /** Updates the time in the TextView */
    private void updateDisplay() {
        displayTime.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)));
    }

    /** Displays a notification when the time is updated */
    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Time choosen is ").append(displayTime.getText()),   Toast.LENGTH_SHORT).show();

    }

    /** Add padding to numbers less than ten */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }



    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Seleccione fecha", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        if (id == 998) {
            return new TimePickerDialog(this,
                    mTimeSetListener, pHour, pMinute, false);
        }
        return null;
    }



    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
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

                Intent myIntent = new Intent(CitaServicio_NoLogin_2.this, CitaServicio_NoLogin_3.class);




                fecha = dateView.getText().toString();
                hora = displayTime.getText().toString();

                myIntent.putExtra("nombre", nombre);
                myIntent.putExtra("email", email);
                myIntent.putExtra("celular", cel);
                myIntent.putExtra("tel", tel);
                myIntent.putExtra("fecha", fecha);
                myIntent.putExtra("hora", hora);

                CitaServicio_NoLogin_2.this.startActivity(myIntent);

            }

        });

    }
    public void addListenerCancelerButton() {

        cancelarButton = (Button) findViewById(R.id.cancelar);

        cancelarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent myIntent = new Intent(CitaServicio_NoLogin_2.this, WelcomeNoLogin.class);




                CitaServicio_NoLogin_2.this.startActivity(myIntent);

            }

        });
    }
}
