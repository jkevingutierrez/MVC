package co.informatica.mvc.models

import com.mongodb.casbah.Imports._

object MongoFactory {
  // private val SERVER = "localhost"
  // private val PORT   = 27017
  // private val DATABASE = "test"
  private val DATABASE = "heroku_mkmf5xpd"
  private val URI = MongoClientURI("mongodb://heroku_mkmf5xpd:5rvv6dlhv3ulg57lvrpppdmkfp@ds161190.mlab.com:61190/heroku_mkmf5xpd")
  // val mongoClient = MongoClient(SERVER, PORT)
  val mongoClient = MongoClient(URI)
  val db = mongoClient(DATABASE)

}
