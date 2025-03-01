package controllers.users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.User;
import services.Dataservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectUsersController extends HttpServlet {

    private final Dataservice<User> service = new Dataservice<>();

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        // Obtener la lista de celulares desde la base de datos
        List<User> users = service.seleccionar("SELECT * FROM users", User.class);

        // Si la lista está vacía o es nula, se asigna una lista vacía
        if (users != null && !users.isEmpty()) {
            request.setAttribute("users", users);
        } else {
            request.setAttribute("users", new ArrayList<>());  // Lista vacía si no se encuentran celulares
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
            if ("ADMIN".equalsIgnoreCase(profile)) {
                rd = request.getRequestDispatcher("panel.jsp");
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
