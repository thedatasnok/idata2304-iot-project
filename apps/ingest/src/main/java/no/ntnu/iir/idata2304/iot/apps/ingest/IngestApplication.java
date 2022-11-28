package no.ntnu.iir.idata2304.iot.apps.ingest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

import no.ntnu.iir.idata2304.iot.apps.ingest.config.CustomHints;

@SpringBootApplication
@ImportRuntimeHints(CustomHints.class)
public class IngestApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(IngestApplication.class, args);
  }

}
