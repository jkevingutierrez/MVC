package co.informatica.mvc.controllers

import javax.servlet.http.{HttpServletRequest, HttpServletResponse, HttpSession}

import co.informatica.mvc.models.User
import co.informatica.mvc.views.{UserFormTemplate, UsersTemplate}

class UsersController extends BaseController {

  override lazy val template = UsersTemplate

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
    super.doGet(req, resp)

    val session: HttpSession = req.getSession(false)

    if (session != null) {
      val name = session.getAttribute("name").toString()
      resp.setCharacterEncoding("UTF-8")

      if (req.getPathInfo == null) {
        val users = User.getAll

        template.entities = Option(users)
        resp.getWriter().print("<!DOCTYPE html>" + template.message(name))
      } else if (req.getPathInfo() == "/create") {
        resp.getWriter().print("<!DOCTYPE html>" + UserFormTemplate.message(name))
      } else if (req.getPathInfo() contains "delete") {
        val oid = req.getPathInfo().substring(req.getPathInfo().lastIndexOf("/") + 1)
        val remove = User.delete(oid)

        resp.sendRedirect("/users")
      } else {
        resp.sendRedirect("/users")
      }
    } else {
      resp.sendRedirect("/login")
    }

  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {
    super.doPost(req, resp)

    val id = ""
    val name = req.getParameter("name")
    val email = req.getParameter("email")

    val user = new User(id, name, email)

    val createdUser = User.create(user)
    println("Created User:")
    println(createdUser.id)
    println(createdUser.name)
    println(createdUser.email)

    resp.sendRedirect("/users")

  }
}
