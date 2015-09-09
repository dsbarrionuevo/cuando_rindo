package com.labsis.cuandorindo.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.labsis.cuandorindo.Entidades.Examen;
import com.labsis.cuandorindo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdaptadorExamenes extends RecyclerView.Adapter<AdaptadorExamenes.ExamenViewHolder> {

    ArrayList<Examen> items = new ArrayList<>();

    AppCompatActivity activity;
    public AdaptadorExamenes(AppCompatActivity activity){
        this.activity = activity;
    }
    /**
     * Cambio todos los items del adaptador
     *
     * @param items nuevos items a mostrar
     */
    public void setExamenes(ArrayList<Examen> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    /**
     * Agrega un Examen al adaptador, en la posicion 0
     *
     * @param examen Examen a insertar
     */
    public void agregar(Examen examen) {
        items.add(0, examen);
        notifyItemInserted(0);
    }

    @Override
    public ExamenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_examen_item, parent, false);
        return new ExamenViewHolder(view);
    }

    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    public void onBindViewHolder(ExamenViewHolder holder, int position) {
        Examen examen = items.get(position);

        int color = examen.getTipoExamen().getColor();
        //Si el tipo examen tiene color
        if (color != 0) {
            holder.viewColor.setBackgroundColor(color);
        }
        //Si no tiene color, va transparente
        else {
            holder.viewColor.setBackgroundColor(Color.TRANSPARENT);
        }
        holder.lblTipoExamen.setText(examen.getTipoExamen().getNombre());
        holder.lblMateria.setText(examen.getMateria().getNombre());
        holder.lblDescripcion.setText(examen.getDescripcion());
        holder.ratingBarPrioridad.setRating(examen.getPrioridad());
        holder.lblFecha.setText(formatoFecha.format(examen.getFechaExamen()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ExamenViewHolder extends RecyclerView.ViewHolder {
        CardView cardItem;
        TextView lblTipoExamen;
        TextView lblMateria;
        TextView lblDescripcion;
        RatingBar ratingBarPrioridad;
        TextView lblFecha;
        View viewColor;

        public ExamenViewHolder(View itemView) {
            super(itemView);
            cardItem = (CardView) itemView;
            lblTipoExamen = (TextView) itemView.findViewById(R.id.lblTipoParcial);
            lblMateria = (TextView) itemView.findViewById(R.id.lblMateria);
            lblDescripcion = (TextView) itemView.findViewById(R.id.lblDescripcion);
            ratingBarPrioridad = (RatingBar) itemView.findViewById(R.id.ratingBarPrioridad);
            lblFecha = (TextView) itemView.findViewById(R.id.lblFecha);
            viewColor = itemView.findViewById(R.id.color);

            int colorGrisOcuro = activity.getColor(R.color.grey_800);
            int colorGrisClaro = activity.getColor(R.color.grey_500);

            //Esto es para cambiar el color de las estrellitas
            LayerDrawable stars = (LayerDrawable) ratingBarPrioridad.getProgressDrawable();
            DrawableCompat.setTint(stars.getDrawable(2), colorGrisOcuro); //Estrellas Activas
            DrawableCompat.setTint(stars.getDrawable(0), colorGrisClaro); //Estrellas sin seleccionar
            DrawableCompat.setTint(stars.getDrawable(1), colorGrisClaro); //Fondo estrellas seleccionadas a la mitad

            //Al hacer click en una item, aca se tendria q mandar el evento
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
