package interfaces;

import java.util.List;


public interface DataInterface<T> {
    public String insertar(T object, String query);
    public List<T> seleccionar(String query, Class<T> clazz);
    public String modificar(T object, String query);
    public String eliminar(Long id, String query);
    public T findById(Long id, String query, Class<T> clazz);
}
