package pl.luxmed.company.demo.mapper;

import org.mapstruct.Mapper;
import pl.luxmed.company.demo.dto.ManagerDto;
import pl.luxmed.company.demo.dto.TeamDto;
import pl.luxmed.company.demo.entity.Manager;
import pl.luxmed.company.demo.entity.Team;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

    Manager toEntity(ManagerDto managerDto);
    ManagerDto toDto(Manager manager);

}