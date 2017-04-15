package co.informatica.mvc.views

import co.informatica.mvc.models.Post

import scala.xml.Elem

object IndexTemplate extends BaseTemplate {

  var entities: Option[Iterator[Post]] = None

  override def template: Elem =
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        {
          entities match {
            case Some(entities) => {
              for (model <- entities) yield {
                <div class="post-preview">
                  <a href={ "/post/" + model.id }>
                    <h2 class="post-title">
                      { model.title }
                    </h2>
                    <h3 class="post-subtitle">
                      { model.subtitle }
                    </h3>
                  </a>
                  <p class="post-meta">Creado por { model.user.name } el día { model.createdDate }</p>
                </div>
                <hr/>
              }
            }
            case None => {
              <h3>No existe ningun post.</h3>
            }
          }
        }
      </div>
    </div>
}
