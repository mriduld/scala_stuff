package model

case class User(id: String, name: String, email: String, password: String, tweetCount: Long)
case class Twit(id: String, twit: String, dt: Long)
