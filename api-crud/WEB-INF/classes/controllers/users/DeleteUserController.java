package controllers.users;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import modelo.User;
import services.Dataservice;
import java.io.IOException;

public class DeleteUserController extends HttpServlet {

    private final Dataservice<User> service = new Dataservice<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            response.setContentType("text/html");
    
            String rfc = request.getParameter("rfc");
            if (rfc == null || rfc.isEmpty() || rfc.equals("undefined")) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "RFC inválido");
                return;
            }
	    HttpSession session = request.getSession();
    
	    String resultado=service.eliminar(rfc, "DELETE FROM users WHERE rfc = ?");
    	    if(resultado.equals("Registro eliminado correctamente")){
		
    		session.setAttribute("successMessage", resultado);
		response.sendRedirect(request.getContextPath() + "/SelectUsersController?");
	    }else{
		session.setAttribute("errorMessage", resultado);

	    }
            
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de RFC inválido");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar el celular");
        }
    }
    
}
