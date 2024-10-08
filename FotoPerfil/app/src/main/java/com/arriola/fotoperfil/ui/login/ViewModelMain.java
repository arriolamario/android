package com.arriola.fotoperfil.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.arriola.fotoperfil.request.ApiClient;
import com.arriola.fotoperfil.ui.registro.RegistroActivity;

public class ViewModelMain extends AndroidViewModel {
    private Context context;
    public ViewModelMain(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void Entrar(String mail, String password){
        if(ApiClient.Login(context, mail, password)){
            Intent intent = new Intent(context, RegistroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("registro", false);
            context.startActivity(intent);
        }
        else{
            Toast.makeText(context, "Email/Password incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Registrar(){
        Intent intent = new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("registro", true);
        context.startActivity(intent);
    }
}
