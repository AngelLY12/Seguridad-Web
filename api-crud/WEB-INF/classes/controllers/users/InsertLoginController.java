package controllers.users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.Dataservice;
import modelo.User;

import java.io.IOException;
import java.io.PrintWriter;

public class InsertLoginController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String rfc = request.getParameter("rfc");
            String password = request.getParameter("password");

            // Validar que los campos no estén vacíos
            if (rfc == null || rfc.isEmpty() || password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios y no pueden estar vacíos.");
            }

          
            String sql = "SELECT * FROM users WHERE rfc = ? AND password = ?";
            Dataservice<User> service = new Dataservice<>();
            User resultado = service.findByParams(sql, User.class, rfc, password);
	    HttpSession sesion = request.getSession();
            // Verificar si el usuario fue encontrado
            if (resultado == null) {
                sesion.setAttribute("errorMessage","Usuario no encontrado o credenciales incorrectas.");
		response.sendRedirect("login.jsp");
            }

            // Guardar el usuario en la sesión
           
            sesion.setAttribute("resultado", resultado);
	    sesion.setMaxInactiveInterval(60*10);
            String profile = resultado.getProfile();
            RequestDispatcher rd = null;

	    if ("USER".equalsIgnoreCase(profile) || "MODERATOR".equalsIgnoreCase(profile)) {
                response.sendRedirect(request.getContextPath() + "/SelectController");
            } 
            else if ("ADMIN".equalsIgnoreCase(profile)) {
                response.sendRedirect(request.getContextPath() + "/SelectUsersController");
            }

            
            if (rd != null) {
                rd.forward(request, response);
            }

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
