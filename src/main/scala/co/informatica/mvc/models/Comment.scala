package co.informatica.mvc.models

import java.text.SimpleDateFormat
import java.util.Date

import com.mongodb.casbah.Imports._

class Comment(val content: String, val user: User, val id: String = "") extends Model {
  var createdDate: String = ""
}

object Comment {
  private val coll = MongoFactory.collection("comment")
  private val dateFormat = new SimpleDateFormat("dd/MM/yyyy")

  def getAll: Iterator[Comment] = {
    val cursorIterator = coll.find()
    cursorIterator.map({ mongoObject => convertDbObjectToModel(mongoObject) })
  }

  private def convertDbObjectToModel(obj: MongoDBObject): Comment = {
    val id = obj.getAs[ObjectId]("_id").get.toString()
    val content = obj.getAs[String]("content").get
    val date = obj.getAs[String]("date").get
    val userId = obj.getAs[String]("user.id").get
    val userName = obj.getAs[String]("user.name").get
    val userEmail = obj.getAs[String]("user.email").get

    val user = new User(userId, userName, userEmail)

    val comment = new Comment(content, user, id)
    comment.createdDate = date
    comment
  }

  def get(id: String): Option[Comment] = {
    val mongoObject = MongoDBObject("_id" -> new ObjectId(id))
    val someComment = coll.findOne(mongoObject)

    someComment match {
      case Some(someComment) => {
        Option(convertDbObjectToModel(someComment))
      }
      case None => {
        None
      }
    }
  }

  def find(comment: Comment): Option[Comment] = {
    val mongoObject = buildMongoDbObject(comment, comment.createdDate)
    val someComment = coll.findOne(mongoObject)

    someComment match {
      case Some(someComment) => {
        Option(convertDbObjectToModel(someComment))
      }
      case None => {
        None
      }
    }
  }

  def create(comment: Comment): Comment = {
    var mongoObject = MongoDBObject("content" -> comment.content)
    val someComment = coll.findOne(mongoObject)
    val date = new Date()
    val createdDate = dateFormat.format(date)

    someComment match {
      case Some(someComment) => {
        println("El Comentrio ya existe")
        comment
      }
      case None => {
        mongoObject = buildMongoDbObject(comment, createdDate)
        coll.insert(mongoObject)
        convertDbObjectToModel(mongoObject)
      }
    }
  }

  private def buildMongoDbObject(comment: Comment, createdDate: String): MongoDBObject = {
    MongoDBObject(
      "content" -> comment.content,
      "date" -> createdDate,
      "user" -> MongoDBObject(
        "id" -> comment.user.id,
        "name" -> comment.user.name,
        "email" -> comment.user.email))
  }

  def delete(id: String): WriteResult = {
    val mongoObject = MongoDBObject("_id" -> new ObjectId(id))
    coll.remove(mongoObject)
  }

}