package no.ntnu.iir.idata2304.iot.apps.sensor.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.ntnu.iir.idata2304.iot.apps.sensor.mqtt.MqttGateway;

@Slf4j
@Component
@RequiredArgsConstructor
public class CpuTemperaturePoller {
  private final MqttGateway mqttGateway;

  private static final Path CPU_TEMP_FILE_PATH = Path.of("/sys/class/thermal/thermal_zone0/temp");
    
  @Scheduled(cron = "0/5 * * * * *")
	public void readTemperature() throws IOException {
    try {
      if (!Files.exists(CPU_TEMP_FILE_PATH)) {
        log.warn(
          "Could not find the file to read the CPU temperature from! ({})", 
          CPU_TEMP_FILE_PATH
        );
        
        return;
      }

      // reads the temperature file as a string, replaces any non digit characters
      // and parses it as an integer
      int temperature = Integer.parseInt(
        Files.readString(CPU_TEMP_FILE_PATH).replace("\\D", "")
      );
      Float temperatureValue = (temperature / 1000f);
      this.mqttGateway.sendToMqtt(temperatureValue.toString().getBytes());
    } catch (Exception e) {
      e.printStackTrace();
    }
	}
}
