package com.arriola.loginpreferencia.ui.login;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.arriola.loginpreferencia.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding bind;
    private ViewModelMain vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
}