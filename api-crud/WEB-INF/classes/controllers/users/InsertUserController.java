package controllers.users;
import javax.servlet.http.*;
import services.Dataservice;
import java.io.IOException;
import java.io.PrintWriter;

import modelo.User;

public class InsertUserController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String rfc = request.getParameter("rfc");
            String name = request.getParameter("name");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");

            if (rfc == null || rfc.isEmpty() ||
                name == null || name.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios y no pueden estar vacíos");
            }

           
            Dataservice<User> service = new Dataservice<>();
            User user = new User(rfc,name,lastName,password);
            String resultado = service.insertar(user, "INSERT INTO users (rfc, name, lastname, password, profile) VALUES (?, ?, ?, ?,?)");
            HttpSession sesion = request.getSession();
            sesion.setAttribute("resultado", resultado);
            response.sendRedirect(request.getContextPath() + "/SelectController?");

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
