package ask.example.yandex.architecture.sprint1.api;

import ask.example.yandex.architecture.sprint1.model.TemperatureResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class TemperatureController {

    private final TemperatureServiceClient temperatureServiceClient;

    @Autowired
    public TemperatureController(TemperatureServiceClient temperatureServiceClient) {
        this.temperatureServiceClient = temperatureServiceClient;
    }

    @GetMapping("/temperature")
    public TemperatureResponse getTemperature(@RequestParam String location) {
        try {
            log.info("Get temperature by location: {}", location);
            return temperatureServiceClient.getTemperatureByLocation(location);
        } catch (Exception e) {
            throw new ApiException("Failed to retrieve temperature data", e);
        }
    }

    @GetMapping("/temperature/{sensorId}")
    public TemperatureResponse getTemperatureById(@PathVariable String sensorId) {
        try {
            log.info("Get temperature by sensor id: {}", sensorId);
            return temperatureServiceClient.getTemperatureBySensorId(sensorId);
        } catch (Exception e) {
            throw new ApiException("Failed to retrieve temperature data", e);
        }
    }
}