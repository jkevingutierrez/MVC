package co.informatica.mvc.controllers

import javax.servlet.http.{HttpServletRequest, HttpServletResponse, HttpSession}

import co.informatica.mvc.models.{Comment, Post, User}

class CommentsController extends BaseController {

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {
    super.doPost(req, resp)

    val content = req.getParameter("content")

    val session: HttpSession = req.getSession(false)

    val userId = session.getAttribute("id").toString()
    val userName = session.getAttribute("name").toString()
    val userEmail = session.getAttribute("email").toString()

    val postId = req.getParameter("postid")

    val post = Post.get(postId)

    post match {
      case Some(post) => {
        println("Post")
        println(post.id)
        println(post.title)

        val user = new User(userId, userName, userEmail)
        var comment = new Comment(content, user)
        comment = Comment.create(comment)

        println(comment.id)
        Post.addComment(post.id, comment)

        resp.sendRedirect("/post/" + post.id)
      }
      case None => {}
    }

    // val createdComment = Comment.create(comment)
    // println("Created Comment:")
    // println(createdComment.id)
    // println(createdComment.content)
    // println(createdComment.user)

  }
}
