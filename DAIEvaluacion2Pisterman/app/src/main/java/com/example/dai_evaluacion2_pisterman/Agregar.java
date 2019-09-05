package com.example.dai_evaluacion2_pisterman;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Agregar extends Fragment implements View.OnClickListener {

    Button agregar;
    EditText titulo,url;
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.agregar,container,false);
        agregar=vista.findViewById(R.id.BotonContinuar);
        titulo=vista.findViewById(R.id.editTitulo);
        url=vista.findViewById(R.id.editUrl);
        agregar.setOnClickListener(this);
        return vista;
    }

    @Override
    public void onClick(View v) {

        if(titulo.getText().length()>0&&url.getText().length()>0)
        {
            MainActivity principal= (MainActivity) getActivity();

            Log.d("agreg",""+principal.json.indexOf("]"));
            //principal.json.replace("]}",",{\\\"Titulo\\\":\\\"\"+titulo.getText()+\"\\\",\\\"Ubicacion\\\":\\\"\"+url.getText()+\"\\\"}]}");
            //String json="";
            //principal.json.replace("]}",json);
            String concatenado=principal.json.substring(0,1017);
            principal.json=concatenado;
            principal.json+=",{\"Titulo\":\""+titulo.getText()+"\",\"Ubicacion\":\""+url.getText()+"\"}]}";
            principal.VolverAVerOSiguiente();
        }
    }
}
