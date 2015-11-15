package com.solinpromex.pedrovillarejo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by modestovascofornas on 11/13/15.
 */
public class CustomAdapter extends BaseAdapter {

    String [] result, recipients;
    Context context;
    int [] imageId;



    String nombre_POI,latitud,longitud,direccion,recipient,subject,body;
    private static LayoutInflater inflater=null;
    public CustomAdapter(WelcomeNoLogin mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //query a Parse

        ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
        query.whereEqualTo("tipo_contacto", "nombre_POI");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    int len = scoreList.size();
                    for (int i = 0; i < len; i++) {
                        ParseObject p = scoreList.get(i);
                        nombre_POI = p.getString("dato_contacto");

                        Log.d("Nombre POI", "NOMBRE POI: " + nombre_POI);


                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("datos_contacto");
        query1.whereEqualTo("tipo_contacto", "latitud");
        query1.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    int len = scoreList.size();
                    for (int i = 0; i < len; i++) {
                        ParseObject p = scoreList.get(i);
                        latitud = p.getString("dato_contacto");

                        Log.d("LATITUD POI", "LATITUD POI: " + latitud);


                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("datos_contacto");
        query2.whereEqualTo("tipo_contacto", "longitud");
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    int len = scoreList.size();
                    for (int i = 0; i < len; i++) {
                        ParseObject p = scoreList.get(i);
                        longitud = p.getString("dato_contacto");

                        Log.d("LONGITUD POI", "LONGITUD POI: " + longitud);


                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        ParseQuery<ParseObject> query5 = ParseQuery.getQuery("datos_contacto");
        query5.whereEqualTo("tipo_contacto", "direccion");
        query5.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    int len = scoreList.size();
                    for (int i = 0; i < len; i++) {
                        ParseObject p = scoreList.get(i);
                        direccion = p.getString("dato_contacto");

                        Log.d("DIRECCION POI", "DIRECCION POI: " + direccion);


                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });



    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.grid_single, null);
        holder.tv=(TextView) rowView.findViewById(R.id.grid_text);
        holder.img=(ImageView) rowView.findViewById(R.id.grid_image);

        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "Ha seleccionado " + result[position], Toast.LENGTH_LONG).show();
                //posiciones
                //position = 0 -->llamar
                //position = 1 -->abrir web de autos nuevos
                //position = 2 -->abrir web de autos usados
                //position = 3 -->abrir web de dto a proveedores
                //position = 4 -->abrir proceso de cita
                //position = 5 -->abrir mapa
                //position = 6 -->enviar email
                //position = 7 -->enviar SMS
                //position = 8 -->abrir proceso compartir app


                if (position == 0) {

                    //Toast.makeText(context, "Ha seleccionado LLAMAR" + result[position], Toast.LENGTH_LONG).show();

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
                                    context.startActivity(intent);

                                }
                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }
                    });

                }

                if (position == 1) {

                    //Toast.makeText(context, "Ha seleccionado LLAMAR" + result[position], Toast.LENGTH_LONG).show();

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
                    query.whereEqualTo("tipo_contacto", "web_nuevos");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (e == null) {
                                int len = scoreList.size();
                                for (int i = 0; i < len; i++) {
                                    ParseObject p = scoreList.get(i);
                                    String web = p.getString("dato_contacto");

                                    Log.d("score", "Web: " + web);


                                    Intent myIntent = new Intent(context,NuevosActivity.class);
                                    myIntent.putExtra("web", web); //Optional parameters

                                    context.startActivity(myIntent);

                                }
                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }
                    });

                }
                if (position == 2) {

                    //Toast.makeText(context, "Ha seleccionado LLAMAR" + result[position], Toast.LENGTH_LONG).show();

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
                    query.whereEqualTo("tipo_contacto", "web_semi_nuevos");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (e == null) {
                                int len = scoreList.size();
                                for (int i = 0; i < len; i++) {
                                    ParseObject p = scoreList.get(i);
                                    String web = p.getString("dato_contacto");

                                    Log.d("score", "Web: " + web);


                                    Intent myIntent = new Intent(context,SemiNuevosActivity.class);
                                    myIntent.putExtra("web", web); //Optional parameters

                                    context.startActivity(myIntent);

                                }
                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }
                    });

                }

                if (position == 3) {

                    //Toast.makeText(context, "Ha seleccionado LLAMAR" + result[position], Toast.LENGTH_LONG).show();

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
                    query.whereEqualTo("tipo_contacto", "web_proveedores");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (e == null) {
                                int len = scoreList.size();
                                for (int i = 0; i < len; i++) {
                                    ParseObject p = scoreList.get(i);
                                    String web = p.getString("dato_contacto");

                                    Log.d("score", "Web: " + web);


                                    Intent myIntent = new Intent(context,ProveedoresActivity.class);
                                    myIntent.putExtra("web", web); //Optional parameters

                                    context.startActivity(myIntent);

                                }
                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }
                    });

                }

                if (position == 4) {

                }
                if (position == 5) {

                    //Toast.makeText(context, "Ha seleccionado LLAMAR" + result[position], Toast.LENGTH_LONG).show();


                    ParseQuery<ParseObject> query3 = ParseQuery.getQuery("datos_contacto");
                    query3.whereEqualTo("tipo_contacto", "web_proveedores");
                    query3.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (e == null) {
                                int len = scoreList.size();
                                for (int i = 0; i < len; i++) {
                                    ParseObject p = scoreList.get(i);
                                    String web = p.getString("dato_contacto");

                                    Log.d("score", "Web: " + web);
                                    Log.d("LONGITUD POI  FINAL", "LONGITUD POI: " + longitud);

                                    Log.d("LATITUD POI  FINAL", "LATITUD POI: " + latitud);


                                    Log.d("DIRECCION POI FINAL", "DIRECCION POI: " + direccion);


                                    Intent myIntent = new Intent(context,MapaActivity.class);
                                    myIntent.putExtra("nombre_POI", nombre_POI);
                                    myIntent.putExtra("latitud", latitud);
                                    myIntent.putExtra("longitud", longitud);
                                    myIntent.putExtra("direccion", direccion);

                                    //Optional parameters

                                    context.startActivity(myIntent);

                                }
                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }
                    });

                }

                if (position == 6) {

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
                    query.whereEqualTo("tipo_contacto", "email_contacto");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (e == null) {
                                int len = scoreList.size();
                                for (int i = 0; i < len; i++) {
                                    ParseObject p = scoreList.get(i);
                                    String email = p.getString("dato_contacto");


                                    Log.d("EMAIL FINAL", "EMAIL: " + email);

                                    subject = "Enviado desde la Android App PEDRO VILLAREJO";
                                    body = "Escriba aquí el contenido de su email";
                                    recipient = email;
                                    Intent enviar = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                                    // prompts email clients only
                                    enviar.setType("message/rfc822");

                                    enviar.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                                    enviar.putExtra(Intent.EXTRA_SUBJECT, "Enviado desde la Android App PEDRO VILLAREJO");
                                    enviar.putExtra(Intent.EXTRA_TEXT, "Escriba aquí el texto de su email");

                                    try {
                                        // the user can choose the email client
                                        context.startActivity(Intent.createChooser(enviar, "Seleccione una aplicación para enviar el email..."));

                                    } catch (android.content.ActivityNotFoundException ex) {
                                        Toast.makeText(context, "No dispone de aplicaciones email.",
                                                Toast.LENGTH_LONG).show();
                                    }





                                }
                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }
                    });

                }
                if (position == 7) {

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("datos_contacto");
                    query.whereEqualTo("tipo_contacto", "celular_sms");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> scoreList, ParseException e) {
                            if (e == null) {
                                int len = scoreList.size();
                                for (int i = 0; i < len; i++) {
                                    ParseObject p = scoreList.get(i);
                                    String celular_sms = p.getString("dato_contacto");


                                    Log.d("CELULAR", "CELULAR: " + celular_sms);

// Initialize SmsManager Object// add the phone number in the data

                                    Uri uri = Uri.parse("smsto:" + celular_sms);



                                    Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);

                                    // add the message at the sms_body extra field

                                    smsSIntent.putExtra("sms_body", "Texto de su SMS");

                                    try{

                                        context.startActivity(smsSIntent);

                                    } catch (Exception ex) {

                                        Toast.makeText(context, "ERROR - SMS no enviado...",

                                                Toast.LENGTH_LONG).show();

                                        ex.printStackTrace();

                                    }

                                }
                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }
                    });

                }
            }
        });

        return rowView;
    }

}