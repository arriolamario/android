package com.arriola.preferenciasejemplo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class VerActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Integer> mColor;
    private MutableLiveData<Integer> mSize;
    public VerActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Integer> getMColor(){
        if(mColor == null)
            mColor = new MutableLiveData<>();
        return mColor;
    }

    public  LiveData<Integer> getMSize(){
        if(mSize == null)
            mSize = new MutableLiveData<>();
        return  mSize;
    }

    public void leerDatos(){
        SharedPreferences sp = context.getSharedPreferences("configuracion.xml", 0);

        String color = sp.getString("color", "");
        int size = sp.getInt("size", -1);

        if(color != "" && size != -1){
            switch (color){
                case "rojo": mColor.setValue(Color.RED); break;
                case "azul": mColor.setValue(Color.BLUE); break;
                case "amarillo": mColor.setValue(Color.YELLOW); break;
                case "verde": mColor.setValue(Color.GREEN); break;
                default: mColor.setValue(Color.BLACK); break;
            }

            mSize.setValue(size);
        }
    }
}
