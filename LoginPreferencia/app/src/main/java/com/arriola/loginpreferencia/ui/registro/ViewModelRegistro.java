package com.arriola.loginpreferencia.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.arriola.loginpreferencia.model.Usuario;
import com.arriola.loginpreferencia.request.ApiClient;

public class ViewModelRegistro extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutable;
    public ViewModelRegistro(@NonNull Application application) {
        super(application);
        context = getApplication().getApplicationContext();
        
    }

    public LiveData<Usuario> getUsuarioMutable(){
        if(usuarioMutable == null){
            usuarioMutable = new MutableLiveData<Usuario>();
        }
        return usuarioMutable;
    }
    
    public void Guardar(String documento, String apellido, String nombre, String email, String password){
        Usuario usuario = new Usuario(documento, apellido, nombre, email, password);

        ApiClient.Guardar(context.getApplicationContext(), usuario);

        Toast.makeText(context, "Se guardaron los datos", Toast.LENGTH_SHORT).show();
    }

    public void Leer(Intent intent){
        boolean registro = intent.getBooleanExtra("registro", false);
        if(!registro){
            Usuario usuario = ApiClient.Leer(context);

            if(usuario != null){
                usuarioMutable.setValue(usuario);
            }
        }
    }
}
