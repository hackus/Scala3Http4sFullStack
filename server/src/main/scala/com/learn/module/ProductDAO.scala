package com.learn.module

import com.learn.Product

//case class Product(code: String, description: String, price: Double)

object ProductDAO:

  def findProducts(): List[Product] =
    List(
      Product("CODE1", "Product 1", 10),
      Product("CODE2", "Product 2", 130),
      Product("CODE3", "Product 3", 200),
      Product("CODE4", "Product 4", 200),
      Product("CODE5", "Product 5", 200)
    )