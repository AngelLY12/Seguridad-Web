package controllers.users;
import javax.servlet.http.*;
import java.io.IOException;

public class logOutController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion= request.getSession(false);
	if(sesion!= null){
	   sesion.invalidate();
	}
	response.sendRedirect("login.jsp");
    }
}



