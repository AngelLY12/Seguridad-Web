package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import services.CelularService;
import java.io.IOException;

public class DeleteController extends HttpServlet {

    private final CelularService service = new CelularService();

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
            service.eliminarCelular(imei);
    
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
