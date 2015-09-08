package com.labsis.cuandorindo.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.labsis.cuandorindo.Entidades.Examen;
import com.labsis.cuandorindo.Entidades.TipoExamen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creada por Fede on 07/09/2015
 */
public class ExamenDAO {

    private String tabla = "Examen";
    private String col_id = "id";
    private String col_nota = "nota";
    private String col_descripcion = "descripcion";
    private String col_prioridad = "prioridad";
    private String col_fechaRecordatorio = "fechaRecordatorio";
    private String col_fechaExamen = "fechaExamen";
    private String col_idTipoExamen = "idTipoExamen";
    private String col_idMateria = "idMateria";

    private HashMap<Integer, Examen> items = new HashMap<>();

    private static ExamenDAO instance;

    private Context context;

    public static ExamenDAO getInstance(Context context) {
        if (instance == null) {
            instance = new ExamenDAO();
            instance.context = context;
        }

        return instance;
    }

    public static String getSQLCreate() {
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

    private Examen crearObjeto(Cursor cursor) {
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
        TipoExamen tipoExamen = TipoExamenDAO.getInstance(context).leer(idTipoExamen);
        examen.setTipoExamen(tipoExamen);

        examen.setMateria(MateriaDAO.getInstance(context).leer(cursor.getInt(cursor.getColumnIndex(col_idMateria))));

        return examen;
    }

    /**
     * Lee todos los Examenes de la DB
     *
     * @return ArrayList con todos los Examenes
     */
    public ArrayList<Examen> leerTodo() {
        return leerTodo(null, null);
    }

    private ArrayList<Examen> leerTodo(String select, String[] args) {
        DBHelper db = DBHelper.getInstancia(context);

        ArrayList<Examen> examenes = new ArrayList<>();

        String[] col = {col_id};

        Cursor cursor = db.getReadableDatabase().query(tabla, col, select, args, null, null, null);
        while (cursor.moveToNext()) {
            int idExamen = cursor.getInt(0);
            Examen examen = items.get(idExamen);
            if (examen == null) {
                examen = leer(idExamen);
                items.put(examen.getId(), examen);
            }
            examenes.add(examen);
        }

        cursor.close();

        return examenes;
    }

    /**
     * Retorna un Examen
     *
     * @param id id del Examen a buscar
     * @return Examen encontrado, o null si no lo encuentra
     */
    public Examen leer(int id) {
        Examen examen = items.get(id);
        if (examen != null) {
            return examen;
        }

        DBHelper db = DBHelper.getInstancia(context);

        String select = col_id + " = ?";
        String[] args = {"" + id};

        Cursor cursor = db.getReadableDatabase().query(tabla, null, select, args, null, null, null);
        if (cursor.moveToNext()) {
            examen = crearObjeto(cursor);
            items.put(examen.getId(), examen);
        }

        cursor.close();

        return examen;
    }

    /**
     * Inserta un Examen en la DB
     *
     * @param examen Examen a insertar
     * @return ID con que se inserto
     */
    public int insertar(Examen examen) {
        DBHelper db = DBHelper.getInstancia(context);
        ContentValues cv = new ContentValues();
        cv.put(col_descripcion, examen.getDescripcion());

        if (examen.getFechaExamen() != null) {
            cv.put(col_fechaExamen, format.format(examen.getFechaExamen()));
        } else {
            cv.putNull(col_fechaExamen);
        }

        if (examen.getFechaRecordatorio() != null) {
            cv.put(col_fechaRecordatorio, format.format(examen.getFechaRecordatorio()));
        } else {
            cv.putNull(col_fechaRecordatorio);
        }

        cv.put(col_idTipoExamen, examen.getTipoExamen().getId());
        cv.put(col_idMateria, examen.getMateria().getId());
        cv.put(col_nota, examen.getNota());
        cv.put(col_prioridad, examen.getPrioridad());

        int id = (int) db.getWritableDatabase().insert(tabla, null, cv);
        if (id != -1) {
            examen.setId(id);
            items.put(id, examen);
        }
        return id;
    }
}
