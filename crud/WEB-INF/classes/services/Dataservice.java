package services;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.connectionDB;
import interfaces.DataInterface;
import java.lang.reflect.Field;


public class Dataservice<T> implements DataInterface<T> {
    private final connectionDB db = new connectionDB();

    @Override
    public String insertar(T object, String query) {
        try(PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            setParameters(stmt, object); 
            stmt.executeUpdate();
            return "Registro añadido con exito";
        } catch (SQLException | IllegalAccessException e) {
            return "Error al añadir celular: " + e.getMessage();
        }finally{
            db.desconectar();
        }
        
    }



    @Override
    public List<T> seleccionar(String query, Class<T> clazz) {
        List<T> objects = new ArrayList<>();
        try(PreparedStatement stmt = db.getConnection().prepareStatement(query);
        ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                T object = clazz.getDeclaredConstructor().newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = rs.getObject(field.getName());
                    field.set(object, value);
                }
                objects.add(object);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al seleccionar:"+e);
        } finally {
                db.desconectar();
            } 
            return objects;
    }
    
    
    @Override
    public String modificar(T object, String query, String[] fieldOrder) {
    try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
        for (int i = 0; i < fieldOrder.length; i++) {
            Field field = object.getClass().getDeclaredField(fieldOrder[i]);
            field.setAccessible(true);
            stmt.setObject(i + 1, field.get(object));
        }
        int filasAfectadas = stmt.executeUpdate();
        return filasAfectadas > 0 ? "Registro modificado correctamente" : "No se pudo modificar el registro";
    } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
        return "Error al modificar: " + e.getMessage();
    } finally {
        db.desconectar();
    }
    }

    @Override
    public String eliminar(Long id, String query) {
        try(PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
            stmt.setLong(1, id);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0 ? "Registro eliminado correctamente" : "No se encontró el registro";

        } catch (SQLException e) {
            return "Error al eliminar el celular: " + e.getMessage();
        } finally {
            db.desconectar();
            
        }
    }
    @Override
    public T findById(Long imei, String query, Class<T> clazz){
        try(PreparedStatement stmt = db.getConnection().prepareStatement(query);
        ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
               T object= clazz.getDeclaredConstructor().newInstance();
               for(Field field: clazz.getDeclaredFields()){
                field.setAccessible(true);
                Object value = rs.getObject(field.getName());
                field.set(object, value);
               }
               return object;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
               
            db.desconectar();
        } 
            return null;

        }
    
    
    private void setParameters(PreparedStatement stmt, T object) throws IllegalAccessException, SQLException{
        Field[] fields= object.getClass().getDeclaredFields();
        int index=1;
        for(Field field: fields){
            field.setAccessible(true);
            stmt.setObject(index++, field.get(object));
        }
    }
}
