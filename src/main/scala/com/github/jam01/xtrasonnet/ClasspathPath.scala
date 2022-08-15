package com.github.jam01.xtrasonnet

/*-
 * Copyright 2022 Jose Montoya.
 *
 * Licensed under the Elastic License 2.0; you may not use this file except in
 * compliance with the Elastic License 2.0.
 */

import sjsonnet.{Importer, Path}

import scala.collection.mutable
import scala.io.Source
import scala.util.Using

object ClasspathPath {
  val root = new ClasspathPath("")
  val resolver = new Importer {
    override def resolve(docBase: Path, importName: String): Option[Path] = docBase match {
      case ClasspathPath("") => Some(ClasspathPath(importName))
      case ClasspathPath(_) => Some(docBase / importName)
      case _ => None
    }

    override def read(path: Path): Option[String] = {
      val p = "/" + path.asInstanceOf[ClasspathPath].path
      getClass.getResource(p) match {
        case null => None
        case _ => Using.resource(getClass.getResourceAsStream(p)) { stream =>
          Some(Source.fromInputStream(stream).mkString)
        }
      }
    }
  }
}

/**
 * An implementation of sjsonnet.Path that works with the JVM's ClassPath
 *
 * @param path name of the desired resource
 */
case class ClasspathPath(path: String) extends Path {
  override def parent(): ClasspathPath = {
    var parent = path.split('/').dropRight(1).mkString("/")
    if (!"".equals(parent)) parent = parent + "/"
    ClasspathPath(parent)
  }

  override def segmentCount(): Int = path.split('/').length

  override def last: String = path.split('/').last

  override def /(s: String): Path = ClasspathPath(path + "/" + s)

  override def renderOffsetStr(offset: Int, loadedFileContents: mutable.HashMap[Path, Array[Int]]): String =
    path + ":" + offset

  override def equals(other: Any): Boolean = other match {
    case other: ClasspathPath => path.equals(other.path)
    case _ => false
  }

  override def hashCode: Int = path.hashCode()

  override def relativeToString(p: Path): String = {
    if (this.parent().equals(p.asInstanceOf[ClasspathPath].parent())) last else toString
  }

  override def toString: String = path
}
