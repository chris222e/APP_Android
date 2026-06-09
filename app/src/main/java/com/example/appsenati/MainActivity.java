package com.example.appsenati;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);

        // 🔥 AGREGAR TABS
        tabLayout.addTab(tabLayout.newTab().setText("Préstamo"));
        tabLayout.addTab(tabLayout.newTab().setText("Recepción"));
        tabLayout.addTab(tabLayout.newTab().setText("Historial"));
        tabLayout.addTab(tabLayout.newTab().setText("Configuración"));

        // 🔥 CARGAR PRIMER FRAGMENT (REPCIÓN COMO EN LA IMAGEN)
        cargarFragmento(new recepcion());
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        if (tab != null) tab.select();

        // 🔥 EVENTO DE CAMBIO
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Fragment fragment = null;

                switch (tab.getPosition()) {
                    case 0: fragment = new prestamo(); break;
                    case 1: fragment = new recepcion(); break;
                    case 2: fragment = new historial(); break;
                    case 3: fragment = new configuracion(); break;
                }

                if (fragment != null) {
                    cargarFragmento(fragment);
                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    // 🔥 MÉTODO PARA CAMBIAR FRAGMENTS
    private void cargarFragmento(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameContainer, fragment)
                .commit();
    }
}