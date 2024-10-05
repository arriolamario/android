package com.arriola.preferenciasejemplo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void guardarDatos(String color, String size){
        if(!color.isEmpty() && !size.isEmpty()) {
            SharedPreferences sp = context.getSharedPreferences("configuracion.xml", 0);
            SharedPreferences.Editor editor = sp.edit();
            int tam = Integer.parseInt(size);
            editor.putString("color", color);
            editor.putInt("size", tam);

            editor.commit();
            Toast.makeText(context, "Datos Guardados", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(context, VerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }
}
