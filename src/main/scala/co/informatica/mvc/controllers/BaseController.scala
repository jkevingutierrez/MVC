package co.informatica.mvc.controllers

import javax.servlet.http.{ HttpServlet, HttpServletRequest, HttpServletResponse }
import co.informatica.mvc.models.Model
import co.informatica.mvc.views.BaseTemplate

trait BaseController extends HttpServlet {
  lazy val model: Model = null
  lazy val template: BaseTemplate = null

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
    println("GET: " + this.getClass.getName)
    println("getPathInfo:")
    println(req.getPathInfo())
  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {
    println("POST: " + this.getClass.getName)
  }

}
