package com.labsis.cuandorindo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.labsis.cuandorindo.Adaptadores.AdaptadorExamenes;
import com.labsis.cuandorindo.DAO.ExamenDAO;
import com.labsis.cuandorindo.Entidades.Examen;

public class ActivityPrincipal extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView lstExamenes;
    private AdaptadorExamenes adaptadorExamenes;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

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
        adaptadorExamenes.setExamenes(ExamenDAO.getInstance().leerTodo());

        adaptadorExamenes.setOnExamenLongClick(new AdaptadorExamenes.OnExamenLongClick() {
            @Override
            public void onExamenLongClick(final Examen examen, final int pos) {
                new MaterialDialog.Builder(ActivityPrincipal.this)
                        .content("¿Desea borrar el examen seleccionado?")
                        .positiveText("Si")
                        .negativeText("No")
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);
                                if (ExamenDAO.getInstance().borrar(examen.getId()) == 1) {
                                    adaptadorExamenes.quitar(pos);
                                } else {
                                    Toast.makeText(ActivityPrincipal.this, "Error al borrar el examen", Toast.LENGTH_LONG).show();
                                }
                            }
                        }).show();
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
                drawerLayout.openDrawer(Gravity.START);
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

        //Drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.drawer_principal:

                        break;
                    case R.id.drawer_materias:
                        Intent intent = ActivityMateria.getIntent(ActivityPrincipal.this);
                        startActivity(intent);
                        break;
                }

                drawerLayout.closeDrawers();
                return false;
            }
        });

    }
}
