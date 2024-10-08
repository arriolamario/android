package com.arriola.loginfile.ui.registro;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.arriola.loginfile.R;
import com.arriola.loginfile.databinding.ActivityRegistroBinding;
import com.arriola.loginfile.model.Usuario;

public class RegistroActivity extends AppCompatActivity {
    private ActivityRegistroBinding bind;
    private ViewModelRegistro vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityRegistroBinding.inflate(getLayoutInflater());

        setContentView(bind.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelRegistro.class);

        vm.getUsuarioMutable().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                bind.etApellido.setText(usuario.getApellido());
                bind.etDocumento.setText(usuario.getDocumento());
                bind.etNombre.setText(usuario.getNombre());
                bind.etEmailRegistro.setText(usuario.getMail());
                bind.etPasswordRegistro.setText(usuario.getPassword());
            }
        });

        bind.btGuardar.setOnClickListener(view -> {
            String documento = bind.etDocumento.getText().toString();
            String apellido = bind.etApellido.getText().toString();
            String nombre = bind.etNombre.getText().toString();
            String email = bind.etEmailRegistro.getText().toString();
            String password = bind.etPasswordRegistro.getText().toString();

            vm.Guardar(documento, apellido, nombre, email, password);

        });

        vm.Leer(getIntent());

    }
}