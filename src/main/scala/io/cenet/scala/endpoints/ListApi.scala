package io.cenet.scala.endpoints

import java.lang.{Long => JLong}
import com.google.api.server.spi.config.Api
import com.google.api.server.spi.config.ApiMethod
import io.cenet.scala.datastore.entity.ListEntity
import io.cenet.scala.datastore.Objectify
import com.googlecode.objectify.Key
import com.google.appengine.api.users.{User => ApiUser}
import javax.inject.Named

@Api(version = "v1", name = "list", authenticators = Array(classOf[ScalaAuthenticator]))
class ListApi {
  
  @ApiMethod(httpMethod = "get")
  def get = Objectify.load.`type`(classOf[ListEntity])
  
  @ApiMethod(httpMethod = "post")
  def post(@Named("csv") csv : String) = Objectify.save.entity(new ListEntity(null, csv.split(",").toList)).now
  
  @ApiMethod(httpMethod = "put", path = "{id}")
  def put(@Named("id") id : JLong, @Named("csv") csv : String) = 
    Objectify.load.key(Key.create(classOf[ListEntity], id)).now match {
      case existing : ListEntity => 
        existing.list = csv.split(",").toList
        Objectify.save.entity(existing).now
    }
  
  @ApiMethod(httpMethod = "delete", path = "{id}")
  def delete(user : ApiUser, @Named("id") id : JLong) = Objectify.delete.key(Key.create(classOf[ListEntity], id))
}