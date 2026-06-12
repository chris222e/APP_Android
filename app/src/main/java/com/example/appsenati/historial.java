package com.example.appsenati;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class historial extends Fragment {

    RecyclerView recyclerHerramientas;
    List<Herramienta> listaHerramientas;
    HerramientaAdapter adapter;
    RequestQueue requestQueue;
    private final String IP = "192.168.101.43";
    private final String URL_GET = "http://" + IP + "/api_senati/historial.php";
    private final String URL_DELETE = "http://" + IP + "/api_senati/eliminar.php?id=";

    public historial() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        recyclerHerramientas = view.findViewById(R.id.recyclerHerramientas);
        recyclerHerramientas.setLayoutManager(new LinearLayoutManager(getContext()));
        
        listaHerramientas = new ArrayList<>();
        
        // Implementamos la interfaz para eliminar
        adapter = new HerramientaAdapter(listaHerramientas, (id, position) -> {
            eliminarDato(id, position);
        });
        
        recyclerHerramientas.setAdapter(adapter);
        requestQueue = Volley.newRequestQueue(requireContext());

        obtenerDatos();
    }

    private void obtenerDatos() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_GET, null,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            JSONArray objetos = response.getJSONArray("data");
                            listaHerramientas.clear();

                            for (int i = 0; i < objetos.length(); i++) {
                                JSONObject obj = objetos.getJSONObject(i);
                                Log.d("Resultado", obj.getString("descripcion"));

                                Herramienta herramienta = new Herramienta();
                                herramienta.setId(obj.optString("idherramienta", ""));
                                herramienta.setNombre(obj.optString("nombre", ""));
                                herramienta.setMarca(obj.optString("marca", ""));
                                herramienta.setDescripcion(obj.optString("descripcion", ""));
                                herramienta.setCondicion(obj.optString("condicion", ""));
                                herramienta.setTipo(obj.optString("tipo", ""));
                                
                                listaHerramientas.add(herramienta);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        Log.e("ErrorJSON", "No podemos leer JSON");
                    }
                },
                error -> Log.e("ErrorVolley", error.toString())
        );
        requestQueue.add(request);
    }

    private void eliminarDato(String id, int position) {
        // Aseguramos que la URL esté bien formada
        String urlFinal = "http://" + IP + "/api_senati/eliminar.php?id=" + id;
        
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlFinal, null,
                response -> {
                    try {
                        if (response.getBoolean("success")) {
                            listaHerramientas.remove(position);
                            adapter.notifyItemRemoved(position);
                            Toast.makeText(getContext(), "Eliminado con éxito", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "No se pudo eliminar", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
        );
        requestQueue.add(request);
    }
}