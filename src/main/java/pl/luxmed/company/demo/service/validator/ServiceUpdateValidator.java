package pl.luxmed.company.demo.service.validator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.luxmed.company.demo.dto.IdentificableDto;
import pl.luxmed.company.demo.exception.BadRequestException;
import pl.luxmed.company.demo.exception.NotFoundException;

@Component
public class ServiceUpdateValidator implements ServiceValidator {
    @Override
    public void validate(IdentificableDto dto, JpaRepository jpa) {
        if(dto.getId()==null) {
            throw new BadRequestException("No entity id provided");
        }
        if(jpa.findById(dto.getId()).isEmpty()) {
            throw new NotFoundException("Entity with id "+dto.getId()+"not found, hence can not be updated");
        }
    }
}
