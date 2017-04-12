package co.informatica.mvc.controllers

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import co.informatica.mvc.views.IndexTemplate

class IndexController extends BaseController
{
  val template = IndexTemplate
  val model = null

  override def doGet(req : HttpServletRequest, resp : HttpServletResponse) = {

    resp.getWriter().print("<!DOCTYPE html>" + template.message)
  }
}
