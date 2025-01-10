## sbt project compiled with Scala 3

### Usage

This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

For more information on the sbt-dotty plugin, see the
[scala3-example-project](https://github.com/scala/scala3-example-project/blob/main/README.md).

### Create scala fullstack project

#### script

##### create server
sbt new scala/scala3.g8
mkdir server
mv src server/

##### create client
npm create vite@4.1.0
-> client
-> vanilla
-> Javascript

cd client
npm install
npm run dev

cd client
npm install -D @scala-js/vite-plugin-scalajs@1.0.0

#### Define multimodule project in build.sbt

### Run

npm run dev

sbt server/run

[//]: # (sbt --batch -Dsbt.server.forcestart=true client/compile)

[//]: # (sbt --batch -Dsbt.server.forcestart=true client/run)

## References:
https://idiomaticsoft.com/post/2023-12-12-fullstack/
https://daily.dev/blog/build-rest-api-with-scala-play-framework
https://www.scala-js.org/doc/project/cross-build.html
https://github.com/sbt/sbt/issues/6777
https://github.com/startbootstrap/startbootstrap-simple-sidebar