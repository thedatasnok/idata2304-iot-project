package no.ntnu.iir.idata2304.iot.apps.ingest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import no.ntnu.iir.idata2304.iot.apps.ingest.model.Sensor;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SensorRepositoryTests {
  
  @Autowired
  private SensorRepository repository;

  @Test
  void storingSensorsWorks() {
    var storedSensor = this.repository.save(new Sensor(1, "datasnok", "room"));
    
    assertEquals(1, storedSensor.getId());
    assertEquals("datasnok", storedSensor.getPlace());
    assertEquals("room", storedSensor.getRoom());
    assertEquals(1, repository.findAll().size());

    var anotherSensor = this.repository.save(new Sensor(1, "datasnok", "livid-room"));

    assertEquals(1, anotherSensor.getId());
    assertEquals("datasnok", anotherSensor.getPlace());
    assertEquals("livid-room", anotherSensor.getRoom());
    assertEquals(2, repository.findAll().size());
  }

}
