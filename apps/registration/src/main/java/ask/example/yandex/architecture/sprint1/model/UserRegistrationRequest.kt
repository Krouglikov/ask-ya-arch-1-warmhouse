package ask.example.yandex.architecture.sprint1.model

import jakarta.validation.constraints.*

data class UserRegistrationRequest(
    @field:NotBlank(message = "Name is required")
    val name: String,

    val formalId: String? = null,

    @field:Email(message = "Invalid email format")
    val email: String? = null,

    @field:Pattern(regexp = "^\\+7\\d{10}$", message = "Phone must be in +7XXXXXXXXXX format")
    val phone: String? = null
)