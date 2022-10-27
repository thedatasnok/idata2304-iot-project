package no.ntnu.iir.idata2304.iot.apps.sensor.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import no.ntnu.iir.idata2304.iot.apps.sensor.mqtt.MqttGateway;

@Component
@RequiredArgsConstructor
public class CpuTemperaturePoller {

  private final MqttGateway mqttGateway;
    
  @Scheduled(fixedRate = 5000)
	public void readTemperature() throws IOException {
    // TODO: Read the correct file sys/class/thermal/thermal_zone0/temp
		Path path = Paths.get("apps/sensor/src/main/resources/number.txt");
    int temperature = Integer.valueOf(Files.readAllLines(path).get(0));
    Float temperatureValue = (temperature / 1000f);
    this.mqttGateway.sendToMqtt(temperatureValue.toString().getBytes());
	}
}
