package co.informatica.mvc.controllers

import co.informatica.mvc.models.{User, Authenticator}
import javax.servlet.RequestDispatcher
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}
 
class LoginController extends HttpServlet
{
  
  override def doPost(req : HttpServletRequest, resp : HttpServletResponse) = {
    
    var rd:RequestDispatcher = null;
    
    val userName = req.getParameter("username")
    val password = req.getParameter("password")
    
    val authenticator = new Authenticator()
    
    val result = authenticator.authenticate(userName, password)
    
    if (result.equals("success")) {
      val user = new User(userName, password);
			rd = req.getRequestDispatcher("/success.jsp");
			req.setAttribute("user", user);
		} else {
			rd = req.getRequestDispatcher("/error.jsp");
		}
    
    rd.forward(req, resp);
        
  }
    
    
    
//    resp.getWriter().print("<HTML>" +
//      "<HEAD><TITLE>Hello, Scala!</TITLE></HEAD>" +
//      "<BODY>Hello, Scala! This is a servlet.</BODY>" +
//      "</HTML>")
     
}
