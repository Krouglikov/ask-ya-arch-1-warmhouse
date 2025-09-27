package ask.example.yandex.architecture.sprint1.controller

import ask.example.yandex.architecture.sprint1.client.DeviceCreateRequest
import ask.example.yandex.architecture.sprint1.model.DeviceRegistrationRequest
import ask.example.yandex.architecture.sprint1.model.DeviceRegistrationResponse
import ask.example.yandex.architecture.sprint1.service.DeviceService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/devices")
class DeviceController(
    private val deviceService: DeviceService
) {

    @PostMapping
    fun registerDevice(@Valid @RequestBody request: DeviceRegistrationRequest): DeviceRegistrationResponse {
        val response = deviceService.createSensor(
            DeviceCreateRequest(request.deviceName, request.deviceType, request.housingId, unit(request.protocol))
        )
        if (response != null) {
            return DeviceRegistrationResponse(response.id)
        } else {
            throw Exception(
                "Failed to create sensor with name ${request.deviceName} and type ${request.deviceType}"
            )
        }
    }

    private fun unit(protocol: String): String {
        return "℃"
    }

}