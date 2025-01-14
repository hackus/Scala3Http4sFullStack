# Create scala fullstack project using Scala3, Http4s, Javascript
## Building the project from scratch
________________
### Server
________________
1. sbt new scala/scala3.g8
2. mkdir server
3. mv src server/
________________
### Client
________________
npm create vite@4.1.0
   1. -> client
   2. -> vanilla
   3. -> javascript
### Deploy locally
    cd client
    npm install
    npm run dev
### Install scala-js plugin
    cd client
    npm install -D @scala-js/vite-plugin-scalajs@1.0.0
## Implementation
________________
### build.sbt
________________

Have used [RockTheJvm](#RockTheJvm) example and partially [IdiomaticSoft](#IdiomaticSoft)

    Create common dir to keep shared code between server and client 
        upheld by -core- in build.sbt    
    Configure multimodule project in build.sbt
        upheld by -root- in build.sbt    
    Configure server
        upheld by -server- in build.sbt
    Configure client
        upheld by -client- in build.sbt
________________
### Common
________________
    Contains common DTOs between client(javascript) and server(scala)
________________
### Client
________________
* Change main.js - to not be confused with [ScalaJs](#ScalaJs) main.js
* Create a template page with this element <div id="app"></div>
    upheld by index.html
  * Following the example from [IdiomaticSoft](#IdiomaticSoft) 
  * download styles from [StartBootstrap](#StartBootstrap)
  * reference them in the project
* Create app.js that will load the css and ScalaJs:main.js which will 
    contain the code implemented in scala on client side therefor [ScalaJs](#ScalaJs) library
________________
### Server
________________

* Use the code from [IdiomaticSoft](#IdiomaticSoft)


## Run

* Important    
    
      Start from the client because, when server is started first, client does not start.

* Run Client

      npm run dev

* Run Server
  
      sbt server/run

* Refresh Client

[//]: # (sbt --batch -Dsbt.server.forcestart=true client/compile)

[//]: # (sbt --batch -Dsbt.server.forcestart=true client/run)

# References:
##### [IdiomaticSoft](https://idiomaticsoft.com/post/2023-12-12-fullstack/)
##### [RockTheJvm](https://rockthejvm.com/articles/building-a-full-stack-scala-3-application-with-the-typelevel-stack)
##### [StartBootstrap](https://startbootstrap.com/template/simple-sidebar)
##### [ScalaJs](https://www.scala-js.org/doc/project/cross-build.html)
