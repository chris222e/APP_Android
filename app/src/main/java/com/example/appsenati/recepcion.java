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

import org.json.JSONException;
import org.json.JSONObject;

public class recepcion extends Fragment {

    private EditText etSearch, etNombre, etMarca, etDescripcion;
    private RadioGroup rgCondicion, rgTipo;
    private RadioButton rbBueno, rbRegular, rbMalo, rbElectrica, rbManual;
    private Button btnBuscar, btnActualizar;
    private RequestQueue requestQueue;

    public recepcion() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recepcion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar vistas
        etSearch = view.findViewById(R.id.etSearch);
        etNombre = view.findViewById(R.id.etNombre);
        etMarca = view.findViewById(R.id.etMarca);
        etDescripcion = view.findViewById(R.id.etDescripcion);
        rgCondicion = view.findViewById(R.id.rgCondicion);
        rgTipo = view.findViewById(R.id.rgTipo);
        rbBueno = view.findViewById(R.id.rbBueno);
        rbRegular = view.findViewById(R.id.rbRegular);
        rbMalo = view.findViewById(R.id.rbMalo);
        rbElectrica = view.findViewById(R.id.rbElectrica);
        rbManual = view.findViewById(R.id.rbManual);
        btnBuscar = view.findViewById(R.id.btnBuscar);
        btnActualizar = view.findViewById(R.id.btnActualizar);

        requestQueue = Volley.newRequestQueue(requireContext());

        btnBuscar.setOnClickListener(v -> buscarHerramienta());
        btnActualizar.setOnClickListener(v -> actualizarHerramienta());
    }

    private void buscarHerramienta() {
        String id = etSearch.getText().toString().trim();
        if (id.isEmpty()) {
            Toast.makeText(requireContext(), "Ingrese un ID", Toast.LENGTH_SHORT).show();
            return;
        }

        // Si usas emulador: 10.0.2.2
        // Si usas celular físico: pon la IP de tu PC (ej: 192.168.1.10)
        String ip = "10.0.2.2"; 
        String url = "http://" + ip + "/api_senati/buscar.php?id=" + id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    if (!isAdded()) return;
                    try {
                        etNombre.setText(response.optString("nombre", ""));
                        etMarca.setText(response.optString("marca", ""));
                        etDescripcion.setText(response.optString("descripcion", ""));

                        String condicion = response.optString("condicion", "");
                        if (condicion.equalsIgnoreCase("Bueno")) rbBueno.setChecked(true);
                        else if (condicion.equalsIgnoreCase("Regular")) rbRegular.setChecked(true);
                        else if (condicion.equalsIgnoreCase("Malo")) rbMalo.setChecked(true);

                        String tipo = response.optString("tipo", "");
                        if (tipo.equalsIgnoreCase("Eléctrica") || tipo.equalsIgnoreCase("Electrica")) rbElectrica.setChecked(true);
                        else if (tipo.equalsIgnoreCase("Manual")) rbManual.setChecked(true);

                        Toast.makeText(requireContext(), "Herramienta encontrada", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(requireContext(), "Error al procesar los datos del servidor", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    if (!isAdded()) return;
                    String message = "Error de conexión";
                    if (error.networkResponse != null) {
                        message = "Error del servidor: " + error.networkResponse.statusCode;
                    } else if (error instanceof com.android.volley.NoConnectionError) {
                        message = "No hay conexión. ¿Está encendido XAMPP?";
                    } else if (error instanceof com.android.volley.TimeoutError) {
                        message = "Tiempo de espera agotado";
                    }
                    Toast.makeText(requireContext(), message + " (" + ip + ")", Toast.LENGTH_LONG).show();
                }
        );

        requestQueue.add(request);
    }

    private void actualizarHerramienta() {
        String id = etSearch.getText().toString().trim();
        if (id.isEmpty()) {
            Toast.makeText(requireContext(), "Busque una herramienta primero", Toast.LENGTH_SHORT).show();
            return;
        }

        String ip = "10.0.2.2";
        String url = "http://" + ip + "/api_senati/actualizar.php";

        JSONObject postData = new JSONObject();
        try {
            postData.put("id", id);
            postData.put("nombre", etNombre.getText().toString());
            postData.put("marca", etMarca.getText().toString());
            postData.put("descripcion", etDescripcion.getText().toString());
            
            int selectedCondicionId = rgCondicion.getCheckedRadioButtonId();
            if (selectedCondicionId != -1) {
                RadioButton selectedCondicion = requireView().findViewById(selectedCondicionId);
                postData.put("condicion", selectedCondicion.getText().toString());
            }

            int selectedTipoId = rgTipo.getCheckedRadioButtonId();
            if (selectedTipoId != -1) {
                RadioButton selectedTipo = requireView().findViewById(selectedTipoId);
                postData.put("tipo", selectedTipo.getText().toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, postData,
                response -> {
                    if (!isAdded()) return;
                    Toast.makeText(requireContext(), "Actualizado correctamente", Toast.LENGTH_SHORT).show();
                },
                error -> {
                    if (!isAdded()) return;
                    // Intentamos parsear el error si es un error de JSON pero la operación se hizo
                    Toast.makeText(requireContext(), "Error al actualizar (Verifique conexión)", Toast.LENGTH_SHORT).show();
                }
        );

        requestQueue.add(request);
    }
}