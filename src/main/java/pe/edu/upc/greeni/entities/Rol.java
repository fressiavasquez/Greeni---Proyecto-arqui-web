package pe.edu.upc.greeni.entities;

import jakarta.persistence.*;

@Entity
@Table(name ="Rol")
public class Rol {
    @Id
    private int rolId;

    @Column(name ="tipo",length = 10,nullable = false)
    private String tipo;

    public Rol() {
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
