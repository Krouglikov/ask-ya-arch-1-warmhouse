package ask.example.yandex.architecture.sprint1.controller

import ask.example.yandex.architecture.sprint1.model.*
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/housings")
class HousingController {

    @PostMapping
    fun registerHousing(@Valid @RequestBody request: HousingRegistrationRequest): HousingRegistrationResponse {
        // Реализация логики регистрации помещения
        return HousingRegistrationResponse("housing-67890")
    }
}