package pl.luxmed.company.demo.configuration;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CommonValidator {

    ValidatorFactory factory;
    Validator validator;

    public CommonValidator() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public void validateOrThrow(Object objectToValidate) {
        Set<ConstraintViolation<Object>> violations = validator.validate(objectToValidate);

        if (!violations.isEmpty()) {
            throw new RuntimeException("Walidacja nie powiodła się:" +
                    violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(",")));
        }
    }

}
