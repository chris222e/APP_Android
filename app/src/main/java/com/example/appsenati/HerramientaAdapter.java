package com.example.appsenati;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HerramientaAdapter extends RecyclerView.Adapter<HerramientaAdapter.ViewHolder> {

    private List<Herramienta> listaHerramientas;
    private OnItemDeleteListener listener;

    public interface OnItemDeleteListener {
        void onItemDelete(String id, int position);
    }

    public HerramientaAdapter(List<Herramienta> listaHerramientas, OnItemDeleteListener listener) {
        this.listaHerramientas = listaHerramientas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_herramienta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Herramienta herramienta = listaHerramientas.get(position);
        holder.tvNombre.setText(herramienta.getNombre());
        holder.tvDescripcion.setText(herramienta.getDescripcion());
        
        holder.btnEliminar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemDelete(herramienta.getId(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaHerramientas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDescripcion;
        Button btnEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreItem);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionItem);
            btnEliminar = itemView.findViewById(R.id.btnEliminarItem);
        }
    }
}