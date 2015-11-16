package io.cenet.scala.datastore

import com.googlecode.objectify.ObjectifyFactory
import com.googlecode.objectify.ObjectifyService
import io.cenet.scala.datastore.entity.ListEntity
import com.googlecode.objectify.VoidWork

object Objectify {
  
  ObjectifyService.register(classOf[ListEntity])
  
  def load = ObjectifyService.ofy.load
  
  def save = ObjectifyService.ofy.save
  
  def delete = ObjectifyService.ofy.delete
  
  def transaction(work : VoidWork) = ObjectifyService.ofy.transactNew(100, work)
}