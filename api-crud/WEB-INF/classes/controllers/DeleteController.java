package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.Celular;
import services.Dataservice;
import java.io.IOException;

public class DeleteController extends HttpServlet {

    private final Dataservice<Celular> service = new Dataservice<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            response.setContentType("text/html");
    
            String imeiStr = request.getParameter("imei");
            if (imeiStr == null || imeiStr.isEmpty() || imeiStr.equals("undefined")) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "IMEI inválido");
                return;
            }
    
            Long imei = Long.parseLong(imeiStr);
            service.eliminar(imei, "DELETE FROM celular WHERE imei = ?");
    
            response.sendRedirect(request.getContextPath() + "/SelectController?");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de IMEI inválido");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar el celular");
        }
    }
    
}
