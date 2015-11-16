# endpoints-objectify-oauth2-scala-scalajs

Maven
-----

1) Optional: Build Scala JS with 1.1) $ SBT 1.2) fastOptJS

2) Run with $ mvn appengine:devserver

App Engine Devserver
--------------------

Run locally with Appengine SDK.

1) Build with maven $ mvn package

2) Copy files to source $ copy target/endpoints-objectify-oauth2-scala-scalajs/WEB-INF/lib/* src/main/webapp/WEB-INF/lib/

3) Optional: Download Appengine SDK or look for it in Maven repostiory

3) Start with address and port as parameters $ %APPENGINE_SDK_HOME/bin/dev_appserver -a localhost -p 8080 src/main/webapp

Eclipse
-------

Create Eclipse project with some modifications and import

1) $ mvn eclipse:eclipse

2) Edit .project and replace builder and nature

3) Import in Eclipse

4) Set source and target output
