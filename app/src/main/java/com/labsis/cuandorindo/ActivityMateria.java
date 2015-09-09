package com.labsis.cuandorindo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.labsis.cuandorindo.Adaptadores.AdaptadorExamenes;
import com.labsis.cuandorindo.Adaptadores.AdaptadorMateria;
import com.labsis.cuandorindo.DAO.ExamenDAO;
import com.labsis.cuandorindo.DAO.MateriaDAO;
import com.labsis.cuandorindo.Entidades.Materia;

public class ActivityMateria extends AppCompatActivity {

    private RecyclerView lstMaterias;
    private AdaptadorMateria adaptadorMaterias;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);

        //Lista
        lstMaterias = (RecyclerView) findViewById(R.id.lstMaterias);
        lstMaterias.setItemAnimator(new DefaultItemAnimator());
        lstMaterias.setLayoutManager(new LinearLayoutManager(this));

        //Adaptador
        adaptadorMaterias = new AdaptadorMateria(this);
        adaptadorMaterias.setMaterias(MateriaDAO.getInstance(this).leerTodo());
        lstMaterias.setAdapter(adaptadorMaterias);

        //Click a una materia
        adaptadorMaterias.setOnMateriaClickListener(new AdaptadorMateria.OnMateriaClickListener() {
            @Override
            public void onMateriaClick(Materia materia, int pos) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("idMateria", materia.getId());
                intent.putExtras(bundle);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarMaterias);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
