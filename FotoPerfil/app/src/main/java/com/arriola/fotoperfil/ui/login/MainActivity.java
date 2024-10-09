package com.arriola.fotoperfil.ui.login;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.arriola.fotoperfil.R;
import com.arriola.fotoperfil.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding bind;
    private ViewModelMain vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validar();
        bind = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(bind.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelMain.class);


        bind.btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = bind.etEmail.getText().toString();
                String password= bind.etPassword.getText().toString();
                vm.Entrar(mail, password);
            }
        });

        bind.btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.Registrar();
            }
        });
    }

    private void validar() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
            Toast.makeText(this, "PERMISOS CONSEDIDO", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "SIN PERMISOS", Toast.LENGTH_SHORT).show();
        }
    }
}