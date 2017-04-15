package co.informatica.mvc.models

import java.text.SimpleDateFormat
import java.util.Date
import com.mongodb.casbah.Imports._

class Post(val id: String = "", val title: String, val subtitle: String = "", val content: String, val user: User) extends Model {
  var createdDate: String = ""
}

object Post {
  private val coll = MongoFactory.collection("post")
  private val dateFormat = new SimpleDateFormat("dd/MM/yyyy")

  private def buildMongoDbObject(post: Post, createdDate: String): MongoDBObject = {
    MongoDBObject(
      "title" -> post.title,
      "subtitle" -> post.subtitle,
      "content" -> post.content,
      "date" -> createdDate,
      "user" -> MongoDBObject(
        "id" -> post.user.id,
        "name" -> post.user.name,
        "email" -> post.user.email))

  }

  private def convertDbObjectToModel(obj: MongoDBObject): Post = {
    val id = obj.getAs[ObjectId]("_id").get.toString()
    val title = obj.getAs[String]("title").get
    val subtitle = obj.getAs[String]("subtitle").get
    val content = obj.getAs[String]("content").get
    val date = obj.getAs[String]("date").get
    val userId = obj.getAs[String]("user.id").get
    val userName = obj.getAs[String]("user.name").get
    val userEmail = obj.getAs[String]("user.email").get

    val user = new User(userId, userName, userEmail)

    val post = new Post(id, title, subtitle, content, user)
    post.createdDate = date
    post
  }

  def getByUserId(id: String): Iterator[Post] = {
    val q = MongoDBObject(
      "user" -> MongoDBObject("id" -> id))

    val cursorIterator = coll.find(q)
    cursorIterator.map({ mongoObject => convertDbObjectToModel(mongoObject) })
  }

  def getAll: Iterator[Post] = {
    val cursorIterator = coll.find()
    cursorIterator.map({ mongoObject => convertDbObjectToModel(mongoObject) })
  }

  def get(id: String): Option[Post] = {
    val mongoObject = MongoDBObject("_id" -> new ObjectId(id))
    val somePost = coll.findOne(mongoObject)

    somePost match {
      case Some(somePost) => {
        Option(convertDbObjectToModel(somePost))
      }
      case None => {
        None
      }
    }
  }

  def find(post: Post): Option[Post] = {
    val mongoObject = buildMongoDbObject(post, post.createdDate)
    val somePost = coll.findOne(mongoObject)

    somePost match {
      case Some(somePost) => {
        Option(convertDbObjectToModel(somePost))
      }
      case None => {
        None
      }
    }
  }

  def create(post: Post): Post = {
    var mongoObject = MongoDBObject("title" -> post.title)
    val somePost = coll.findOne(mongoObject)
    val date = new Date()
    val createdDate = dateFormat.format(date)

    somePost match {
      case Some(somePost) => {
        println("El usuario ya existe")
        post
      }
      case None => {
        mongoObject = buildMongoDbObject(post, createdDate)
        val insert = coll.insert(mongoObject)
        convertDbObjectToModel(mongoObject)
      }
    }
  }

  def delete(id: String): WriteResult = {
    val mongoObject = MongoDBObject("_id" -> new ObjectId(id))
    coll.remove(mongoObject)
  }

}