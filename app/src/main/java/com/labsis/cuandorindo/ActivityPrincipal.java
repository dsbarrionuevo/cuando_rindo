package com.labsis.cuandorindo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

    private FloatingActionButton fab;
    private RecyclerView lstExamenes;
    private AdaptadorExamenes adaptadorExamenes;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Lista
        lstExamenes = (RecyclerView) findViewById(R.id.lstExamenes);
        lstExamenes.setItemAnimator(new DefaultItemAnimator());
        lstExamenes.setLayoutManager(new LinearLayoutManager(this));

        //Adaptador
        adaptadorExamenes = new AdaptadorExamenes(this);
        adaptadorExamenes.setExamenes(ExamenDAO.getInstance(this).leerTodo());
        adaptadorExamenes.setOnExamenLongClick(new AdaptadorExamenes.OnExamenLongClick() {
            @Override
            public void onExamenLongClick(Examen examen, int pos) {
                adaptadorExamenes.quitar(pos);
            }
        });

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
        toolbar.inflateMenu(R.menu.toolbar_principal);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbarPrincipal_Buscar:
                        Toast.makeText(ActivityPrincipal.this, "Buscar", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
    }
}
