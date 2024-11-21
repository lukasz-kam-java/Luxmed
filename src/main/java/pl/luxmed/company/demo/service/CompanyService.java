package pl.luxmed.company.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.luxmed.company.demo.configuration.CommonValidator;
import pl.luxmed.company.demo.dto.CompanyDto;
import pl.luxmed.company.demo.entity.Company;
import pl.luxmed.company.demo.exception.BadRequestException;
import pl.luxmed.company.demo.exception.NotFoundException;
import pl.luxmed.company.demo.mapper.CompanyMapper;
import pl.luxmed.company.demo.repository.CompanyRepository;
import pl.luxmed.company.demo.service.validator.ServiceValidator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CommonValidator commonValidator;


    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        if (companies.isEmpty()) {
            throw new NotFoundException("No companies found");
        }
        return companies.stream().map(companyMapper::toDto).toList();
    }

    public CompanyDto getCompanyById(Integer companyId) {
        if (companyId == null) {
            throw new BadRequestException("Company Id not provided.");
        }
        return companyRepository
                .findById(companyId)
                .map(companyMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Company with the id=%s not found.", companyId)));
    }

    public CompanyDto saveCompany(CompanyDto companyDto, ServiceValidator serviceValidator) {
        serviceValidator.validate(companyDto, companyRepository);
        commonValidator.validateOrThrow(companyDto);
        return companyMapper.toDto(companyRepository.save(companyMapper.toEntity(companyDto)));
    }

    public void deleteCompany(Integer companyId) {
        if (companyId == null) {
            throw new BadRequestException("Company Id not provided.");
        }
        companyRepository
                .findById(companyId)
                .ifPresentOrElse(companyRepository::delete, () -> {
                    throw new NotFoundException(String.format("Company with id=%s has not been found.", companyId));
                });
    }


}
