package io.cenet.scala.endpoints

import java.lang.{Long => JLong}
import javax.inject.Named
import scala.collection.JavaConversions._
import com.google.api.server.spi.config.Api
import com.google.api.server.spi.config.ApiMethod
import com.googlecode.objectify.Key
import com.google.appengine.api.users.{User => ApiUser}
import com.googlecode.objectify.VoidWork
import io.cenet.scala.datastore.entity.ListEntity
import io.cenet.scala.datastore.Objectify
import io.cenet.scala.endpoints.result.IdResult
import java.util.Arrays

@Api(version = "v1", name = "list", authenticators = Array(classOf[ScalaAuthenticator]))
class ListApi {
  
  @ApiMethod(httpMethod = "get")
  def get = Objectify.load.`type`(classOf[ListEntity]).list()
  
  @ApiMethod(httpMethod = "post")
  def post(@Named("csv") csv : String) = Objectify.save.entity(ListEntity(csv.split(",").toList)).now match {
    case entity => IdResult(entity.getId)
  }
  
  @ApiMethod(httpMethod = "put", path = "{id}")
  def put(@Named("id") id : JLong, @Named("csv") csv : String) : Unit =
    // Make PUT (update) idempotent
    Objectify.transaction(new VoidWork {
      override def vrun =
        Objectify.load.key(Key.create(classOf[ListEntity], id)).now match {
          case existing : ListEntity => 
            existing.list = csv.split(",").toList
            Objectify.save.entity(existing).now
          }
    })
  
  @ApiMethod(httpMethod = "delete", path = "{id}")
  def delete(user : ApiUser, @Named("id") id : JLong) : Unit = 
    // Make DELETE (update) idempotent
    Objectify.transaction(new VoidWork {
      override def vrun =
        Objectify.delete.key(Key.create(classOf[ListEntity], id)).now
    })
}