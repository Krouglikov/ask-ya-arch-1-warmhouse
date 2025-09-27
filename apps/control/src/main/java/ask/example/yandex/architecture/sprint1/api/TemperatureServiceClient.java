package ask.example.yandex.architecture.sprint1.api;

import ask.example.yandex.architecture.sprint1.model.TemperatureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Service
public class TemperatureServiceClient {

    private final RestTemplate restTemplate;
    private final String externalApiUrl;

    public TemperatureServiceClient(
            @Autowired RestTemplate restTemplate,
            @Value("${external.api.url}") String url) {
        this.restTemplate = restTemplate;
        this.externalApiUrl = url;
    }

    public TemperatureResponse getTemperatureByLocation(String location) {
        URI uri = UriComponentsBuilder.fromHttpUrl(externalApiUrl + "/temperature")
                .queryParam("location", location)
                .build()
                .toUri();

        ResponseEntity<TemperatureResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                createHttpEntity(),
                TemperatureResponse.class
        );

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("External API error: " + response.getStatusCode());
        }

        return response.getBody();
    }

    public TemperatureResponse getTemperatureBySensorId(String sensorId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(externalApiUrl + "/temperature/" + sensorId)
                .build()
                .toUri();

        ResponseEntity<TemperatureResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                createHttpEntity(),
                TemperatureResponse.class
        );

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("External API error: " + response.getStatusCode());
        }

        return response.getBody();
    }

    private HttpEntity<?> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        // Добавьте авторизацию при необходимости:
        // headers.setBearerAuth("your-access-token");
        return new HttpEntity<>(headers);
    }
}
