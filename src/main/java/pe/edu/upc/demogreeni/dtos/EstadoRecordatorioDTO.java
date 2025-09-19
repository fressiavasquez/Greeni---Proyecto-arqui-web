package pe.edu.upc.demogreeni.dtos;

public class EstadoRecordatorioDTO {
    private int idEstadoRecordatorio;
    private String nombre;
    private String tipoER;

    public int getIdEstadoRecordatorio() {
        return idEstadoRecordatorio;
    }

    public void setIdEstadoRecordatorio(int idEstadoRecordatorio) {
        this.idEstadoRecordatorio = idEstadoRecordatorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoER() {
        return tipoER;
    }

    public void setTipoER(String tipoER) {
        this.tipoER = tipoER;
    }
}
