package pe.edu.upc.greeni.dtos;

public class QuantityEspecieDTO {
    private int IdEspecie;
    private int quantityEspecie;

    public int getIdEspecie() {
        return IdEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        IdEspecie = idEspecie;
    }

    public int getQuantityEspecie(String s) {
        return quantityEspecie;
    }

    public void setQuantityEspecie(int quantityEspecie) {
        this.quantityEspecie = quantityEspecie;
    }
}
