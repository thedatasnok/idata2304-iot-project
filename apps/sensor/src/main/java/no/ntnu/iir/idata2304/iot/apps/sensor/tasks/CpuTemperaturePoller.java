package no.ntnu.iir.idata2304.iot.apps.sensor.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import no.ntnu.iir.idata2304.iot.apps.sensor.mqtt.MqttGateway;

@Component
@RequiredArgsConstructor
public class CpuTemperaturePoller {

  private final MqttGateway mqttGateway;
    
  @Scheduled(cron = "0/5 * * * * *")
	public void readTemperature() throws IOException {
    try {
      // TODO: Read the correct file sys/class/thermal/thermal_zone0/temp
      var path = Path.of(this.getClass().getClassLoader().getResource("number.txt").toURI());
      int temperature = Integer.parseInt(Files.readString(path));
      Float temperatureValue = (temperature / 1000f);
      this.mqttGateway.sendToMqtt(temperatureValue.toString().getBytes());
    } catch (Exception e) {
      e.printStackTrace();
    }
	}
}
