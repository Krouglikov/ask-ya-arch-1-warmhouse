package ask.example.yandex.architecture.sprint1.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.io.Serializable

/**
 * Client for main app
 */
@Service
class MainAppClient(
    private val restTemplate: RestTemplate,
    @param:Value("\${smarthome.api.url}") private val baseUrl: String
) {
    fun getSensors(): List<DeviceResponse>? {
        val response: ResponseEntity<List<DeviceResponse>> =
            restTemplate.exchange<List<DeviceResponse>>(
                "$baseUrl/sensors",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<DeviceResponse>>() {}
            )
        return response.body
    }

    fun getSensorById(id: Int): DeviceResponse? {
        val response = restTemplate.exchange(
            buildUri("/sensors/{id}", id),
            HttpMethod.GET,
            null,
            DeviceResponse::class.java,
            id
        )
        return response.body
    }

    fun createSensor(request: DeviceCreateRequest): DeviceResponse? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val response = restTemplate.exchange(
            "$baseUrl/sensors",
            HttpMethod.POST,
            HttpEntity(request, headers),
            DeviceResponse::class.java
        )
        return response.body
    }

    fun updateSensor(id: Int, request: DeviceUpdateRequest): DeviceResponse? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val response = restTemplate.exchange(
            buildUri("/sensors/{id}", id),
            HttpMethod.PUT,
            HttpEntity(request, headers),
            DeviceResponse::class.java,
            id
        )
        return response.body
    }

    fun deleteSensor(id: Int) {
        restTemplate.delete(buildUri("/sensors/{id}", id), id)
    }

    fun updateSensorValue(id: Int, value: Double, status: String) {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val body: Map<String, Serializable> = java.util.Map.of(
            "value", value,
            "status", status
        )

        restTemplate.exchange(
            buildUri("/sensors/{id}/value", id),
            HttpMethod.PATCH,
            HttpEntity(body, headers),
            Void::class.java,
            id
        )
    }

    private fun <T> responseType(responseType: Class<T>): ParameterizedTypeReference<T> {
        return ParameterizedTypeReference.forType(responseType)
    }

    private fun buildUri(path: String, vararg vars: Any): String {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
            .path(path)
            .buildAndExpand(*vars)
            .toString()
    }
}
