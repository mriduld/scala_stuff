import avro.test.Person
import org.apache.avro.specific.SpecificDatumWriter
import org.junit.Test
import org.apache.avro.file.DataFileWriter

class AvroTest {

  @Test
  def createPersonFile(){
    println("Avro Test")
    val fileWriter = new DataFileWriter(new SpecificDatumWriter[Person]())

  }
}
