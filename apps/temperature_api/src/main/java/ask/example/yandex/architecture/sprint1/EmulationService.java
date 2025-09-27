package ask.example.yandex.architecture.sprint1;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Random;

@Service
public class EmulationService {
    private static final Random RANDOM = new Random();
    private static final String[] UNITS = {"C", "F"};
    private static final String[] STATUSES = {"active", "inactive", "maintenance"};
    private static final String[] SENSOR_TYPES = {"temperature", "humidity", "pressure"};
    private static final String[] LOCATIONS = {"Living Room", "Bedroom", "Kitchen"};

    /**
     * Generates a TemperatureResponse with random values for testing/demo purposes
     */
    public TemperatureResponse generateRandomForLocation(String location) {
        double value = -50.0 + (100.0 * RANDOM.nextDouble()); // -50 to +50 range
        String unit = UNITS[RANDOM.nextInt(UNITS.length)];
        ZonedDateTime timestamp = ZonedDateTime.now();
        String status = STATUSES[RANDOM.nextInt(STATUSES.length)];
        String sensorId = Integer.toString(RANDOM.nextInt(3));
        String sensorType = SENSOR_TYPES[0];
        String description = "Measurement " + (RANDOM.nextInt(1000)) +
                (RANDOM.nextBoolean() ? " with warning" : "");

        return new TemperatureResponse(
                value, unit, timestamp, defaultLocation(location, sensorId), status, sensorId, sensorType, description
        );
    }

    /**
     * Generates a TemperatureResponse with random values for testing/demo purposes
     */
    public TemperatureResponse generateRandomForId(String id) {
        String location = LOCATIONS[RANDOM.nextInt(LOCATIONS.length)];
        double value = -50.0 + (100.0 * RANDOM.nextDouble()); // -50 to +50 range
        String unit = UNITS[RANDOM.nextInt(UNITS.length)];
        ZonedDateTime timestamp = ZonedDateTime.now();
        String status = STATUSES[RANDOM.nextInt(STATUSES.length)];
        String sensorType = SENSOR_TYPES[0];
        String description = "Measurement " + (RANDOM.nextInt(1000)) +
                (RANDOM.nextBoolean() ? " with warning" : "");

        return new TemperatureResponse(
                value, unit, timestamp, defaultSensorId(id, location), status, id, sensorType, description
        );
    }

    // If no location is provided, use a default based on sensor ID
    private String defaultLocation(String location, String sensorId) {
        if (location == null || location.isEmpty()) {
            return switch (sensorId) {
                case "1" -> "Living Room";
                case "2" -> "Bedroom";
                case "3" -> "Kitchen";
                default -> "Unknown";
            };
        } else {
            return location;
        }
    }

    // If no sensor ID is provided, generate one based on location
    private String defaultSensorId(String sensorId, String location) {
        if (sensorId == null || sensorId.isEmpty()) {
            return switch (location) {
                case "Living Room" -> "1";
                case "Bedroom" -> "2";
                case "Kitchen" -> "3";
                default -> "0";
            };
        } else {
            return sensorId;
        }
    }
}
