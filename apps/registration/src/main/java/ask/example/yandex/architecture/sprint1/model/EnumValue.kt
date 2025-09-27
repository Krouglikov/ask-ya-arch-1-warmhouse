import ask.example.yandex.architecture.sprint1.model.EnumValueValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EnumValueValidator::class])
annotation class EnumValue(
    val enumClass: KClass<*>,
    val message: String = "Invalid enum value",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Payload>> = []
)


