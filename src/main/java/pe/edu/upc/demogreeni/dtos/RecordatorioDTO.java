package pe.edu.upc.demogreeni.dtos;

import pe.edu.upc.demogreeni.entities.EstadoRecordatorio;
import pe.edu.upc.demogreeni.entities.Usuario;

public class RecordatorioDTO {
    private int idRecordatorio;
    private String nombre;
    private String fechaRe;
    private String tipo;
    private Usuario usuario;
    private EstadoRecordatorio estado;

    public int getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(int idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaRe() {
        return fechaRe;
    }

    public void setFechaRe(String fechaRe) {
        this.fechaRe = fechaRe;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoRecordatorio getEstado() {
        return estado;
    }

    public void setEstado(EstadoRecordatorio estado) {
        this.estado = estado;
    }
}
