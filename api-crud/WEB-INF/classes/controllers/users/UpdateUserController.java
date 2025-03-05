package controllers.users;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import modelo.User;
import services.Dataservice;
import java.io.IOException;
import java.rmi.ServerException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateUserController extends HttpServlet {


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        
        try{
            String rfc = request.getParameter("rfc");
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastName");
            String profile = request.getParameter("profile");

            if (rfc == null || rfc.isEmpty() ||
                name == null || name.isEmpty() ||
                lastname == null || lastname.isEmpty() ||
                profile == null || profile.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios y no pueden estar vacíos");
            }

            Dataservice<User> service = new Dataservice<>();
            User user = new User(rfc,name,lastname,profile);
            String[] fieldOrder = {"name", "lastname", "profile", "rfc"};
            String resultado = service.modificar(user, "UPDATE users SET name = ?, lastname = ?, profile = ? WHERE id = ?", fieldOrder);
            if(resultado.equals("Registro modificado correctamente")){
                response.sendRedirect(request.getContextPath() + "/SelectController?");
            }else {
                request.setAttribute("errorMessage", "No se pudo modificar el registro.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/updateUser.jsp");
                dispatcher.forward(request, response);
            }
        }catch (Exception e){
            System.out.println("Ha ocurrido un error:" + e);
        }
    }

}