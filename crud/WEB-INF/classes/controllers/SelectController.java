package controllers;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Celular;
import services.CelularService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectController extends HttpServlet {

    private final CelularService service = new CelularService();

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        List<Celular> celulares = service.verCelulares();

        // Verifica si la lista está vacía o nula
        if (celulares == null || celulares.isEmpty()) {
            request.setAttribute("celulares", celulares);
        } else {
            request.setAttribute("celulares", new ArrayList<>());
        }

        request.setAttribute("celulares", celulares);
        RequestDispatcher rd = request.getRequestDispatcher("/src/phones.jsp");
        rd.forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            "Ocurrió un error en el servidor");
    }
}

}

