package pl.luxmed.company.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.luxmed.company.demo.dto.ProjectDto;
import pl.luxmed.company.demo.entity.Manager;
import pl.luxmed.company.demo.entity.Project;

@Mapper(componentModel = "spring", uses = ManagerMapper.class)
public interface ProjectMapper {

    Project toEntity(ProjectDto projectDto);
    @Mapping(source = "manager", target = "managerId", qualifiedByName = "mapManagerToManagerId")
    ProjectDto toDto(Project project);

    @Named("mapManagerToManagerId")
    default Integer mapProjectToProjectId(Manager managerId) {
        if (managerId == null) {
            return null;
        }
        return managerId.getId();
    }

}