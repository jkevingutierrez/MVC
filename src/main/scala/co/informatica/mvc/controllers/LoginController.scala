package co.informatica.mvc.controllers

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import co.informatica.mvc.models.User
import co.informatica.mvc.views.LoginFormTemplate

class LoginController extends BaseController {
  override lazy val template = LoginFormTemplate

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

    val findUser = User.find(user)

    findUser match {
      case Some(findUser) => {
        val user = findUser
        val session = req.getSession()

        session.setAttribute("id", user.id)
        session.setAttribute("name", user.name)
        session.setAttribute("email", user.email)

        println("Logged User:")
        println(user.name)
        println(user.email)

        resp.sendRedirect("/")
      }
      case None => {
        resp.sendRedirect("/login")
      }
    }

  }
}
