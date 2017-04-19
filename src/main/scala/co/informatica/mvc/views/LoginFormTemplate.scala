package co.informatica.mvc.views

import scala.xml.Elem

object LoginFormTemplate extends BaseTemplate {

  override def title: String = "Blog MVC | Iniciar Sesión"

  override def header: Elem =
    <header class="intro-header"></header>

  override def template(currentUser: String = ""): Elem =
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <h3 class="text-center">Ingresa tus datos para iniciar sesion</h3>
        <form action="/login" method="post" novalidate="novalidate">
          <div class="row control-group">
            <div class="form-group col-xs-12 floating-label-form-group controls">
              <label for="name">Nombre</label>
              <input type="text" class="form-control" placeholder="Nombre" id="name" name="name" required="required" data-validation-required-message="Escribe tu nombre."/>
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <div class="row control-group">
            <div class="form-group col-xs-12 floating-label-form-group controls">
              <label for="email">Correo electronico</label>
              <input type="email" class="form-control" placeholder="Correo electronico" id="email" name="email" required="required" data-validation-required-message="Escribe tu correo."/>
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
