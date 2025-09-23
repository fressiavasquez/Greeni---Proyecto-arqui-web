package pe.edu.upc.greeni.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Estado_Recordatorio")
public class EstadoRecordatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_recordatorio")
    private int idEstadoRecordatorio;

    @Column(name = "nombre", length = 25, nullable = false)
    private String nombre;

    public EstadoRecordatorio() {
    }

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
}
