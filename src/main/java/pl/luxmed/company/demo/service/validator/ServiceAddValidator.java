package pl.luxmed.company.demo.service.validator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.luxmed.company.demo.dto.IdentificableDto;
import pl.luxmed.company.demo.exception.BadRequestException;

@Component
public class ServiceAddValidator implements ServiceValidator {
    @Override
    public void validate(IdentificableDto dto, JpaRepository jpa) {
        if(dto.getId() != null) {
            throw new BadRequestException("Id not allowed when creating new entity");
        }
    }
}
