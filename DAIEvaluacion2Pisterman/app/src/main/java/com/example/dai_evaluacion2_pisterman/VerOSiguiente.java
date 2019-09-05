package com.example.dai_evaluacion2_pisterman;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

public class VerOSiguiente extends Fragment implements View.OnClickListener {
    View VistaAUsar;
    ImageView imagen;
    TextView titulo;
    Button Siguiente,Ver,agregar;
    ArrayList<Evaluacion> listita;
    int position=0;
    public View onCreateView(LayoutInflater inflador, ViewGroup parent, Bundle datosRecibidos)
    {
        VistaAUsar=inflador.inflate(R.layout.la_veo_o_no,parent,false);
        imagen=VistaAUsar.findViewById(R.id.ImageViewAUsar);
        titulo=VistaAUsar.findViewById(R.id.TituloImagen);
        Siguiente=VistaAUsar.findViewById(R.id.BotonSiguiente);
        Ver=VistaAUsar.findViewById(R.id.BotonVer);
        listita=new ArrayList<>();
        agregar=VistaAUsar.findViewById(R.id.AgregarInfo);
        TareaAsincronica miTarea=new TareaAsincronica();
        miTarea.execute();
        Siguiente.setOnClickListener(this);
        Ver.setOnClickListener(this);
        agregar.setOnClickListener(this);
        return VistaAUsar;
    }

    @Override
    public void onClick(View v) {
        Button recibido = (Button) v;
        if (recibido.getId() == R.id.BotonSiguiente) {
            position++;
            if(position==listita.size())
            {
                Siguiente.setEnabled(true);
            }
            titulo.setText(listita.get(position).getTitulo());
            imagen.setVisibility(View.INVISIBLE);
        }
        else
        {if (recibido.getId() == Ver.getId()) {
            imagen.setVisibility(View.VISIBLE);
            Glide.with(this.getContext()).load(listita.get(position).getURlImagen()).apply(new RequestOptions()).into(imagen);
        }
        else
        {if(recibido.getId()==agregar.getId());
            {
                MainActivity principal= (MainActivity) getActivity();
                principal.IrAAgregar();
            }}}


    }

    private class TareaAsincronica extends AsyncTask<Void,Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            titulo.setText(listita.get(position).getTitulo());
        }

        @Override

        protected Void doInBackground(Void... voids) {
            try{
                MainActivity principal=(MainActivity) getActivity();
                String GsonQuerido=principal.json;
                procesarJSONLeido(GsonQuerido);
            }catch(Exception e)
            {
                Log.d("Error",""+e.getLocalizedMessage());
            }
            return null;
        }
        private void procesarJSONLeido(String JsonCrudo)
        {
            Log.d("procesarJSONLeido","Entro a procesar el JSON");
            JsonParser parseador;
            parseador=new JsonParser();
            JsonObject objetoJsonCrudito;
            objetoJsonCrudito=parseador.parse(JsonCrudo).getAsJsonObject();
            Log.d("procesarJSONLeido","post parse");
            JsonArray arrSearch;
            arrSearch=objetoJsonCrudito.get("Fotos").getAsJsonArray();
            Log.d("procesarJSONLeido","Antes del for");
            for (int i = 0; i < arrSearch.size(); i++) {

                JsonObject objetoActual = arrSearch.get(i).getAsJsonObject();
                String Titulo = objetoActual.get("Titulo").getAsString();
                Log.d("agreg",""+objetoActual.get("Titulo").getAsString());
                String UrlImagen=objetoActual.get("Ubicacion").getAsString();
                Evaluacion evaluacion=new Evaluacion(Titulo,UrlImagen);
                listita.add(evaluacion);
            }

            Log.d("procesarJSONLeido",""+listita.size());
        }
    }

}
