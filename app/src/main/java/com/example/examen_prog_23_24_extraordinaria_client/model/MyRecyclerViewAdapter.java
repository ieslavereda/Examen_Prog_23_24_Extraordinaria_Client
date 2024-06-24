package com.example.examen_prog_23_24_extraordinaria_client.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen_prog_23_24_extraordinaria_client.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


    private List<Linea> values;
    private LayoutInflater inflater;
    private Context context;

    public MyRecyclerViewAdapter(@NonNull Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        values = new ArrayList<>();
    }

    public void insertValue(Linea linea) {
        values.add(0, linea);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_element, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Linea linea = values.get(position);

        holder.tvIncremento.setText("" + linea.variacion);
        holder.tvNombre.setText(linea.nombre);
        holder.tvSaldo.setText("" + linea.saldo);

        holder.updateBackground(context.getResources().getColor((position % 2 == 0) ? R.color.recycler_even : R.color.recycler_odd, context.getTheme()));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public static class Linea {

        private String nombre;
        private float variacion;
        private float saldo;

        public Linea(String nombre, float variacion, float saldo) {
            this.nombre = nombre;
            this.variacion = variacion;
            this.saldo = saldo;
        }

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvIncremento;
        TextView tvNombre;
        TextView tvSaldo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvIncremento = itemView.findViewById(R.id.tvIncremento);
            tvSaldo = itemView.findViewById(R.id.tvSaldo);
        }

        public void updateBackground(int color) {
            itemView.setBackgroundColor(color);
        }
    }
}
