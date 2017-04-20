package co.informatica.mvc.models

trait Get {
  def getAll: Iterator[Model]

  def get(id: String): Option[Model]

  def find(model: Model): Option[Model]
}
