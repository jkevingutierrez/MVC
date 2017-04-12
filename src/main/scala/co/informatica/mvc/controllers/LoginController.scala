package co.informatica.mvc.controllers

import co.informatica.mvc.models.User
import co.informatica.mvc.views.IndexTemplate
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

class LoginController extends BaseController
{
	val template = IndexTemplate
	val model = null

  override def doPost(req : HttpServletRequest, resp : HttpServletResponse) = {
    
    val userName = req.getParameter("username")
    val password = req.getParameter("password")
    
    val user2 = new User(userName, password)
//    
//    req.setCharacterEncoding("utf8")
//    
//    val gson = new Gson(); 
//    
//    val reader = req.getReader()
//        
//    val jsonUser = reader.readLine()
//
//    val user = gson.fromJson(jsonUser, classOf[User])
//     
//    val json = gson.toJson(user)
//    resp.setContentType("application/json")
//    resp.getWriter().print(json)

    resp.getWriter().print("Hello World")
        
  }
}
