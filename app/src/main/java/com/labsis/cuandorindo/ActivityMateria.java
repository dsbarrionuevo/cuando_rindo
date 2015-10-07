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
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.labsis.cuandorindo.Adaptadores.AdaptadorMateria;
import com.labsis.cuandorindo.DAO.MateriaDAO;
import com.labsis.cuandorindo.Entidades.Materia;

public class ActivityMateria extends AppCompatActivity {

    public static final String extra_isPicker = "isPicker";
    public static final String extra_materia = "materia";


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

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ActivityMateria.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(extra_isPicker, false);
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
        adaptadorMaterias.setMaterias(MateriaDAO.getInstance().leerTodo());
        lstMaterias.setAdapter(adaptadorMaterias);

        //Click a una materia
        adaptadorMaterias.setOnMateriaClickListener(new AdaptadorMateria.OnMateriaClickListener() {
            @Override
            public void onMateriaClick(Materia materia, int pos) {
                if(isPicker) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putInt(extra_materia, materia.getId());
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
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(ActivityMateria.this)
                        .autoDismiss(false)
                        .input("Materia...", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog materialDialog, CharSequence charSequence) {
                                String cadena = charSequence.toString();
                                if(cadena.length()==0){
                                    Toast.makeText(ActivityMateria.this, "Ingrese algo", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                Materia materia = new Materia();
                                materia.setNombre(cadena);
                                MateriaDAO.getInstance().insertar(materia);

                                adaptadorMaterias.agregar(materia);

                                materialDialog.dismiss();
                            }
                        }).show();
            }
        });
        //Si es picker lo oculto
        if (isPicker) {
            floatingActionButton.setVisibility(View.GONE);
        }
    }
}
