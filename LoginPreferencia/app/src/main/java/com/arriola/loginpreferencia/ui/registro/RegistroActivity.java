package com.arriola.loginpreferencia.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.arriola.loginpreferencia.databinding.ActivityRegistroBinding;
import com.arriola.loginpreferencia.model.Usuario;
import com.arriola.loginpreferencia.request.ApiClient;

public class RegistroActivity extends AppCompatActivity {
    private ActivityRegistroBinding bind;
    private ViewModelRegistro vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityRegistroBinding.inflate(getLayoutInflater());

        setContentView(bind.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelRegistro.class);

        Intent intent = getIntent();
        if(intent != null){
            String email = intent.getStringExtra("email");
            if(email != null && !email.isEmpty()){
                Usuario usuario = ApiClient.Leer(getApplicationContext(), email);

                bind.etApellido.setText(usuario.getApellido());
                bind.etDocumento.setText(usuario.getDocumento());
                bind.etNombre.setText(usuario.getNombre());
                bind.etEmailRegistro.setText(usuario.getMail());
                bind.etPasswordRegistro.setText(usuario.getPassword());
            }
        }


        bind.btGuardar.setOnClickListener(view -> {
            String documento = bind.etDocumento.getText().toString();
            String apellido = bind.etApellido.getText().toString();
            String nombre = bind.etNombre.getText().toString();
            String email = bind.etEmailRegistro.getText().toString();
            String password = bind.etPasswordRegistro.getText().toString();

            vm.Guardar(documento, apellido, nombre, email, password);

        });

    }
}