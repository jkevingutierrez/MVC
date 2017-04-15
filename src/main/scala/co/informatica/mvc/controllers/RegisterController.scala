package co.informatica.mvc.controllers

import co.informatica.mvc.models.User
import co.informatica.mvc.views.RegisterFormTemplate
import javax.servlet.http.{ HttpServletRequest, HttpServletResponse }

class RegisterController extends BaseController {
  override lazy val template = RegisterFormTemplate

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
    super.doGet(req, resp)

    resp.setCharacterEncoding("UTF-8")
    resp.getWriter().print("<!DOCTYPE html>" + template.message())

  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {
    super.doPost(req, resp)

    val id = ""
    val name = req.getParameter("name")
    val email = req.getParameter("email")

    val user = new User(id, name, email)

    val createdUser = User.create(user)

    resp.sendRedirect("/login")

  }
}
