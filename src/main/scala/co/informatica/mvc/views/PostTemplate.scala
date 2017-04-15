package co.informatica.mvc.views

import co.informatica.mvc.models.Post
import scala.xml.Elem

object PostTemplate extends BaseTemplate {

  var entity: Option[Post] = None

  override def title: String = "Blog MVC | Iniciar Sesión"

  override def header: Elem =
    <header class="intro-header" style="background-image: url('/img/post-bg.jpg')">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            {
              entity match {
                case Some(entity) => {
                  <div class="post-heading">
                    <h1>{ entity.title }</h1>
                    <h2 class="subheading">{ entity.subtitle }</h2>
                    <span class="meta">
                      Creado por{ entity.user.name }
                      el día{ entity.createdDate }
                    </span>
                  </div>
                }
                case None => {
                  <h3>El post no existe.</h3>
                }
              }
            }
          </div>
        </div>
      </div>
    </header>

  override def template: Elem =
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        {
          entity match {
            case Some(entity) => {
              <p>{ entity.content }</p>
            }
            case None => {
              <h3>El post no existe.</h3>
            }
          }
        }
      </div>
    </div>
}
