package io.cenet.scala.datastore.entity

import com.googlecode.objectify.annotation.Cache
import com.googlecode.objectify.annotation.Id
import com.googlecode.objectify.annotation.Entity

@Entity
@Cache
class ListEntity(@Id var id : java.lang.Long = null, var list : List[String] = Nil) {
  
  def ListEntity() = {}
}