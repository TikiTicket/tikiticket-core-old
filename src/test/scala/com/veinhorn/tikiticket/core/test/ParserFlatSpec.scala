package com.veinhorn.tikiticket.core.test

import java.nio.file.{Files, Paths}

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by veinhorn on 25.12.16.
  */
class ParserFlatSpec extends FlatSpec with Matchers {
  /** Loads HTML string from file from html folder */
  def loadHtml(fileName: String): String =
    new String(Files.readAllBytes(Paths.get(s"html/$fileName")))
}
