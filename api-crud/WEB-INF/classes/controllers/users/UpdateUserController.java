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
            User user = service.findByParams("SELECT * FROM users WHERE rfc = ?",User.class,rfc);

	   if (user == null) {
                request.setAttribute("errorMessage", "No se encontró un usuario con el RFC proporcionado.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/updateUser.jsp");
                dispatcher.forward(request, response);
                return;
            }
	    
            user.setName(name);
            user.setLastName(lastname);
            user.setProfile(profile);

            String[] fieldOrder = {"name", "lastName", "profile", "rfc"};
            String resultado = service.modificar(user, "UPDATE users SET name = ?, lastname = ?, profile = ? WHERE rfc = ?", fieldOrder);
            if(resultado.equals("Registro modificado correctamente")){
		HttpSession session = request.getSession();
                session.setAttribute("successMessage", resultado);
                response.sendRedirect(request.getContextPath() + "/SelectUsersController?");
	
            }else {
                request.setAttribute("errorMessage", resultado);
		
                RequestDispatcher dispatcher = request.getRequestDispatcher("/updateUser.jsp");
                dispatcher.forward(request, response);
            }
        }catch (Exception e){
           e.printStackTrace(); 
           
        }
    }

}
