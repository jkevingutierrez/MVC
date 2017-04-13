package co.informatica.mvc.controllers

import javax.servlet.http.HttpServlet
import co.informatica.mvc.models.Model
import co.informatica.mvc.views.BaseTemplate

trait BaseController extends HttpServlet {
  val model: Model
  val template: BaseTemplate
}
