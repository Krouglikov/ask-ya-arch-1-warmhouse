package ask.example.yandex.architecture.sprint1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TemperatureResponse(
        float value,
        String unit,
        @JsonProperty("timestamp") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") String timestamp,
        String location,
        String status,
        String sensorId,
        String sensorType,
        String description) {
}
