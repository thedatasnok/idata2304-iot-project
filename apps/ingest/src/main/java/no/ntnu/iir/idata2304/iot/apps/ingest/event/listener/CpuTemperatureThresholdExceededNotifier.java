package no.ntnu.iir.idata2304.iot.apps.ingest.event.listener;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import no.ntnu.iir.idata2304.iot.apps.ingest.event.model.CpuTemperatureMeasurementCreatedEvent;

@Slf4j
@Component
public class CpuTemperatureThresholdExceededNotifier {

  @Value("${ingest.notifier.enabled}")
  private boolean enabled;

  @Value("${ingest.notifier.discord.webhook.url}")
  private String discordWebhookUrl;

  @Value("${ingest.notifier.cpu-temperature.threshold}")
  private Float threshold; 

  private RestTemplate restTemplate = new RestTemplate();

  /**
   * Event listener that will analyze contents of a CPU temperature measurement event and notify 
   * a Discord webhook if the threshold is exceeded.
   * 
   * @param event the event to analyze
   */
  @EventListener(classes = CpuTemperatureMeasurementCreatedEvent.class)
  @RegisterReflectionForBinding(DiscordWebhookMessage.class) // allows for serialization in a native image
  public void onCpuMeasurementCreated(CpuTemperatureMeasurementCreatedEvent event) {
    var measurement = event.getMeasurement();
    if (!this.enabled || measurement.getTemperature() < this.threshold) return;

    var message = new DiscordWebhookMessage(
      String.format(
        "Sensor: %s/%s/%s logged a CPU temperature of %.2f°C, exceeding the threshold of %.2f°C!", 
        measurement.getSensorPlace(),
        measurement.getSensorRoom(),
        measurement.getSensorId(),
        measurement.getTemperature(),
        this.threshold
      )
    );

    try {
      this.restTemplate.postForObject(this.discordWebhookUrl, message, Object.class);
    } catch (Exception e) {
      log.error("Notifier failed to send POST request to configured WebHook: {}", e.getMessage());
    }
  }

  /**
   * Simple wrapper around the JSON object that is sent to the Discord WebHook.
   * We only need the content part to send a message. In case of further expansion, check the
   * Discord WebHook documentation for more information.
   */
  @Data
  @AllArgsConstructor
  class DiscordWebhookMessage {
    private String content;
  }

}
