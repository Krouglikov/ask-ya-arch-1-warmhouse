package ask.example.yandex.architecture.sprint1.model

import EnumValue
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EnumValueValidator : ConstraintValidator<EnumValue, String> {

    private lateinit var annotation: EnumValue

    override fun initialize(annotation: EnumValue) {
        this.annotation = annotation
    }

    override fun isValid(value: String?, p1: ConstraintValidatorContext?): Boolean {
        val enumClass = annotation.enumClass.java
        return enumClass.isEnum && enumClass.enumConstants.any { it.toString() == value }
    }

}