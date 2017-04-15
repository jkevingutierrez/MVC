package co.informatica.mvc.models

import com.mongodb.casbah.Imports._

object MongoFactory extends DatabaseFactory {

  // override protected val SERVER = "localhost"
  // override protected val PORT   = 27017
  // override protected val DATABASE = "test"
  // val mongoClient = MongoClient(SERVER, PORT)

  override protected lazy val SERVER = "ds161190.mlab.com"
  override protected lazy val PORT = 61190
  override protected lazy val USER = "heroku_mkmf5xpd"
  override protected lazy val PASSWORD = "5rvv6dlhv3ulg57lvrpppdmkfp"
  override protected lazy val DATABASE = "heroku_mkmf5xpd"

  protected val URI = MongoClientURI("mongodb://" + USER + ":" + PASSWORD + "@" + SERVER + ":" + PORT + "/" + DATABASE)
  protected val mongoClient = MongoClient(URI)

  override protected lazy val db = mongoClient(DATABASE)

  def collection(coll: String) = db(coll)

}
