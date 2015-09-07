package com.labsis.cuandorindo.Entidades;

import java.util.Date;

/**
 * Created by Facu on 04/09/2015.
 */
public class Examen {

    private int id;
    private String descripcion;
    private int prioridad;
    private float nota;
    private Date fechaRecordatorio;
    private Date fechaExamen;

    private Materia materia;
    private TipoExamen tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return tipo.getNombre() + ": " + materia.getNombre();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Date getFechaRecordatorio() {
        return fechaRecordatorio;
    }

    public void setFechaRecordatorio(Date fechaRecordatorio) {
        this.fechaRecordatorio = fechaRecordatorio;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public TipoExamen getTipoExamen() {
        return tipo;
    }

    public void setTipoExamen(TipoExamen tipo) {
        this.tipo = tipo;
    }

    public Date getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }
}
