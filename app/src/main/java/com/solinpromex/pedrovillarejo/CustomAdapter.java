package com.solinpromex.pedrovillarejo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter(WelcomeNoLogin mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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


                                    Intent myIntent = new Intent(context,MapaActivity.class);
                                    myIntent.putExtra("web", web); //Optional parameters

                                    context.startActivity(myIntent);

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