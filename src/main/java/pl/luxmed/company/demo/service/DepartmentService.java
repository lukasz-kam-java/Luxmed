package pl.luxmed.company.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.luxmed.company.demo.configuration.CommonValidator;
import pl.luxmed.company.demo.dto.DepartmentDto;
import pl.luxmed.company.demo.entity.Company;
import pl.luxmed.company.demo.entity.Department;
import pl.luxmed.company.demo.exception.BadRequestException;
import pl.luxmed.company.demo.exception.NotFoundException;
import pl.luxmed.company.demo.mapper.DepartmentMapper;
import pl.luxmed.company.demo.repository.CompanyRepository;
import pl.luxmed.company.demo.repository.DepartmentRepository;
import pl.luxmed.company.demo.service.validator.ServiceValidator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final CommonValidator commonValidator;
    private final CompanyRepository companyRepository;

    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        if (departments.isEmpty()) {
            throw new NotFoundException("No departments found");
        }
        return departments
                .stream()
                .map(departmentMapper::toDto).toList();
    }

    public DepartmentDto getDepartmentById(Integer departmentId) {
        if (departmentId == null) {
            throw new BadRequestException("Department Id not provided.");
        }
        return departmentRepository
                .findById(departmentId)
                .map(departmentMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Department with the id=%s not found.", departmentId)));
    }

    public DepartmentDto saveDepartment(DepartmentDto departmentDto, ServiceValidator serviceValidator) {

        serviceValidator.validate(departmentDto, departmentRepository);
        commonValidator.validateOrThrow(departmentDto);

        Company company = companyRepository
                .findById(departmentDto.getCompanyId())
                .orElseThrow(() -> new NotFoundException(String.format("Company with id=%s has not been found.", departmentDto.getCompanyId())));

        Department department = departmentMapper.toEntity(departmentDto);
        department.setCompany(company);

        return departmentMapper.toDto(departmentRepository.save(department));
    }

    public void delete(Integer departmentId) {
        if (departmentId == null) {
            throw new BadRequestException("Department Id not provided.");
        }
        departmentRepository
                .findById(departmentId)
                .ifPresentOrElse(departmentRepository::delete, () -> {
                    throw new NotFoundException(String.format("Department with id=%s has not been found.", departmentId));
                });
    }


}
