package io.cenet.scala.endpoints

import com.google.api.server.spi.config.Authenticator
import javax.servlet.http.HttpServletRequest
import com.google.api.server.spi.auth.common.{User => SecureUser}

class ScalaAuthenticator extends Authenticator {
  
  def authenticate(request: HttpServletRequest): SecureUser = new SecureUser("id", "email")
}