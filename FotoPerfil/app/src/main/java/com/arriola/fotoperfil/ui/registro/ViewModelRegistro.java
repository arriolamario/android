package com.arriola.fotoperfil.ui.registro;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.arriola.fotoperfil.model.Usuario;
import com.arriola.fotoperfil.request.ApiClient;

import java.io.ByteArrayOutputStream;

public class ViewModelRegistro extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutable;
    private MutableLiveData<Uri> uriMutableLiveData;
    private Uri fotoPerfilUri;
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
        Usuario usuario = new Usuario(documento, apellido, nombre, email, password, fotoPerfilUri.toString());

        ApiClient.Guardar(context.getApplicationContext(), usuario);

        Toast.makeText(context, "Se guardaron los datos", Toast.LENGTH_SHORT).show();
    }

    public void Leer(Intent intent){
        boolean registro = intent.getBooleanExtra("registro", false);
        if(!registro){
            Usuario usuario = ApiClient.Leer(context);

            if(usuario != null){
                Log.d("SALIDA-LEER", usuario.getFoto());
                usuarioMutable.setValue(usuario);
            }
        }
    }

    public LiveData<Uri> getUriMutable(){

        if(uriMutableLiveData==null){
            uriMutableLiveData=new MutableLiveData<>();
        }
        return uriMutableLiveData;
    }

    public void recibirFoto(ActivityResult result) {
        if(result.getResultCode() == RESULT_OK){
            Intent data=result.getData();
            fotoPerfilUri =data.getData();
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
                context.getContentResolver().takePersistableUriPermission (fotoPerfilUri, Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            uriMutableLiveData.setValue(fotoPerfilUri);
            Log.d("SALIDA-RECIBIR-FOTO", fotoPerfilUri.toString());
        }
    }
}
