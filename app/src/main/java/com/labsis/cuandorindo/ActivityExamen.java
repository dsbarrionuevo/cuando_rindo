package com.labsis.cuandorindo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

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
        btnMateria = (Button) findViewById(R.id.btnMateria);
        spnTipo = (Spinner) findViewById(R.id.spnTipo);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        btnFechaExamen = (Button) findViewById(R.id.btnFechaExamen);
        rtbPrioridad = (RatingBar) findViewById(R.id.rtbPrioridad);

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
