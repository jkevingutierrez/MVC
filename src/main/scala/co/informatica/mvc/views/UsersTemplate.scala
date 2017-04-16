package co.informatica.mvc.views

import co.informatica.mvc.models.User

import scala.xml.Elem

object UsersTemplate extends BaseTemplate {

  var entities: Option[Iterator[User]] = None

  override def title: String = "Blog MVC | Iniciar Sesión"

  override def header: Elem =
    <header class="intro-header" style="background-image: url('/img/about-bg.jpg?v=1')">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <div class="page-heading">
              <h1>Usuarios registrados</h1>
              <hr class="small"/>
            </div>
          </div>
        </div>
      </div>
    </header>

  override def template: Elem =
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
        <div>
          {
            entities match {
              case Some(entities) => {
                <table class="table table-striped">
                  <thead>
                    <tr>
                      <th>Nombre</th>
                      <th>Correo</th>
                      <th class="controls"></th>
                    </tr>
                  </thead>
                  <tbody>
                    {
                      for (model <- entities) yield {
                        <tr>
                          <td>{ model.name }</td>
                          <td>{ model.email }</td>
                          <td>
                            <a class="btn btn-danger" href={ "/users/delete/" + model.id }>Eliminar</a>
                          </td>
                        </tr>
                      }
                    }
                  </tbody>
                </table>

              }
              case None => {
                <h3>No hay usuarios registrados.</h3>
              }
            }
          }
        </div>
        <div class="row">
          <div class="form-group col-xs-12">
            <div class="text-center">
              <a class="btn btn-default" href="/users/create" role="button">Crear Nuevo Usuario</a>
            </div>
          </div>
        </div>
      </div>
    </div>
}
