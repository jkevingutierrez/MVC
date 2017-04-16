package co.informatica.mvc.views

import co.informatica.mvc.models.Post

import scala.xml.Elem

object PostsTemplate extends BaseTemplate {

  var entities: Option[Iterator[Post]] = None

  override def title: String = "Blog MVC | Iniciar Sesión"

  override def header: Elem =
    <header class="intro-header" style="background-image: url('/img/post-bg.jpg?v=1')">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <div class="page-heading">
              <h1>Mis Posts</h1>
              <hr class="small"/>
            </div>
          </div>
        </div>
      </div>
    </header>

  override def template: Elem =
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        {entities match {
        case Some(entities) => {
          for (model <- entities) yield {
            <div class="post-preview">
              <h2 class="post-title">
                {model.title}
              </h2>
              <h3 class="post-subtitle">
                {model.subtitle}
              </h3>
              <p class="post-meta">Creado por
                {model.user.name}
                el día
                {model.createdDate}
              </p>
              <a class="btn btn-danger" href={"/posts/delete/" + model.id} role="button">Eliminar Post</a>
            </div>
                <hr/>
          }
        }
        case None => {
          <h3>No tiene posts registrados.</h3>
        }
      }}<div class="row">
        <div class="form-group col-xs-12">
          <div class="text-center">
            <a class="btn btn-default" href="/posts/create" role="button">Crear Nuevo Post</a>
          </div>
        </div>
      </div>
      </div>
    </div>
}
