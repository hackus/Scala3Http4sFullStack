package com.learn


import cats.effect.*
import com.comcast.ip4s.*
import com.learn.module.ProductDAO
import io.circe.generic.auto.*
import io.circe.syntax.*
import org.http4s.*
import org.http4s.circe.*
import org.http4s.dsl.io.*
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits.*
import org.http4s.server.Router


object Main extends IOApp.Simple {

  // 1: Create the product service
  val productService = HttpRoutes.of[IO] {
    case GET -> Root / "products" =>
      Ok(ProductDAO.findProducts().asJson)
  }
  // 2: Allocate a route to the service in the router
  val httpApp = Router("/api" -> productService).orNotFound

  // 3: Build the actual server
  val server = EmberServerBuilder
    .default[IO]
//    .withHost(ipv4"0.0.0.0")
    .withHost(host"0.0.0.0")
    .withPort(port"8080")
    .withHttpApp(httpApp)
    .build

//  def server = for {
////    postgres <- makePostgres
////    jobs <- JobsLive.resource[IO](postgres)
////    jobApi <- JobRoutes.resource[IO](jobs)
//    server <- EmberServerBuilder
//      .default[IO]
//      .withHost(ipv4"0.0.0.0")
//      .withPort(port"8081")
////      .withHttpApp(CORS(jobApi.routes.orNotFound))
//      .withHttpApp(httpApp)
//      .build
//  } yield server

  // 4: Launch the server in the application loop
  val run = for {
    _ <- server.allocated
    _ <- IO.never // this is needed so that the server keeps running
  } yield ()
}