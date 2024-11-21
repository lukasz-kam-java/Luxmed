package pl.luxmed.company.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.luxmed.company.demo.dto.CompanyDto;
import pl.luxmed.company.demo.entity.Company;

@Mapper(componentModel = "spring", uses = DepartmentMapper.class)
public interface CompanyMapper {

    Company toEntity(CompanyDto companyDto);
    CompanyDto toDto(Company company);


}