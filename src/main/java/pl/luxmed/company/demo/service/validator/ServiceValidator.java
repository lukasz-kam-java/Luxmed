package pl.luxmed.company.demo.service.validator;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.luxmed.company.demo.dto.IdentificableDto;

public interface ServiceValidator {

    void validate(IdentificableDto dto, JpaRepository jpa);


}
