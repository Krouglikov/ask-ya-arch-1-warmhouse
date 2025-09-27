package ask.example.yandex.architecture.sprint1.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Map<String, Any> {
        val errors = mutableMapOf<String, List<String>>()
        ex.bindingResult.fieldErrors.forEach { error ->
            val list = errors.computeIfAbsent(error.field) { mutableListOf() } as MutableList<String>
            list.add(error.defaultMessage!!)
        }
        return mapOf("error" to errors)
    }
}