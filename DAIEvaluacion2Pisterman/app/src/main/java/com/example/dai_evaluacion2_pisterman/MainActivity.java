package com.example.dai_evaluacion2_pisterman;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    static MediaPlayer mediaPlayer;
    static boolean isPlayingAudio=false;
    public String json;
    int OpcionSeleccionada;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        json="{\"Fotos\":[{\"Titulo\":\"Paisaje\",\"Ubicacion\":\"https://concepto.de/wp-content/uploads/2015/03/paisaje-e1549600034372.jpg\"}, {\"Titulo\":\"Personas\",\"Ubicacion\":\"https://www.importancia.org/wp-content/uploads/personas.gif\"},{\"Titulo\":\"Lenguajes de programacion\",\"Ubicacion\":\"http://imagenes.universia.net/gc/net/images/ciencia-tecnologia/l/le/len/lenguajes-de-programacion.jpg\"}, {\"Titulo\":\"Redes sociales\",\"Ubicacion\":\"https://www.redeszone.net/app/uploads/2019/05/perfil-seguridad-redes-sociales.jpg\"}, {\"Titulo\":\"Banderas\",\"Ubicacion\":\"https://previews.123rf.com/images/kadirtinte/kadirtinte1403/kadirtinte140300004/27564296-banderas-oficiales-de-los-pa%C3%ADses.jpg\"},{\"Titulo\":\"Autos\",\"Ubicacion\":\"https://www.infobae.com/new-resizer/HxTcqZ5ZrR_qP92CKrDA0Jd6hug=/750x0/filters:quality(100)/s3.amazonaws.com/arc-wordpress-client-uploads/infobae-wp/wp-content/uploads/2019/01/23124051/los-autos-mas-vendidos-de-latam-de-2018-1920-.jpg\"}, {\"Titulo\":\"Peliculas\",\"Ubicacion\":\"https://i.blogs.es/25ffa9/pixar-30/450_1000.jpg\"}]}";
        Fragment VerOSiguiente=new VerOSiguiente();
        CambiarFragment(VerOSiguiente);
        AlertDialog.Builder mensaje=new AlertDialog.Builder(this);
        mensaje.setTitle("Para vos una pequñas canciones");
        String[] opciones={"Bicycle Race","Hijo De Hombre","Stayin' Alive","Yo quiero un héroe"};
        mensaje.setSingleChoiceItems(opciones,-1,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                OpcionSeleccionada=item;
            }
                }
        );
        mensaje.setPositiveButton("Continuar",Escucha);
        mensaje.setIcon(R.drawable.emoji);
        mensaje.create();
        mensaje.show();
    }
    DialogInterface.OnClickListener Escucha=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int CualSera) {

            if(OpcionSeleccionada==0)
            {
                PlayAudio(context,R.raw.bicycle);
            }
            if(OpcionSeleccionada==1)
            {
                PlayAudio(context,R.raw.tarzan);
            }
            if(OpcionSeleccionada==2)
            {
                PlayAudio(context,R.raw.alive);
            }
            if(OpcionSeleccionada==3)
            {
                PlayAudio(context,R.raw.shrek);
            }
        }
    };
    public static void PlayAudio(Context c, int id){
        mediaPlayer = MediaPlayer.create(c, id);
        SoundPool soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC,50);
        if (!mediaPlayer.isPlaying())
        {
            isPlayingAudio= true;
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
    }

    public void VolverAVerOSiguiente()
    {
        Log.d("agreg",""+json.length());
        Fragment VerOSiguiente=new VerOSiguiente();
        CambiarFragment(VerOSiguiente);
    }
    public void IrAAgregar()
    {
        Log.d("Agregar","entro");
        Fragment Agregar=new Agregar();
        CambiarFragment(Agregar);
    }
    private void CambiarFragment(Fragment FragemntACambiar)
    {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayoutMiQueridoAmigo,FragemntACambiar);
        fragmentTransaction.commit();
    }
}
