package com.labsis.cuandorindo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.labsis.cuandorindo.DAO.ExamenDAO;
import com.labsis.cuandorindo.DAO.MateriaDAO;
import com.labsis.cuandorindo.DAO.TipoExamenDAO;
import com.labsis.cuandorindo.Entidades.Examen;
import com.labsis.cuandorindo.Entidades.Materia;
import com.labsis.cuandorindo.Entidades.TipoExamen;

import java.util.ArrayList;
import java.util.Calendar;


public class ActivityPrincipal extends AppCompatActivity {

    Preferencias preferencias;
    private String preferencias_nombre = "preferencias";
    private String preferencias_primerUso = "primerUso";
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);



        preferencias = new Preferencias(this, preferencias_nombre);
        if(preferencias.recuperar(preferencias_primerUso, true)){
            preferencias.guardar(preferencias_primerUso, false);

            //Materias
            Materia materiaFisica = new Materia();
            materiaFisica.setNombre("Física I");
            MateriaDAO.getInstance(this).insertar(materiaFisica);

            Materia materiaFisica2 = new Materia();
            materiaFisica2.setNombre("Física II");
            MateriaDAO.getInstance(this).insertar(materiaFisica2);

            Materia analisis = new Materia();
            analisis.setNombre("Análisis Matermático I");
            MateriaDAO.getInstance(this).insertar(analisis);

            Materia analisis2 = new Materia();
            analisis2.setNombre("Análisis Matermático II");
            MateriaDAO.getInstance(this).insertar(analisis2);

            //Tipos
            TipoExamen tipoParcial = new TipoExamen();
            tipoParcial.setNombre("Parcial");
            TipoExamenDAO.getInstance(this).insertar(tipoParcial);

            TipoExamen tipoFinal = new TipoExamen();
            tipoFinal.setNombre("Final");
            TipoExamenDAO.getInstance(this).insertar(tipoFinal);

            TipoExamen tipoTP = new TipoExamen();
            tipoTP.setNombre("Trabajo práctico");
            TipoExamenDAO.getInstance(this).insertar(tipoTP);

            Examen examen = new Examen();
            examen.setTipoExamen(tipoTP);
            examen.setMateria(analisis2);
            examen.setPrioridad(4);
            examen.setDescripcion("Llalalalala");

            Calendar calendar = Calendar.getInstance();
            examen.setFechaExamen(calendar.getTime());

            ExamenDAO.getInstance(this).insertar(examen);
        }


        RecyclerView lstExamenes = (RecyclerView) findViewById(R.id.lstExamenes);
        lstExamenes.setItemAnimator(new DefaultItemAnimator());
        lstExamenes.setLayoutManager(new LinearLayoutManager(this));

        AdaptadorExamenes adaptadorExamenes = new AdaptadorExamenes();
        adaptadorExamenes.setExamenes(ExamenDAO.getInstance(this).leerTodo());
        lstExamenes.setAdapter(adaptadorExamenes);


        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPrincipal.this, ActivityExamen.class);
                startActivity(intent);
            }
        });

    }
}
