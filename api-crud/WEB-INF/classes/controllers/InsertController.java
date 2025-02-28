package controllers;
import javax.servlet.http.*;
import modelo.Celular;
import services.Dataservice;

import java.io.IOException;
import java.io.PrintWriter;

public class InsertController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
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
            int anoLanzamiento = Integer.parseInt(anoLanzamientoParam);

           
            Dataservice<Celular> service = new Dataservice<>();
            Celular celular = new Celular(imei, nombre, marca, anoLanzamiento);
            String resultado = service.insertar(celular, "INSERT INTO celular (imei, nombre, marca, anoLanzamiento) VALUES (?, ?, ?, ?)");
            
            response.sendRedirect(request.getContextPath() + "/SelectController?");

        } catch (NumberFormatException e) {
            out.println("<p>Error: El IMEI o el año de lanzamiento deben ser números válidos</p>");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } catch (Exception e) {
            out.println("<p>Error inesperado: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }
}

