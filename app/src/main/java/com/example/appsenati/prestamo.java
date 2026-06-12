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

public class prestamo extends Fragment {

    private static final String IP_SERVIDOR = "192.168.101.43";

    private EditText etNombre, etMarca, etDescripcion;
    private RadioGroup rgCondicion, rgTipo;
    private Button btnGuardar, btnTestWS;
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

        etNombre = view.findViewById(R.id.etNombreP);
        etMarca = view.findViewById(R.id.etMarcaP);
        etDescripcion = view.findViewById(R.id.etDescripcionP);
        rgCondicion = view.findViewById(R.id.rgCondicionP);
        rgTipo = view.findViewById(R.id.rgTipoP);
        btnGuardar = view.findViewById(R.id.btnGuardarP);
        btnTestWS = view.findViewById(R.id.btnTestWSP);

        requestQueue = Volley.newRequestQueue(requireContext());

        btnGuardar.setOnClickListener(v -> guardarHerramienta());

        btnTestWS.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Probando conexión con Web Service...", Toast.LENGTH_SHORT).show();
        });
    }

    private void guardarHerramienta() {
        String url = "http://" + IP_SERVIDOR + "/api_senati/guardar.php";

        JSONObject postData = new JSONObject();
        try {
            postData.put("nombre", etNombre.getText().toString());
            postData.put("marca", etMarca.getText().toString());
            postData.put("descripcion", etDescripcion.getText().toString());
            
            int selectedCondicionId = rgCondicion.getCheckedRadioButtonId();
            if (selectedCondicionId != -1) {
                RadioButton rb = getView().findViewById(selectedCondicionId);
                postData.put("condicion", rb.getText().toString());
            }

            int selectedTipoId = rgTipo.getCheckedRadioButtonId();
            if (selectedTipoId != -1) {
                RadioButton rb = getView().findViewById(selectedTipoId);
                postData.put("tipo", rb.getText().toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, postData,
                response -> Toast.makeText(getContext(), "Guardado correctamente", Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(getContext(), "Error al guardar", Toast.LENGTH_SHORT).show()
        );

        requestQueue.add(request);
    }
}