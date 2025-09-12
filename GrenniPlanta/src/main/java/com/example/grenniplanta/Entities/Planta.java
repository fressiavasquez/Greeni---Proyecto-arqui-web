package com.example.grenniplanta.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Planta")

public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlanta;
    @Column (name="Planta", length = 100, nullable = false)
    private String planta;

    @Column(name="Usuario", length = 100, nullable = false)
    private String usuario;

    @Column (name="Especie", length = 100, nullable = false)
    private String especie;

    @Column (name="Diagnositco", length = 100, nullable = false)
    private String diagnositco;

    @Column (name="Nombre", length = 100, nullable = false)
    private String nombre;

    @Column (name="Imagen", length = 100, nullable = false)
    private String imagen;

    @Column (name="Fecha_reg", length = 100, nullable = false)
    private String fechaReg;

    public Planta() {}

    public int getId() {
        return idPlanta;
    }

    public void setId(int id) {
        this.idPlanta = id;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getDiagnositco() {
        return diagnositco;
    }

    public void setDiagnositco(String diagnositco) {
        this.diagnositco = diagnositco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(String fechaReg) {
        this.fechaReg = fechaReg;
    }
}
