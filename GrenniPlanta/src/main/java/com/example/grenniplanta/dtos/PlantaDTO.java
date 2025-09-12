package com.example.grenniplanta.dtos;

public class PlantaDTO {
    private int id;
    private String Planta;
    private String Usuario;
    private String Especie;
    private String Diagnostico;
    private String Nombre;
    private String Imagen;
    private String Fecha_reg;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPlanta() {
        return Planta;
    }
    public void setPlanta(String Planta) {
        this.Planta = Planta;
    }
    public String getUsuario() {
        return Usuario;
    }
    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    public String getEspecie() {
        return Especie;
    }
    public void setEspecie(String Especie) {
        this.Especie = Especie;
    }
    public String getDiagnostico() {
        return Diagnostico;
    }
    public void setDiagnostico(String Diagnostico) {
        this.Diagnostico = Diagnostico;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public String getImagen() {
        return Imagen;
    }
    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
    public String getFecha_reg() {
        return Fecha_reg;
    }
    public void setFecha_reg(String Fecha_reg) {
        this.Fecha_reg = Fecha_reg;
    }
}
