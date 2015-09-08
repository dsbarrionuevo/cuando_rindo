package com.labsis.cuandorindo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.labsis.cuandorindo.Adaptadores.AdaptadorExamenes;
import com.labsis.cuandorindo.DAO.ExamenDAO;
import com.labsis.cuandorindo.DAO.MateriaDAO;
import com.labsis.cuandorindo.DAO.TipoExamenDAO;
import com.labsis.cuandorindo.Entidades.Examen;
import com.labsis.cuandorindo.Entidades.Materia;
import com.labsis.cuandorindo.Entidades.TipoExamen;

import java.util.Calendar;

public class ActivityPrincipal extends AppCompatActivity {

    private Preferencias preferencias;
    private String preferencias_nombre = "preferencias";
    private String preferencias_primerUso = "primerUso";

    private FloatingActionButton fab;
    private RecyclerView lstExamenes;
    private AdaptadorExamenes adaptadorExamenes;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Cargo las preferencias

        preferencias = new Preferencias(this, preferencias_nombre);

        //Si es primer uso
        if (preferencias.recuperar(preferencias_primerUso, true)) {

            //Cargo los datos en la DB
            inicializarDatosEnDB();

            //Marco que ya no es el primer uso
            preferencias.guardar(preferencias_primerUso, false);
        }

        //Lista
        lstExamenes = (RecyclerView) findViewById(R.id.lstExamenes);
        lstExamenes.setItemAnimator(new DefaultItemAnimator());
        lstExamenes.setLayoutManager(new LinearLayoutManager(this));

        //Adaptador
        adaptadorExamenes = new AdaptadorExamenes();
        adaptadorExamenes.setExamenes(ExamenDAO.getInstance(this).leerTodo());
        lstExamenes.setAdapter(adaptadorExamenes);

        //FAB
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPrincipal.this, ActivityExamen.class);
                startActivity(intent);
            }
        });

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarPrincipal);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityPrincipal.this, "No implementado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Este metodo solo se debe llamar una vez.
     * Inserta en la Base de datos las Materias y los TiposExamen con los que la aplicacion arrancaria
     */
    private void inicializarDatosEnDB() {
        //Materias
        Materia materia_Fisica = new Materia();
        materia_Fisica.setNombre("Física I");
        MateriaDAO.getInstance(this).insertar(materia_Fisica);

        Materia materia_Fisica2 = new Materia();
        materia_Fisica2.setNombre("Física II");
        MateriaDAO.getInstance(this).insertar(materia_Fisica2);

        Materia materia_AnalisisMatematico = new Materia();
        materia_AnalisisMatematico.setNombre("Análisis Matemático I");
        MateriaDAO.getInstance(this).insertar(materia_AnalisisMatematico);

        Materia materia_AnalisisMatematico2 = new Materia();
        materia_AnalisisMatematico2.setNombre("Análisis Matemático II");
        MateriaDAO.getInstance(this).insertar(materia_AnalisisMatematico2);

        //Tipos
        TipoExamen tipo_Parcial = new TipoExamen();
        tipo_Parcial.setNombre("Parcial");
        tipo_Parcial.setColor(getResources().getColor(R.color.blue));
        TipoExamenDAO.getInstance(this).insertar(tipo_Parcial);

        TipoExamen tipo_Final = new TipoExamen();
        tipo_Final.setNombre("Final");
        tipo_Final.setColor(getResources().getColor(R.color.red));
        TipoExamenDAO.getInstance(this).insertar(tipo_Final);

        TipoExamen tipo_TP = new TipoExamen();
        tipo_TP.setNombre("Trabajo práctico");
        tipo_TP.setColor(getResources().getColor(R.color.green));
        TipoExamenDAO.getInstance(this).insertar(tipo_TP);

        //Examenes de prueba
        Examen examen_Analisis = new Examen();
        examen_Analisis.setTipoExamen(tipo_TP);
        examen_Analisis.setMateria(materia_AnalisisMatematico);
        examen_Analisis.setPrioridad(3);
        examen_Analisis.setDescripcion("Derivadas parciales, integrales.");

        Calendar calendar = Calendar.getInstance();
        examen_Analisis.setFechaExamen(calendar.getTime());
        ExamenDAO.getInstance(this).insertar(examen_Analisis);

        Examen examen_Fisica = new Examen();
        examen_Fisica.setTipoExamen(tipo_Parcial);
        examen_Fisica.setMateria(materia_Fisica);
        examen_Fisica.setPrioridad(4);
        examen_Fisica.setDescripcion("Movimiento circular, momentum lineal, giróscopos.");

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, 10);
        calendar2.add(Calendar.MONTH, 1);
        examen_Fisica.setFechaExamen(calendar2.getTime());

        ExamenDAO.getInstance(this).insertar(examen_Fisica);
    }
}
