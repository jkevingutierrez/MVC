package co.informatica.mvc.controllers

import co.informatica.mvc.models.{ Post, User }
import co.informatica.mvc.views.{ PostFormTemplate, PostsTemplate }
import javax.servlet.http.{ HttpServletRequest, HttpServletResponse, HttpSession }

class PostsController extends BaseController {

  override lazy val template = PostsTemplate

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
    super.doGet(req, resp)

    val session: HttpSession = req.getSession(false)

    if (session != null) {
      val userName = session.getAttribute("name").toString()
      val userId = session.getAttribute("id").toString()
      resp.setCharacterEncoding("UTF-8")

      if (req.getPathInfo == null) {
        val posts = Post.getByUserId(userId)

        template.entities = Option(posts)
        resp.getWriter().print("<!DOCTYPE html>" + template.message(userName))
      } else if (req.getPathInfo() == "/create") {
        resp.getWriter().print("<!DOCTYPE html>" + PostFormTemplate.message(userName))
      } else if (req.getPathInfo() contains "delete") {
        val oid = req.getPathInfo().substring(req.getPathInfo().lastIndexOf("/") + 1)
        val remove = Post.delete(oid)

        resp.sendRedirect("/posts")
      } else {
        resp.sendRedirect("/posts")
      }
    } else {
      resp.sendRedirect("/login")
    }

  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {
    super.doPost(req, resp)

    val id = ""
    val title = req.getParameter("title")
    val subtitle = req.getParameter("subtitle")
    val content = req.getParameter("content")

    val session: HttpSession = req.getSession(false)

    val userId = session.getAttribute("id").toString()
    val userName = session.getAttribute("name").toString()
    val userEmail = session.getAttribute("email").toString()

    val user = new User(userId, userName, userEmail)

    val post = new Post(id, title, subtitle, content, user)

    val createdPost = Post.create(post)
    println("Created Post:")
    println(createdPost.id)
    println(createdPost.title)
    println(createdPost.subtitle)
    println(createdPost.content)
    println(createdPost.user)

    resp.sendRedirect("/posts")

  }
}
