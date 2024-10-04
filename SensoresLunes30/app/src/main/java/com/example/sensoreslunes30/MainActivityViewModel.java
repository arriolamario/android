package com.example.sensoreslunes30;

import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<StringBuilder> mLectura;
    private SensorManager sensorManager;
    private EscuchaDeLecturas escuchaDeLecturas;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        Context context = this.getApplication().getApplicationContext();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        escuchaDeLecturas = new EscuchaDeLecturas();
    }

    public LiveData<StringBuilder> getMLectura(){
        if(mLectura == null){
            mLectura = new MutableLiveData<>();
        }

        return  mLectura;
    }

    public void obtenerLecturas(){
        //Toast.makeText(this.getApplication().getApplicationContext(), "obtenerLecturas", Toast.LENGTH_SHORT).show();
        List<Sensor> sensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if(!sensores.isEmpty()){
            Toast.makeText(this.getApplication().getApplicationContext(), "Existe Acelerometro", Toast.LENGTH_SHORT).show();
            Sensor acelerometro = sensores.get(0);

            sensorManager.registerListener(escuchaDeLecturas, acelerometro, SensorManager.SENSOR_DELAY_GAME);
        }
        else{
            Toast.makeText(this.getApplication().getApplicationContext(), "No tiene sensor acelerometro", Toast.LENGTH_SHORT).show();
        }
    }

    public void pararLecturas(){
        sensorManager.unregisterListener(escuchaDeLecturas);
    }

    public class EscuchaDeLecturas implements SensorEventListener {
        private StringBuilder sb = new StringBuilder();
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            Toast.makeText(getApplication().getApplicationContext(), "Cambio de sensor", Toast.LENGTH_SHORT).show();
            float acelerX = sensorEvent.values[0];
            float acelerY = sensorEvent.values[1];
            float acelerZ = sensorEvent.values[2];

            sb.append("X " + acelerX + " Y " + acelerY + " Z " + acelerZ+"\n");

            mLectura.postValue(sb);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }
}
