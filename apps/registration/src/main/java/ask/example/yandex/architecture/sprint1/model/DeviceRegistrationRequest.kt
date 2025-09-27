package ask.example.yandex.architecture.sprint1.model

import EnumValue
import jakarta.validation.constraints.*

data class DeviceRegistrationRequest(
    @field:NotBlank(message = "User ID is required")
    val userId: String,

    @field:NotBlank(message = "Housing ID is required")
    val housingId: String,

    @field:NotBlank(message = "Device name is required")
    val deviceName: String,

    @field:NotNull(message = "Device type is required")
    @field:EnumValue(enumClass = DeviceType::class, message = "Invalid device type")
    val deviceType: String,

    @field:NotBlank(message = "Protocol is required")
    val protocol: String
)

