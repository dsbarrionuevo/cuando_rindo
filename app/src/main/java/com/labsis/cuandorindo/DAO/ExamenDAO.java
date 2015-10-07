package com.labsis.cuandorindo.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import com.labsis.cuandorindo.Entidades.Examen;

import java.text.SimpleDateFormat;

/**
 * Creada por Fede on 07/09/2015
 */
public class ExamenDAO extends IdentificableDAO<Examen> {

    private String col_nota = "nota";
    private String col_descripcion = "descripcion";
    private String col_prioridad = "prioridad";
    private String col_fechaRecordatorio = "fechaRecordatorio";
    private String col_fechaExamen = "fechaExamen";
    private String col_idTipoExamen = "idTipoExamen";
    private String col_idMateria = "idMateria";

    private static ExamenDAO instance;

    public ExamenDAO(String tabla) {
        super(tabla);
    }

    public static ExamenDAO getInstance() {
        if (instance == null) {
            instance = new ExamenDAO("Examen");
        }

        return instance;
    }

    @Override
    public String getSQLCreate() {
        return "CREATE TABLE Examen (\n" +
                "    id                INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    descripcion       TEXT,\n" +
                "    prioridad         INTEGER,\n" +
                "    nota              DECIMAL,\n" +
                "    fechaRecordatorio TEXT,\n" +
                "    fechaExamen       TEXT    NOT NULL,\n" +
                "    idTipoExamen      INTEGER REFERENCES TipoExamen (id) ON DELETE CASCADE,\n" +
                "    idMateria         INTEGER REFERENCES Materia (id) ON DELETE CASCADE\n" +
                ");\n";
    }

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    protected Examen crearObjeto(Cursor cursor) {
        Examen examen = new Examen();
        examen.setId(cursor.getInt(cursor.getColumnIndex(col_id)));
        examen.setNota(cursor.getFloat(cursor.getColumnIndex(col_nota)));
        examen.setDescripcion(cursor.getString(cursor.getColumnIndex(col_descripcion)));
        examen.setPrioridad(cursor.getInt(cursor.getColumnIndex(col_prioridad)));

        try {
            examen.setFechaRecordatorio(format.parse(cursor.getString(cursor.getColumnIndex(col_fechaRecordatorio))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            examen.setFechaExamen(format.parse(cursor.getString(cursor.getColumnIndex(col_fechaExamen))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int idTipoExamen = cursor.getInt(cursor.getColumnIndex(col_idTipoExamen));
        examen.setIdTipo(idTipoExamen);

        int idMateria = cursor.getInt(cursor.getColumnIndex(col_idMateria));
        examen.setIdMateria(idMateria);

        return examen;
    }

    @Override
    public ContentValues getContentValue(Examen item) {
        ContentValues cv = new ContentValues();
        cv.put(col_descripcion, item.getDescripcion());
        cv.put(col_prioridad, item.getPrioridad());
        cv.put(col_nota, item.getNota());

        if (item.getFechaExamen() != null) {
            cv.put(col_fechaExamen, format.format(item.getFechaExamen()));
        } else {
            cv.putNull(col_fechaExamen);
        }

        if (item.getFechaRecordatorio() != null) {
            cv.put(col_fechaRecordatorio, format.format(item.getFechaRecordatorio()));
        } else {
            cv.putNull(col_fechaRecordatorio);
        }
        cv.put(col_idMateria, item.getIdMateria());
        cv.put(col_idTipoExamen, item.getIdTipo());
        return cv;
    }
}
