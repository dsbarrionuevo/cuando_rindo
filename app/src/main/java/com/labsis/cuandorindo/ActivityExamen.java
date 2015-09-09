package com.labsis.cuandorindo;

import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.labsis.cuandorindo.DAO.TipoExamenDAO;
import com.labsis.cuandorindo.Entidades.TipoExamen;

import java.util.ArrayList;

/**
 * Creada por santi_000 on 07/09/2015.
 */
public class ActivityExamen extends AppCompatActivity {

    Button btnMateria;
    Spinner spnTipo;
    EditText txtDescripcion;
    Button btnFechaExamen;
    RatingBar rtbPrioridad;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_examen);

        //Spinner Tipo Examen
        spnTipo = (Spinner) findViewById(R.id.spnTipo);
        final ArrayList<TipoExamen> tiposExamen = TipoExamenDAO.getInstance(this).leerTodo();

        String[] nombresTipoExamen = new String[tiposExamen.size()];
        for (int i = 0; i < tiposExamen.size(); i++) {
            nombresTipoExamen[i] = tiposExamen.get(i).getNombre();
        }

        SpinnerAdapter spinnerAdapte = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nombresTipoExamen);
        spnTipo.setAdapter(spinnerAdapte);

        spnTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ActivityExamen.this, "Click en: " + tiposExamen.get(position).getNombre(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        btnMateria = (Button) findViewById(R.id.btnMateria);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        btnFechaExamen = (Button) findViewById(R.id.btnFechaExamen);

        //RatingBar
        rtbPrioridad = (RatingBar) findViewById(R.id.rtbPrioridad);
        LayerDrawable stars = (LayerDrawable) rtbPrioridad.getProgressDrawable();
        DrawableCompat.setTint(stars.getDrawable(2), getResources().getColor(R.color.lime)); //Estrellas Activas
        DrawableCompat.setTint(stars.getDrawable(0), getResources().getColor(R.color.grey_500)); //Estrellas sin seleccionar
        DrawableCompat.setTint(stars.getDrawable(1), getResources().getColor(R.color.grey_500)); //Fondo estrellas seleccionadas a la mitad

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarExamen);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.toolbar_examen);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbarExamen_Recordatorio:
                        Toast.makeText(ActivityExamen.this, "Click en recordatorio", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });


    }
}
