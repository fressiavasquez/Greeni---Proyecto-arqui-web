package pe.edu.upc.greeni.dtos;

import java.time.LocalDate;

public class InteraccionTipoUsuarioListaDTO {

    private int interaccionId;
    private String descripcion;
    private LocalDate fechaPub;
    private String usuario;
    private String tipoInteraccion;

    public int getInteraccionId() {
        return interaccionId;
    }

    public void setInteraccionId(int interaccionId) {
        this.interaccionId = interaccionId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaPub() {
        return fechaPub;
    }

    public void setFechaPub(LocalDate fechaPub) {
        this.fechaPub = fechaPub;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoInteraccion() {
        return tipoInteraccion;
    }

    public void setTipoInteraccion(String tipoInteraccion) {
        this.tipoInteraccion = tipoInteraccion;
    }
}
