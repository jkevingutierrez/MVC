package co.informatica.mvc.models

class Authenticator {
  def authenticate(username: String, password: String): String = {

    if ("prasad".equalsIgnoreCase(username) && "password".equals(password)) "success"
    else "failure"
  }
}
