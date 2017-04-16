package co.informatica.mvc.views

import scala.xml.Elem

trait BaseTemplate {

  def message(currentUser: String = ""): Elem =
    <html lang="es">
      <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta name="description" content="Blog MVC"/>
        <meta name="author" content="Kevin Gutierrez"/>
        <title>
          {title}
        </title>
        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <!-- custom CSS -->
        <link rel="stylesheet" href="/styles/main.min.css?v=1"/>
        <!-- Custom Fonts -->
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="//fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css"/>
        <link href="//fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="//oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="//oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
      </head>
      <body>
        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-custom navbar-fixed-top">
          <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="/">Blog MVC</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav navbar-right">
                {if (currentUser.trim.isEmpty) {
                <li>
                  <a href="/register">Registrarse</a>
                </li>
                  <li>
                    <a href="/login">Iniciar Sesión</a>
                  </li>
              } else {
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                    {currentUser}<span class="caret"></span>
                  </a>
                  <ul class="dropdown-menu">
                    <li>
                      <a href="/posts">Mis posts</a>
                    </li>
                    <li>
                      <a href="/users">Usuarios</a>
                    </li>
                    <li role="separator" class="divider"></li>
                    <li>
                      <a href="/logout">Cerrar sesión</a>
                    </li>
                  </ul>
                </li>
              }}
              </ul>
            </div> <!-- /.navbar-collapse -->
          </div> <!-- /.container-fluid -->
        </nav>{header}<!-- Main Content -->
        <div class="container">
          {template}
        </div>
        <!-- Footer -->
        <footer>
          <div class="container">
            <div class="row">
              <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <ul class="list-inline text-center">
                  <li>
                    <a href="https://twitter.com/JKevinGutierrez">
                      <span class="fa-stack fa-lg">
                        <i class="fa fa-circle fa-stack-2x"></i>
                        <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                      </span>
                    </a>
                  </li>
                  <li>
                    <a href="https://www.facebook.com/jkevingutierrez">
                      <span class="fa-stack fa-lg">
                        <i class="fa fa-circle fa-stack-2x"></i>
                        <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                      </span>
                    </a>
                  </li>
                  <li>
                    <a href="https://github.com/jkevingutierrez">
                      <span class="fa-stack fa-lg">
                        <i class="fa fa-circle fa-stack-2x"></i>
                        <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                      </span>
                    </a>
                  </li>
                </ul>
                <p class="copyright text-muted">Copyright © 2017 Kevin Gutierrez. All Rights Reserved</p>
              </div>
            </div>
          </div>
        </footer>
        <!-- jQuery -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Contact Form JavaScript -->
        <script src="/javascript/vendor/jqBootstrapValidation.min.js?v=1"></script>
      </body>
    </html>

  def template: Elem = <h1>Plantilla de ejemplo</h1>

  def title: String = "Blog MVC"

  def header: Elem =
    <header class="intro-header" style="background-image: url('/img/home-bg.jpg?v=1')">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <div class="site-heading">
              <h1>Blog MVC</h1>
              <hr class="small"/>
              <span class="subheading">Un blog inspirado en los principios solid de la POO</span>
            </div>
          </div>
        </div>
      </div>
    </header>

}
