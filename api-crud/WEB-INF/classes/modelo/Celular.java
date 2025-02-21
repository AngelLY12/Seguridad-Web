package modelo;

public class Celular {
    Long imei;
    String nombre;
    String marca;
    int anoLanzamiento;

 

    public Celular(Long imei, String nombre, String marca, int anoLanzamiento) {
        this.imei = imei;
        this.nombre = nombre;
        this.anoLanzamiento = anoLanzamiento;
        this.marca = marca;
    }
    public Celular(String nombre, String marca, int anoLanzamiento) {
        this.nombre = nombre;
        this.anoLanzamiento = anoLanzamiento;
        this.marca = marca;
    }
    public Celular(){

    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnoLanzamiento() {
        return anoLanzamiento;
    }

    public void setAnoLanzamiento(int anoLanzamiento) {
        this.anoLanzamiento = anoLanzamiento;
    }

    @Override
    public String toString() {
        return "Celular{" +
                "imei=" + imei +
                ", nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", anoLanzamiento=" + anoLanzamiento +
                '}';
    }
}
