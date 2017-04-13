package co.informatica.mvc.controllers

import javax.servlet.http.{HttpServletRequest, HttpServletResponse, HttpSession}

import co.informatica.mvc.views.IndexBaseTemplate

class IndexController extends BaseController {
  val template = IndexBaseTemplate
  val model = null

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {

    val session: HttpSession = req.getSession(false)
    var name = ""

    if (session != null) {
      name = session.getAttribute("name").toString()
      println(name)
    }

    resp.setCharacterEncoding("UTF-8")
    resp.getWriter().print("<!DOCTYPE html>" + template.message(name))
  }
}
