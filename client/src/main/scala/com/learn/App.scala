package com.learn

import scala.scalajs.js
import scala.concurrent.ExecutionContext.Implicits.global
import js.Thenable.Implicits.*
import org.scalajs.dom
import org.scalajs.dom.document
import io.circe.syntax.*
import io.circe.generic.auto.*
import io.circe.parser.parse
import com.learn.Product

// We redefine the Product class here. This can be done using a shared project
// but we keep it that way for simplicity reasons.
//case class Product(code: String, description: String, price: Double)

@main
def HelloWorld(): Unit =
  println("Hello World!")
  val responseText = for {
    response <- dom.fetch("http://localhost:5173/api/products")
    text <- response.text()
  } yield {
    text
  }
  var app = document.getElementById("app")
  for {
    text <- responseText
  } yield {
    val products = parse(text).flatMap(_.as[List[Product]]).toOption.get
    app.innerHTML =
      """<table class="table"><thead><tr><th>Code</th><th>Desc</th><th>Price</th></tr></thead><tbody>""" + products
        .map(x =>
          s"<tr><td>${x.code}</td><td>${x.description}</td><td>${x.price}</td></tr>"
        )
        .mkString + "</tbody></table>"
  }
