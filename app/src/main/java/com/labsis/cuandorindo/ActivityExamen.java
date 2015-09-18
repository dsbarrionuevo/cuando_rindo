package com.labsis.cuandorindo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.labsis.cuandorindo.DAO.MateriaDAO;
import com.labsis.cuandorindo.DAO.TipoExamenDAO;
import com.labsis.cuandorindo.Entidades.Materia;
import com.labsis.cuandorindo.Entidades.TipoExamen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Creada por santi_000 on 07/09/2015.
 */
public class ActivityExamen extends AppCompatActivity{

    View btnMateria;
    TextView lblMateria;
    TextView lblFechaExamen;
    Spinner spnTipo;
    EditText txtDescripcion;
    View btnFechaExamen;
    RatingBar rtbPrioridad;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_examen);
        //Evitar que se muestre el teclado apenas arranca la activity
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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
        btnMateria = findViewById(R.id.btnMateria);
        btnMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityExamen.this, ActivityMateria.class);
                startActivityForResult(intent, 123);
            }
        });
        lblMateria = (TextView) findViewById(R.id.lblMateriaSeleccionada);

        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);

        lblFechaExamen= (TextView) findViewById(R.id.lblFechaSeleccionada);

        final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        btnFechaExamen = findViewById(R.id.btnFechaExamen);
        btnFechaExamen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                final Calendar c= Calendar.getInstance();
                final int año= c.get(Calendar.YEAR);
                final int mes= c.get(Calendar.MONTH);
                final int dia= c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(ActivityExamen.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        lblFechaExamen.setText(formatoFecha.format(new Date(year, monthOfYear,dayOfMonth, 9, 0)));
                    }
                }, año, mes, dia).show();
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==123){
            if(resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                int idMateria = bundle.getInt("idMateria");
                Materia materia = MateriaDAO.getInstance(this).leer(idMateria);
                lblMateria.setText(materia.getNombre());
            }
        }
    }
}
