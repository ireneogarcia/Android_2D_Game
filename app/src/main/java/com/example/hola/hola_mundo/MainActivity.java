package com.example.hola.hola_mundo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

//Changed extends to Activity
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set fullscreen app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Desactiva la barra de titulo
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Crea el panel de juego pasandole toda la clase
        setContentView(new Panel(this));
    }
}
