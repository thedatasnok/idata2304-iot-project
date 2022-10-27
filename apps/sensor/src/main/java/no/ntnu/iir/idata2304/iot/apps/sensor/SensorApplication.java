package no.ntnu.iir.idata2304.iot.apps.sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SensorApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(SensorApplication.class, args);
  }

}
