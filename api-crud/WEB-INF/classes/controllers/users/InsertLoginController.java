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

            // Consulta SQL para buscar el usuario
            String sql = "SELECT * FROM users WHERE rfc = ? AND password = ?";
            Dataservice<User> service = new Dataservice<>();
            User resultado = service.findByParams(sql, User.class, rfc, password);

            // Verificar si el usuario fue encontrado
            if (resultado == null) {
                throw new IllegalArgumentException("Usuario no encontrado o credenciales incorrectas.");
            }

            // Guardar el usuario en la sesión
            HttpSession sesion = request.getSession();
            sesion.setAttribute("resultado", resultado);

            // Redirigir según el perfil del usuario
            String profile = resultado.getProfile();
            RequestDispatcher rd = null;

            response.sendRedirect(request.getContextPath() + "/SelectController");


            // Realizar el forward a la página correspondiente
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