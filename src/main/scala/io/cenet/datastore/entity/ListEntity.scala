package io.cenet.datastore.entity

import java.lang.{Long => JLong}
import java.util.{List => JList}

import scala.collection.JavaConversions._

import com.googlecode.objectify.annotation.Cache
import com.googlecode.objectify.annotation.Id
import com.googlecode.objectify.annotation.Entity
import java.util.Arrays

/**
 * Needs serialization and therefore needs empty constructor and getters and 
 * setters.
 * Also Java types.
 */
@Entity
@Cache
class ListEntity {
  
  @Id
  var id : JLong = null
  var list : JList[String] = null
  
  def ListEntity() = {}
  
  def getId = id
  def setId(newId : JLong) = id = newId
  def getList = list
  def setList(newList : JList[String]) = list = newList
}

object ListEntity {
  
  def apply(list : List[String]) = {
    val entity = new ListEntity
    entity.list = list
    entity
  }
}