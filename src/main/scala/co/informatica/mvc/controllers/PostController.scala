package co.informatica.mvc.controllers

import co.informatica.mvc.models.Post
import co.informatica.mvc.views.{ PostTemplate }
import javax.servlet.http.{ HttpServletRequest, HttpServletResponse, HttpSession }

class PostController extends BaseController {

  override lazy val template = PostTemplate

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
    super.doGet(req, resp)

    val session: HttpSession = req.getSession(false)
    var name = ""

    if (session != null) {
      name = session.getAttribute("name").toString()
    }

    val oid = req.getPathInfo().substring(req.getPathInfo().lastIndexOf("/") + 1)
    val post = Post.get(oid)

    template.entity = post
    resp.setCharacterEncoding("UTF-8")
    resp.getWriter().print("<!DOCTYPE html>" + template.message(name))

  }
}
