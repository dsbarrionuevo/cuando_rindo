package com.labsis.cuandorindo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.labsis.cuandorindo.Entidades.Examen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdaptadorExamenes extends RecyclerView.Adapter<AdaptadorExamenes.ExamenViewHolder> {

    ArrayList<Examen> items = new ArrayList<>();

    public void setExamenes(ArrayList items) {
        this.items = items;
    }

    public void agregar(Examen examen) {
        items.add(0, examen);
        notifyItemInserted(0);
    }

    @Override
    public ExamenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_examen_item, parent, false);
        return new ExamenViewHolder(view);
    }

    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void onBindViewHolder(ExamenViewHolder holder, int position) {
        Examen examen = items.get(position);
        holder.lblTitulo.setText(examen.getTitulo());
        holder.lblDescripcion.setText(examen.getDescripcion());
        holder.ratingBarPrioridad.setRating(examen.getPrioridad());
        Log.i("", "" + examen.getPrioridad());
        holder.lblFecha.setText(formatoFecha.format(examen.getFechaExamen()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ExamenViewHolder extends RecyclerView.ViewHolder {
        TextView lblTitulo;
        TextView lblDescripcion;
        RatingBar ratingBarPrioridad;
        TextView lblFecha;

        public ExamenViewHolder(View itemView) {
            super(itemView);
            lblTitulo = (TextView) itemView.findViewById(R.id.lblTitulo);
            lblDescripcion = (TextView) itemView.findViewById(R.id.lblDescripcion);
            ratingBarPrioridad = (RatingBar) itemView.findViewById(R.id.ratingBarPrioridad);
            lblFecha = (TextView) itemView.findViewById(R.id.lblFecha);
        }
    }
}
