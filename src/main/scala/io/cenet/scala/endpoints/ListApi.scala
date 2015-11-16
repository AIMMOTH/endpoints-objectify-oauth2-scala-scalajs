package io.cenet.scala.endpoints

import com.google.api.server.spi.config.Api
import com.google.api.server.spi.config.ApiMethod
import io.cenet.scala.datastore.entity.ListEntity
import io.cenet.scala.datastore.Objectify
import com.googlecode.objectify.Key

@Api(version = "v1", name = "list")
class ListApi {
  
  @ApiMethod(httpMethod = "get")
  def get = Objectify.load.`type`(classOf[ListEntity])
  
  @ApiMethod(httpMethod = "post")
  def post(list : List[String]) = Objectify.save.entity(new ListEntity(null, list)).now
  
  @ApiMethod(httpMethod = "put")
  def put(id : java.lang.Long, list : List[String]) = {
    Objectify.load.key(Key.create(classOf[ListEntity], id)).now match {
      case existing : ListEntity => 
        existing.list = list
        Objectify.save.entity(existing).now
    }
  }
  
  @ApiMethod(httpMethod = "delete")
  def delete(id : java.lang.Long) = Objectify.delete.key(Key.create(classOf[ListEntity], id))
}