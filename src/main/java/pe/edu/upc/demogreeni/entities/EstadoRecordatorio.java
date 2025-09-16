package pe.edu.upc.demogreeni.entities;

import jakarta.persistence.*;

@Entity
@Table (name="estado_recordatorio")
public class EstadoRecordatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_recordatorio")
    private int idEstadoRecordatorio;

    @Column(name = "nombre", length = 25, nullable = false)
    private String nombre;

    // Constructor vacío obligatorio
    public EstadoRecordatorio() {
    }

    // Getters y setters
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

