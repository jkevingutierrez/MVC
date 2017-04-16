package co.informatica.mvc.controllers

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

class LogoutController extends BaseController {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
    super.doGet(req, resp)

    val session = req.getSession()
    session.invalidate()
    resp.sendRedirect("/")

  }

}
