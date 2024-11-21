package pl.luxmed.company.demo.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.luxmed.company.demo.dto.DepartmentDto;
import pl.luxmed.company.demo.entity.Company;
import pl.luxmed.company.demo.entity.Department;

@Mapper(componentModel = "spring", uses = TeamMapper.class )
public interface DepartmentMapper {
    Department toEntity(DepartmentDto departmentDto);
    @Mapping(source = "company", target = "companyId", qualifiedByName = "mapCompanyToCompanyId")
    DepartmentDto toDto(Department department);

    @Named("mapCompanyToCompanyId")
    default Integer mapCompanyToCompanyId(Company company) {
        if (company == null) {
            return null;
        }
        return company.getId();
    }
}
