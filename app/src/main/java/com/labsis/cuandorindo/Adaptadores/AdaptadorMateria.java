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
import android.widget.Toast;

import com.labsis.cuandorindo.Entidades.Examen;
import com.labsis.cuandorindo.Entidades.Materia;
import com.labsis.cuandorindo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdaptadorMateria extends RecyclerView.Adapter<AdaptadorMateria.MateriaViewHolder> {

    ArrayList<Materia> items = new ArrayList<>();

    AppCompatActivity activity;

    public AdaptadorMateria(AppCompatActivity activity) {
        this.activity = activity;
    }

    /**
     * Cambio todos los items del adaptador
     *
     * @param items nuevos items a mostrar
     */
    public void setMaterias(ArrayList<Materia> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    /**
     * Agrega un Materia al adaptador, en la posicion 0
     *
     * @param materia Materia a insertar
     */
    public void agregar(Materia materia) {
        items.add(0, materia);
        notifyItemInserted(0);
    }

    @Override
    public MateriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_materia_item, parent, false);
        return new MateriaViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MateriaViewHolder holder, int position) {
        Materia materia = items.get(position);

        holder.lblMateria.setText(materia.getNombre());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MateriaViewHolder extends RecyclerView.ViewHolder {

        TextView lblMateria;

        public MateriaViewHolder(View itemView) {
            super(itemView);

            lblMateria = (TextView) itemView.findViewById(R.id.lblMateria);

            //Al hacer click en una item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    //Si aguien se suscribio al evento...
                    if (listenerMateriaClick != null) {
                        Materia materia = items.get(pos);

                        //Mando el evento
                        listenerMateriaClick.onMateriaClick(materia, pos);
                    }
                }
            });
        }
    }

    //
    private OnMateriaClickListener listenerMateriaClick;

    public void setOnMateriaClickListener(OnMateriaClickListener listenerMateriaClick) {
        this.listenerMateriaClick = listenerMateriaClick;
    }

    public interface OnMateriaClickListener {
        public void onMateriaClick(Materia materia, int pos);
    }
}
