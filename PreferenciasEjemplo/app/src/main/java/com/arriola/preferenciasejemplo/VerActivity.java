package com.arriola.preferenciasejemplo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.arriola.preferenciasejemplo.databinding.ActivityMainBinding;
import com.arriola.preferenciasejemplo.databinding.ActivityVerBinding;

public class VerActivity extends AppCompatActivity {
    private ActivityVerBinding bind;
    private VerActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityVerBinding.inflate(getLayoutInflater());

        setContentView(bind.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(VerActivityViewModel.class);

        vm.getMColor().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                bind.tvMostrar.setTextColor(integer);
            }
        });

        vm.getMSize().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                bind.tvMostrar.setTextSize(integer);
            }
        });
        vm.leerDatos();
    }
}