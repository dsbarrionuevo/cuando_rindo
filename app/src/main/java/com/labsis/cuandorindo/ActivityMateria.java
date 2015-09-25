package com.labsis.cuandorindo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.labsis.cuandorindo.Adaptadores.AdaptadorMateria;
import com.labsis.cuandorindo.DAO.MateriaDAO;
import com.labsis.cuandorindo.Entidades.Materia;

public class ActivityMateria extends AppCompatActivity {

    private static final String extra_isPicker = "isPicker";

    private RecyclerView lstMaterias;
    private AdaptadorMateria adaptadorMaterias;
    private Toolbar toolbar;

    private boolean isPicker = false;

    public static Intent getIntentPicker(Context context) {
        Intent intent = new Intent(context, ActivityMateria.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(extra_isPicker, true);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);

        //Veo si es picker o no
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            isPicker = extras.getBoolean(extra_isPicker);
        }

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
                if(isPicker) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putInt("idMateria", materia.getId());
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
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

        //FAB
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        //Si es picker lo oculto
        if (isPicker) {
            floatingActionButton.setVisibility(View.GONE);
        }
    }
}
