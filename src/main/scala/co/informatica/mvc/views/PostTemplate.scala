package co.informatica.mvc.views

import co.informatica.mvc.models.Post

import scala.xml.Elem

object PostTemplate extends BaseTemplate {

  var entity: Option[Post] = None

  override def title: String = "Blog MVC | Iniciar Sesión"

  override def header: Elem =
    <header class="intro-header" style="background-image: url('/img/post-bg.jpg?v=1')">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            {entity match {
            case Some(entity) => {
              <div class="post-heading">
                <h1>
                  {entity.title}
                </h1>
                <h2 class="subheading">
                  {entity.subtitle}
                </h2>
                <span class="meta">
                  Creado por
                  {entity.user.name}
                  el día
                  {entity.createdDate}
                </span>
              </div>
            }
            case None => {
              <h3>El post no existe.</h3>
            }
          }}
          </div>
        </div>
      </div>
    </header>

  override def template(currentUser: String = ""): Elem =
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        {entity match {
        case Some(entity) => {
          <p>
            {entity.content}
          </p>
              <hr/>
            <h4>Comentarios</h4>
            <div class="row">
              {if (entity.comments != null) {
              for (comment <- entity.comments) yield {
                <div class="comment-preview">
                  <p class="comment-meta">
                    {comment.user.name} dice:
                  </p>
                  <p class="comment-content">
                    {comment.content}
                  </p>
                  <p class="comment-meta">
                    {comment.createdDate}
                  </p>
                </div>
                    <hr/>
              }
            }}{if (!currentUser.trim.isEmpty) {
              <form action="/comments" method="post" novalidate="novalidate" class="comment-form">
                <input type="hidden" name="postid" value={entity.id}/>
                <div class="row control-group">
                  <div class="form-group col-xs-12 floating-label-form-group controls">
                    <label for="content">Escribe un comentario</label>
                    <textarea class="form-control" placeholder="Escribe tu comentario" id="content" name="content" required="required" data-validation-required-message="Escribe algo en el comentario."></textarea>
                    <p class="help-block text-danger"></p>
                  </div>
                </div>
                <br/>
                <div class="row">
                  <div class="form-group col-xs-12">
                    <button type="submit" class="btn btn-default">Enviar</button>
                  </div>
                </div>
              </form>
            }}
            </div>


        }
        case None => {
          <h3>El post no existe.</h3>
        }
      }}
      </div>
    </div>
}
