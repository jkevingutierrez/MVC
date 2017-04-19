package co.informatica.mvc.views

import scala.xml.Elem

object PostFormTemplate extends BaseTemplate {

  override def title: String = "Blog MVC | Iniciar Sesión"

  override def header: Elem =
    <header class="intro-header"></header>

  override def template(currentUser: String = ""): Elem =
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <h3 class="text-center">Crear Nuevo Post</h3>
        <form action="/posts" method="post" novalidate="novalidate">
          <div class="row control-group">
            <div class="form-group col-xs-12 floating-label-form-group controls">
              <label for="title">Titulo</label>
              <input type="text" class="form-control" placeholder="Titulo" id="title" name="title" required="required" data-validation-required-message="Escribe el titulo del post."/>
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <div class="row control-group">
            <div class="form-group col-xs-12 floating-label-form-group controls">
              <label for="subtitle">Subtitulo</label>
              <input type="text" class="form-control" placeholder="Subtitulo" id="subtitle" name="subtitle"/>
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <div class="row control-group">
            <div class="form-group col-xs-12 floating-label-form-group controls">
              <label for="content">Contenido</label>
              <textarea class="form-control" placeholder="Contenido" id="content" name="content" required="required" data-validation-required-message="Escribe el cuerpo del post."></textarea>
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
      </div>
    </div>
}
