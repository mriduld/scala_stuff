package hbase

import model.User
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class UserDaoTest extends FunSuite with MockFactory {
  test("Test Mock UserDao") {
     val userDao = stub[UserDao]
     userDao.getUser _ when "test"  returns None
     userDao.getUser _ when "id-1"  returns Some(User("id-1", "test-user", "test@test.com", "password", 1L))
     val user1 = userDao.getUser("test")
     val user2 = userDao.getUser("id-1")
     assert(user1 == None)
     assert(user2.get.id == "id-1")
  }

  test("Actual Put to Hbase") {
     UserDao.addUser("HMS_Surprise", "Patrick O' Brian", "aubrey@sea.com", "abc123")
  }

  test("Get All Users") {
    UserDao.getUsers.foreach(println)
  }
}
