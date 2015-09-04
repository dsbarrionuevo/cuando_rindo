package com.labsis.cuandorindo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Facu on 04/09/2015.
 */
public class AdaptadorListaExamenes extends RecyclerView.Adapter<AdaptadorListaExamenes.ExamenesViewHolder> {

    private ArrayList<Examen> examenes;
    public AdaptadorListaExamenes(ArrayList<Examen> examenes){
        this.examenes=examenes;
    }

    @Override
    public ExamenesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_examen_item, viewGroup, false);
        ExamenesViewHolder examenesViewHolder = new ExamenesViewHolder(itemView);
        return null;
    }

    @Override
    public void onBindViewHolder(ExamenesViewHolder examenesViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ExamenesViewHolder extends RecyclerView.ViewHolder{


        TextView lblTitulo;
        TextView lblDescripcion;
        TextView lblPrioridad;
        TextView lblFecha;


        public ExamenesViewHolder(View itemView) {
            super(itemView);

            lblTitulo= (TextView) itemView.findViewById(R.id.lblTitulo);
            lblDescripcion = (TextView) itemView.findViewById(R.id.lblDescripcion);
            lblFecha= (TextView) itemView.findViewById(R.id.lblFecha);
            lblPrioridad= (TextView) itemView.findViewById(R.id.lblPrioridad);



        }
    }
}
