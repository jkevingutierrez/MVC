package co.informatica.mvc.controllers

import co.informatica.mvc.models.User
import co.informatica.mvc.views.LoginFormBaseTemplate
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

class LoginController extends BaseController {
  val template = LoginFormBaseTemplate
  val model = null

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {

    resp.setCharacterEncoding("UTF-8")
    resp.getWriter().print("<!DOCTYPE html>" + template.message())

  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {

    val name = req.getParameter("name")
    val email = req.getParameter("email")

    val user = new User(name, email)

    val session= req.getSession()
    session.setAttribute("name", name)

    println(user.name)
    println(user.email)

    resp.sendRedirect("/")

  }
}
