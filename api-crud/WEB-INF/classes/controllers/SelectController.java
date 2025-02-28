package controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Celular;
import modelo.User;
import services.Dataservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectController extends HttpServlet {

    private final Dataservice<Celular> service = new Dataservice<>();

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        // Obtener la lista de celulares desde la base de datos
        List<Celular> celulares = service.seleccionar("SELECT * FROM celular", Celular.class);

        // Si la lista está vacía o es nula, se asigna una lista vacía
        if (celulares != null && !celulares.isEmpty()) {
            request.setAttribute("celulares", celulares);
        } else {
            request.setAttribute("celulares", new ArrayList<>());  // Lista vacía si no se encuentran celulares
        }

        HttpSession sesion = request.getSession();

        // Recuperar el objeto User guardado en la sesión
        User resultado = (User) sesion.getAttribute("resultado");
        
        // Verificar si el objeto User existe en la sesión
        if (resultado != null) {
            // Acceder al atributo profile del objeto User
            String profile = resultado.getProfile();
        
            // Redirección dependiendo del perfil
            RequestDispatcher rd = null;
            if ("USER".equalsIgnoreCase(profile)) {
                rd = request.getRequestDispatcher("hero.jsp");
            } else if ("ADMIN".equalsIgnoreCase(profile)) {
                rd = request.getRequestDispatcher("panel.jsp");
            } else if ("MODERATOR".equalsIgnoreCase(profile)) {
                rd = request.getRequestDispatcher("phones.jsp");
            }
        
            // Realizar el forward a la página correspondiente
            if (rd != null) {
                rd.forward(request, response);
            } else {
                System.out.println("No se pudo encontrar la página correspondiente.");
            }
        } else {
            System.out.println("No se encontró el objeto User en la sesión.");
            // Puedes redirigir a una página de error o de inicio de sesión
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error en el servidor");
    }
    }

}
