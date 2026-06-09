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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class prestamo extends Fragment {

    // ⚠️ CAMBIA ESTA IP: 10.0.2.2 para emulador, o la IP de tu PC para celular real
    private static final String IP_SERVIDOR = "192.168.56.1";

    private EditText etSearch, etNombre, etMarca, etDescripcion;
    private RadioGroup rgCondicion;
    private RadioButton rbBueno, rbRegular, rbMalo;
    private Button btnBuscar, btnPrestar;
    private RequestQueue requestQueue;

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
        rgCondicion = view.findViewById(R.id.rgCondicionP);
        rbBueno = view.findViewById(R.id.rbBuenoP);
        rbRegular = view.findViewById(R.id.rbRegularP);
        rbMalo = view.findViewById(R.id.rbMaloP);
        btnBuscar = view.findViewById(R.id.btnBuscarP);
        btnPrestar = view.findViewById(R.id.btnPrestar);

        requestQueue = Volley.newRequestQueue(requireContext());

        btnBuscar.setOnClickListener(v -> buscarHerramienta());

        btnPrestar.setOnClickListener(v -> {
            String id = etSearch.getText().toString().trim();
            if (id.isEmpty()) {
                Toast.makeText(getContext(), "Busque una herramienta primero", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Préstamo realizado con éxito", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buscarHerramienta() {
        String id = etSearch.getText().toString().trim();
        if (id.isEmpty()) {
            Toast.makeText(requireContext(), "Ingrese un ID", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(requireContext(), "Buscando...", Toast.LENGTH_SHORT).show();
        String url = "http://" + IP_SERVIDOR + "/api_senati/buscar.php?id=" + id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    if (!isAdded()) return;
                    etNombre.setText(response.optString("nombre", ""));
                    etMarca.setText(response.optString("marca", ""));
                    etDescripcion.setText(response.optString("descripcion", ""));

                    String condicion = response.optString("condicion", "");
                    if (condicion.equalsIgnoreCase("Bueno")) rbBueno.setChecked(true);
                    else if (condicion.equalsIgnoreCase("Regular")) rbRegular.setChecked(true);
                    else if (condicion.equalsIgnoreCase("Malo")) rbMalo.setChecked(true);

                    Toast.makeText(requireContext(), "Herramienta cargada", Toast.LENGTH_SHORT).show();
                },
                error -> {
                    if (!isAdded()) return;
                    Toast.makeText(requireContext(), "Error: Verifique conexión en " + IP_SERVIDOR, Toast.LENGTH_LONG).show();
                }
        );

        requestQueue.add(request);
    }
}