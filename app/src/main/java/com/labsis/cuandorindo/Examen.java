package com.labsis.cuandorindo;

import java.util.Date;

/**
 * Created by Facu on 04/09/2015.
 */
public class Examen {

    private String descripcion;
    private int prioridad, id;
    private float nota;
    private Date fechaRecordatorio, fechaExamen;

    private Materia materia;
    private Tipo tipo;

    public String getTitulo(){return materia.getNombre()+" " + tipo.getNombre();};

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

    public Date getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }
}
