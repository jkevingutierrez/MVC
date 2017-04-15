package co.informatica.mvc.views

import scala.xml.Elem

object RegisterFormTemplate extends BaseTemplate {

  override def title: String = "Blog MVC | Registrar"

  override def header: Elem =
    <header class="intro-header"></header>

  override def template: Elem =
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <h3 class="text-center">Ingresa tus datos para registrarte</h3>
        <form action="/register" method="post" novalidate="novalidate">
          <div class="row control-group">
            <div class="form-group col-xs-12 floating-label-form-group controls">
              <label>Nombre</label>
              <input type="text" class="form-control" placeholder="Nombre" id="name" name="name" required="required" data-validation-required-message="Escribe tu nombre."/>
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <div class="row control-group">
            <div class="form-group col-xs-12 floating-label-form-group controls">
              <label>Correo electronico</label>
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
