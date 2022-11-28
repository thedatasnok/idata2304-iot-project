package no.ntnu.iir.idata2304.iot.apps.ingest.config;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.lang.Nullable;

/**
 * Custom hints that are used by Spring AOT.
 * Some changes are require to make our SQL migration files & Liquibase to work.
 * Other configuration can be found in the projects resource directory, specifically the 
 * reflection-config.json and resources-config.json files.
 */
public class CustomHints implements RuntimeHintsRegistrar {
  
  @Override
  public void registerHints(RuntimeHints hints, @Nullable ClassLoader classLoader) {
    // include the SQL migration files in the native image
    hints.resources().registerPattern("*.sql"); 

    // include the frontend static files in the native image
    hints.resources().registerPattern("static/*");
  }

}
