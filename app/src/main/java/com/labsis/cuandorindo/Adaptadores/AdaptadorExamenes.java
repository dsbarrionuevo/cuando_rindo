package com.labsis.cuandorindo.Adaptadores;

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
    ArrayList<Examen> todosLosItems = new ArrayList<>();

    String filtro = "";

    AppCompatActivity activity;

    public AdaptadorExamenes(AppCompatActivity activity) {
        this.activity = activity;
        setHasStableIds(true);
    }


    /**
     * Cambio todos los items del adaptador
     *
     * @param items nuevos items a mostrar
     */
    public void setExamenes(ArrayList<Examen> items) {
        this.todosLosItems = items;

        this.items.clear();
        for (Examen examen : items) {
            if (validar(examen)) {
                this.items.add(examen);
            }
        }

        notifyDataSetChanged();
    }

    /**
     * Agrega un Examen al adaptador, en la posicion 0
     *
     * @param examen Examen a insertar
     */
    public void agregar(Examen examen) {
        todosLosItems.add(0, examen);

        if (validar(examen)) {
            items.add(0, examen);
            notifyItemInserted(0);
        }
    }

    public void quitar(Examen examen) {
        todosLosItems.remove(examen);

        if (items.contains(examen)) {
            int posicion = items.indexOf(examen);
            items.remove(posicion);
            notifyItemRemoved(posicion);
        }
    }

    public boolean validar(Examen examen) {
        if (examen.getMateria().getNombre().toLowerCase().contains(filtro.toLowerCase())) {
            return true;
        }

        if (examen.getDescripcion() != null && examen.getDescripcion().toLowerCase().contains(filtro.toLowerCase())) {
            return true;
        }

        return false;
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

    public void setFIltro(String filtro) {
        this.filtro = filtro;
        setExamenes(todosLosItems);
    }

    public class ExamenViewHolder extends RecyclerView.ViewHolder {
        CardView cardItem;
        TextView lblTipoExamen;
        TextView lblMateria;
        TextView lblDescripcion;
        RatingBar ratingBarPrioridad;
        TextView lblFecha;
        View viewColor;

        public ExamenViewHolder(final View itemView) {
            super(itemView);
            cardItem = (CardView) itemView;
            lblTipoExamen = (TextView) itemView.findViewById(R.id.lblTipoParcial);
            lblMateria = (TextView) itemView.findViewById(R.id.lblMateria);
            lblDescripcion = (TextView) itemView.findViewById(R.id.lblDescripcion);
            ratingBarPrioridad = (RatingBar) itemView.findViewById(R.id.ratingBarPrioridad);
            lblFecha = (TextView) itemView.findViewById(R.id.lblFecha);
            viewColor = itemView.findViewById(R.id.color);

            int colorGrisOcuro = activity.getResources().getColor(R.color.grey_800);
            int colorGrisClaro = activity.getResources().getColor(R.color.grey_500);

//            Esto es para cambiar el color de las estrellitas
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

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listenerExamenLongClick != null) {
                        int pos = getAdapterPosition();
                        Examen examen = items.get(pos);
                        listenerExamenLongClick.onExamenLongClick(examen, pos);
                    }
                    return false;
                }
            });
        }
    }

    private OnExamenLongClick listenerExamenLongClick;

    public interface OnExamenLongClick {
        public void onExamenLongClick(Examen examen, int pos);
    }

    public void setOnExamenLongClick(OnExamenLongClick listenerExamenLongClick) {
        this.listenerExamenLongClick = listenerExamenLongClick;
    }

}
