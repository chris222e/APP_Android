package com.example.appsenati;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class prestamo extends Fragment {

    private EditText etSearch, etNombre, etMarca, etDescripcion;
    private Button btnBuscar, btnPrestar;

    public prestamo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_prestamo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSearch = view.findViewById(R.id.etSearchP);
        etNombre = view.findViewById(R.id.etNombreP);
        etMarca = view.findViewById(R.id.etMarcaP);
        etDescripcion = view.findViewById(R.id.etDescripcionP);
        btnBuscar = view.findViewById(R.id.btnBuscarP);
        btnPrestar = view.findViewById(R.id.btnPrestar);

        btnBuscar.setOnClickListener(v -> {
            String id = etSearch.getText().toString();
            if (id.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese un ID para buscar", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Buscando herramienta " + id, Toast.LENGTH_SHORT).show();
            }
        });

        btnPrestar.setOnClickListener(v -> Toast.makeText(getContext(), "Préstamo registrado correctamente", Toast.LENGTH_SHORT).show());
    }
}