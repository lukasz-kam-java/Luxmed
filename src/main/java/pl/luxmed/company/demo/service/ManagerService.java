package pl.luxmed.company.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.luxmed.company.demo.configuration.CommonValidator;
import pl.luxmed.company.demo.dto.ManagerDto;
import pl.luxmed.company.demo.entity.Manager;
import pl.luxmed.company.demo.exception.BadRequestException;
import pl.luxmed.company.demo.exception.NotFoundException;
import pl.luxmed.company.demo.mapper.ManagerMapper;
import pl.luxmed.company.demo.repository.ManagerRepository;
import pl.luxmed.company.demo.repository.ProjectRepository;
import pl.luxmed.company.demo.service.validator.ServiceValidator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerMapper managerMapper;
    private final ProjectRepository projectRepository;
    private final ManagerRepository managerRepository;
    private final CommonValidator commonValidator;


    public List<ManagerDto> getAllManages() {
        List<Manager> managers = managerRepository.findAll();
        if (managers.isEmpty()) {
            throw new NotFoundException("No managers found");
        }
        return managers
                .stream()
                .map(managerMapper::toDto).toList();
    }

    public ManagerDto getManagerById(Integer managerId) {
        if (managerId == null) {
            throw new BadRequestException("Manager Id not provided.");
        }
        return managerRepository
                .findById(managerId)
                .map(managerMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Manager with the id=%s not found.", managerId)));
    }

    public ManagerDto saveManager(ManagerDto managerDto, ServiceValidator serviceValidator) {

        serviceValidator.validate(managerDto, managerRepository);
        commonValidator.validateOrThrow(managerDto);

        Manager manager = managerMapper.toEntity(managerDto);
        return managerMapper.toDto(managerRepository.save(manager));
    }

    public void delete(Integer mangerId) {
        if (mangerId == null) {
            throw new BadRequestException("Manager Id not provided.");
        }
        managerRepository
                .findById(mangerId)
                .ifPresentOrElse(managerRepository::delete, () -> {
                    throw new NotFoundException(String.format("Manager with id=%s has not been found.", mangerId));
                });
    }
}
