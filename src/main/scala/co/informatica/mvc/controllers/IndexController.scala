package co.informatica.mvc.controllers

import javax.servlet.http.{HttpServletRequest, HttpServletResponse, HttpSession}

import co.informatica.mvc.views.IndexBaseTemplate
import co.informatica.mvc.models.MongoFactory

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

    println("DB:")
    println(MongoFactory.db)

    // println("DBNames:")
    // MongoFactory.mongoClient.dbNames().map(println)

    println("DBCollectionNames")
    MongoFactory.db.collectionNames.map(println)


    resp.setCharacterEncoding("UTF-8")
    resp.getWriter().print("<!DOCTYPE html>" + template.message(name))


  }
}
