package controllers;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Celular;
import services.Dataservice;

public class FindCelularController extends HttpServlet {
    private final Dataservice<Celular> service = new Dataservice<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    try {
        String imeiParam = request.getParameter("imei");
        Long imei = Long.parseLong(imeiParam);
        Celular celular = service.findById(imei,"SELECT nombre,marca,anoLanzamiento FROM celular where imei = ?",Celular.class);
        request.setAttribute("celular", celular);
        RequestDispatcher rd = request.getRequestDispatcher("/src/update.jsp");
        rd.forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            "Ocurrió un error en el servidor");
    }
}

}
