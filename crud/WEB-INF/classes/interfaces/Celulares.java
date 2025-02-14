package interfaces;

import java.util.List;

import modelo.Celular;

public interface Celulares {
    public String añadirCelular(Celular celular);
    public List<Celular> verCelulares();
    public boolean findByImei(Long imei);
    public String modificarCelular(Celular nuevoCelular);
    public String eliminarCelular(Long imei);
    public Celular findCelularByImei(Long imei);
}
