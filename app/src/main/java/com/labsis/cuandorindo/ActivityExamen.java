package com.labsis.cuandorindo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

/**
 * Created by santi_000 on 07/09/2015.
 */
public class ActivityExamen extends AppCompatActivity {

    Button btnMateria;
    Spinner spnTipo;
    EditText txtDescripcion;
    Button btnFechaExamen;
    RatingBar rtbPrioridad;
    Button btnFechaRecordatorio;
    EditText txtNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_examen);
        btnMateria = (Button) findViewById(R.id.btnMateria);
        spnTipo = (Spinner) findViewById(R.id.spnTipo);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        btnFechaExamen = (Button) findViewById(R.id.btnFechaExamen);
        rtbPrioridad = (RatingBar) findViewById(R.id.rtbPrioridad);
        btnFechaRecordatorio = (Button) findViewById(R.id.btnFechaRecordatorio);
        txtNota = (EditText) findViewById(R.id.txtNota);


    }
}
