package ask.example.yandex.architecture.sprint1.service;

import ask.example.yandex.architecture.sprint1.client.DeviceCreateRequest
import ask.example.yandex.architecture.sprint1.client.DeviceResponse
import ask.example.yandex.architecture.sprint1.client.DeviceUpdateRequest
import ask.example.yandex.architecture.sprint1.client.MainAppClient
import org.springframework.stereotype.Service

@Service
class DeviceService(
    private val mainAppClient: MainAppClient
) {
    fun getAllSensors(): List<DeviceResponse>? {
        return mainAppClient.getSensors()
    }

    fun getSensorById(id: Int): DeviceResponse? {
        return mainAppClient.getSensorById(id);
    }

    fun createSensor(request: DeviceCreateRequest): DeviceResponse? {
        return mainAppClient.createSensor(request);
    }

    fun updateSensor(id: Int, request: DeviceUpdateRequest): DeviceResponse? {
        return mainAppClient.updateSensor(id, request);
    }

    fun deleteSensor(id: Int) {
        mainAppClient.deleteSensor(id);
    }

    fun updateSensorValue(id: Int, value: Double, status: String) {
        mainAppClient.updateSensorValue(id, value, status);
    }
}