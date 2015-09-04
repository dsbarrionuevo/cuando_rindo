package com.labsis.cuandorindo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;


public class ActivityPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        RecyclerView lstExamenes = (RecyclerView) findViewById(R.id.lstExamenes);
        lstExamenes.setItemAnimator(new DefaultItemAnimator());
        lstExamenes.setLayoutManager(new GridLayoutManager(this, 2));

        Materia algoritmos = new Materia();
        algoritmos.setNombre("Algoritmos y Estrcutura de Datos");

        Materia analisis = new Materia();
        analisis.setNombre("Analisis Matematico");

        Materia diseño = new Materia();
        diseño.setNombre("Diseño de Sistemas");


        Tipo tipo = new Tipo();
        tipo.setNombre("Examen");

        //Examen diseño
        Examen examen = new Examen();
        examen.setDescripcion("Es la muerte");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        examen.setFechaExamen(calendar.getTime());

        examen.setPrioridad(5);
        examen.setTipo(tipo);
        examen.setMateria(diseño);

        ArrayList<Examen> examens = new ArrayList<>();
        for (int i = 0; i<100; i++){
            examens.add(examen);
        }

        AdaptadorExamenes adaptadorExamenes = new AdaptadorExamenes();
        adaptadorExamenes.setExamenes(examens);

        lstExamenes.setAdapter(adaptadorExamenes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
