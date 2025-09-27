package ask.example.yandex.architecture.sprint1.controller

import ask.example.yandex.architecture.sprint1.model.*
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/users")
class UserController {

    @PostMapping
    fun registerUser(@Valid @RequestBody request: UserRegistrationRequest): UserRegistrationResponse {
        // Реализация логики регистрации пользователя
        return UserRegistrationResponse("user-12345")
    }
}