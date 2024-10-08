package com.arriola.fotoperfil.ui.registro;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.arriola.fotoperfil.databinding.ActivityRegistroBinding;
import com.arriola.fotoperfil.model.Usuario;

import java.io.ByteArrayOutputStream;

public class RegistroActivity extends AppCompatActivity {
    private ActivityRegistroBinding bind;
    private ViewModelRegistro vm;
    private Intent intent;
    private ActivityResultLauncher<Intent> arl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityRegistroBinding.inflate(getLayoutInflater());

        setContentView(bind.getRoot());
        abrirGaleria();
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelRegistro.class);

        vm.getUsuarioMutable().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                bind.etApellido.setText(usuario.getApellido());
                bind.etDocumento.setText(usuario.getDocumento());
                bind.etNombre.setText(usuario.getNombre());
                bind.etEmailRegistro.setText(usuario.getMail());
                bind.etPasswordRegistro.setText(usuario.getPassword());
                Bitmap bitmap = BitmapFactory.decodeByteArray(usuario.getFoto(), 0, usuario.getFoto().length);
                bind.ivFotoPerfil.setImageBitmap(bitmap);
            }
        });

        bind.btGuardar.setOnClickListener(view -> {
            String documento = bind.etDocumento.getText().toString();
            String apellido = bind.etApellido.getText().toString();
            String nombre = bind.etNombre.getText().toString();
            String email = bind.etEmailRegistro.getText().toString();
            String password = bind.etPasswordRegistro.getText().toString();

            BitmapDrawable drawable = (BitmapDrawable) bind.ivFotoPerfil.getDrawable();
            Bitmap foto = drawable.getBitmap();

            vm.Guardar(documento, apellido, nombre, email, password, foto);

        });

        vm.Leer(getIntent());

        bind.btTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch(intent);
            }
        });

        vm.getUriMutable().observe(this, new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                bind.ivFotoPerfil.setImageURI(uri);
            }
        });

    }

    private void abrirGaleria(){


        intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


        arl=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                vm.recibirFoto(result);


            }
        });



    }
}