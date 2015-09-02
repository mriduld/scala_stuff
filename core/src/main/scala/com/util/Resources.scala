package com.util

object Resources {
  def using[A <: AutoCloseable, B](resource: A)(fn: A => B): B = try {
     fn(resource)
  } finally {
     if(resource != null)
        resource.close()
  }
}
