package com.example.peliculasencartelera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);

        final ArrayList<Pelicula> peliculas;



        // rellenamos el array de datos con las peliculas obtenidas de la URL

        Adaptador adaptador = new Adaptador(peliculas,this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
    }

    private class DesCargarInternetAsT extends AsyncTask<String,Boolean,Integer>
    {
        URL url;

        public DesCargarInternetAsT(){
            this.url = null;
            peliculas = null;
        }

        @Override
        protected Integer doInBackground(String... params) {
            boolean empezar = false;
            try {
                url = new URL(params[1]);
                XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                parser.setInput(url.openStream(),null);
                int evento = parser.getEventType();

                Pelicula aux = null;
                int cont = 0;

                while (evento != XmlPullParser.END_DOCUMENT && cont < 6){

                    if ( XmlPullParser.START_DOCUMENT == evento){
                        peliculas = new ArrayList<Pelicula>();
                    }else if (evento == XmlPullParser.START_TAG){
                        if (parser.getName().equals("atom10:link")){
                            empezar = true;
                        }else if (empezar == true){
                            if (parser.getName().equals("title")){
                                cont++;
                                aux = new Pelicula();
                                aux.setTitulo(parser.nextText());
                            }else if (parser.getName().equals("descripcion")){
                                String x = parser.nextText();
                                x = limpiarDescripcion(x);
                                aux.setDescripcion(x);

                            }else if (parser.getName().equals("enclosure")){
                                aux.setUrl(parser.getAttributeValue(null,"url"));
                            }
                        }

                    }else if (XmlPullParser.END_TAG == evento){
                        if (parser.getName().equals("item")){
                            peliculas.add(aux);
                        }
                    }
                    evento = parser.next();
                }

                return 1;

            }catch (IOException e){
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Integer integer){
            super.onPostExecute(integer);
            if (integer == 1){
                hiloImagenes = new DesCargarImagAst();
                hiloImagenes.execute();
            }

        }
    }

    class DesCargarImagAst extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Url url;
            Bitmap imagen;
            try {
                for(Pelicula x : peliculas){
                    url = new URL(x.getUrl());
                    imagen = BitmapFactory.decodeStream(url.openStream());
                    x.setImagen(imagen);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            cargarAdaptador();
            espera.dismiss;
        }
    }


}

