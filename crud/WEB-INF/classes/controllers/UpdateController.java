package controllers;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import modelo.Celular;
import services.Dataservice;
import java.io.IOException;
import java.rmi.ServerException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateController extends HttpServlet {


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        
        try{
            String imeiParam = request.getParameter("imei");
            String nombre = request.getParameter("nombre");
            String marca = request.getParameter("marca");
            String anoLanzamientoParam = request.getParameter("anoLanzamiento");

            if (imeiParam == null || imeiParam.isEmpty() ||
                nombre == null || nombre.isEmpty() ||
                marca == null || marca.isEmpty() ||
                anoLanzamientoParam == null || anoLanzamientoParam.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios y no pueden estar vacíos");
            }

            Long imei = Long.parseLong(imeiParam);
            System.out.println("IMEI Long: " + imei);
            int anoLanzamiento = Integer.parseInt(anoLanzamientoParam);
            System.out.println("Año de Lanzamiento int: " + anoLanzamiento);

            Dataservice<Celular> service = new Dataservice<>();
            Celular celular = new Celular(imei,nombre,marca,anoLanzamiento);
            String resultado = service.modificar(celular, "UPDATE celular SET nombre = ?, marca = ?, anoLanzamiento = ? WHERE imei = ?");
            // Guardar el resultado en la sesión o mostrarlo
            HttpSession sesion = request.getSession();
            sesion.setAttribute("resultado", resultado);
            response.sendRedirect(request.getContextPath() + "/SelectController?");
        }catch (Exception e){
            System.out.println("Ha ocurrido un error:" + e);
        }
    }

}

