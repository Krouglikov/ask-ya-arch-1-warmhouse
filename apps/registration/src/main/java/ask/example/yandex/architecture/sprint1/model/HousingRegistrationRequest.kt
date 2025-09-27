package ask.example.yandex.architecture.sprint1.model

import jakarta.validation.constraints.*

data class HousingRegistrationRequest(
    @field:NotBlank(message = "User ID is required")
    val userId: String,

    @field:NotBlank(message = "Address is required")
    val address: String
)