package ask.example.yandex.architecture.sprint1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

/**
 * Java record representing temperature response data
 */
public record TemperatureResponse(
        @JsonProperty("value") double value,
        @JsonProperty("unit") String unit,
        @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
        @JsonProperty("timestamp") ZonedDateTime timestamp,
        @JsonProperty("location") String location,
        @JsonProperty("status") String status,
        @JsonProperty("sensor_id") String sensorId,
        @JsonProperty("sensor_type") String sensorType,
        @JsonProperty("description") String description
) {
}
