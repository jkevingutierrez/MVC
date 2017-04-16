package co.informatica.mvc.controllers

import javax.servlet.http.{HttpServletRequest, HttpServletResponse, HttpSession}

import co.informatica.mvc.models.Post
import co.informatica.mvc.views.IndexTemplate

class IndexController extends BaseController {
  override lazy val template = IndexTemplate

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {

    super.doGet(req, resp)

    val session: HttpSession = req.getSession(false)
    var name = ""

    if (session != null) {
      name = session.getAttribute("name").toString()
    }

    val posts = Post.getAll

    template.entities = Option(posts)

    resp.setCharacterEncoding("UTF-8")
    resp.getWriter().print("<!DOCTYPE html>" + template.message(name))

  }
}
