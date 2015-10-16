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

import com.labsis.cuandorindo.DAO.ExamenDAO;
import com.labsis.cuandorindo.DAO.MateriaDAO;
import com.labsis.cuandorindo.DAO.TipoExamenDAO;
import com.labsis.cuandorindo.Entidades.Examen;
import com.labsis.cuandorindo.Entidades.Materia;
import com.labsis.cuandorindo.Entidades.TipoExamen;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Creada por Santi Murua  el 07/09/2015.
 */
public class ActivityExamen extends AppCompatActivity {

    public static final int REQUESTCODE_PICKERMATERIA = 123;
    private static final String state_examen = "examen";
    View btnMateria;
    TextView lblMateria;
    TextView lblFechaExamen;
    Spinner spnTipo;
    EditText txtDescripcion;
    View btnFechaExamen;
    RatingBar rtbPrioridad;
    Toolbar toolbar;
    Examen examen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nuevo_examen);

        //Evitar que se muestre el teclado apenas arranca la activity
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Spinner Tipo Examen
        spnTipo = (Spinner) findViewById(R.id.spnTipo);
        final ArrayList<TipoExamen> tiposExamen = TipoExamenDAO.getInstance().leerTodo();

        String[] nombresTipoExamen = new String[tiposExamen.size()];
        for (int i = 0; i < tiposExamen.size(); i++) {
            nombresTipoExamen[i] = tiposExamen.get(i).getNombre();
        }

        SpinnerAdapter spinnerAdapte = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nombresTipoExamen);
        spnTipo.setAdapter(spinnerAdapte);

        spnTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                examen.setIdTipo(tiposExamen.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Materia
        btnMateria = findViewById(R.id.btnMateria);
        btnMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ActivityMateria.getIntentPicker(ActivityExamen.this);
                startActivityForResult(intent, REQUESTCODE_PICKERMATERIA);
            }
        });
        lblMateria = (TextView) findViewById(R.id.lblMateriaSeleccionada);

        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);

        lblFechaExamen = (TextView) findViewById(R.id.lblFechaSeleccionada);


        //Fecha
        btnFechaExamen = findViewById(R.id.btnFechaExamen);
        btnFechaExamen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                if (examen.getFechaExamen() != null) {
                    c.setTime(examen.getFechaExamen());
                }

                int año = c.get(Calendar.YEAR);
                int mes = c.get(Calendar.MONTH);
                int dia = c.get(Calendar.DAY_OF_MONTH);


                new DatePickerDialog(ActivityExamen.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.HOUR_OF_DAY, 9);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);

                        String fechaString = UtilesFechas.getInstance(ActivityExamen.this).dateTimeFormat(calendar.getTime());
                        lblFechaExamen.setText(fechaString);

                        examen.setFechaExamen(calendar.getTime());

                    }
                }, año, mes, dia).show();
            }
        });

        //RatingBar
        rtbPrioridad = (RatingBar) findViewById(R.id.rtbPrioridad);
        //LayerDrawable stars = (LayerDrawable) rtbPrioridad.getProgressDrawable();
        //DrawableCompat.setTint(stars.getDrawable(2), getResources().getColor(R.color.lime)); //Estrellas Activas
        //DrawableCompat.setTint(stars.getDrawable(0), getResources().getColor(R.color.grey_500)); //Estrellas sin seleccionar
        //DrawableCompat.setTint(stars.getDrawable(1), getResources().getColor(R.color.grey_500)); //Fondo estrellas seleccionadas a la mitad

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

                    case R.id.toolbarExamen_guardar:

                        examen.setDescripcion(txtDescripcion.getText().toString());
                        examen.setPrioridad((int) rtbPrioridad.getRating());

                        int id = ExamenDAO.getInstance().insertar(examen);
                        if (id != -1) {
                            finish();
                        }
                        break;
                }
                return false;
            }
        });

        //
        if (savedInstanceState == null) {
            examen = new Examen();
        } else {
            examen = savedInstanceState.getParcelable(state_examen);

            //Recupero la materia seleccionada
            Materia matera = examen.getMateria();
            if (matera != null) {
                lblMateria.setText(matera.getNombre());
            }

            //Recupero la fecha seleccionada
            if (examen.getFechaExamen() != null) {
                String fechaString = UtilesFechas.getInstance(ActivityExamen.this).dateTimeFormat(examen.getFechaExamen());
                lblFechaExamen.setText(fechaString);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUESTCODE_PICKERMATERIA) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                int idMateria = bundle.getInt(ActivityMateria.extra_materia);

                Materia materia = MateriaDAO.getInstance().leer(idMateria);

                examen.setIdMateria(materia.getId());
                lblMateria.setText(materia.getNombre());
            }
        }
    }

    private static final String state_examen = "examen";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(state_examen, examen);
    }
}
