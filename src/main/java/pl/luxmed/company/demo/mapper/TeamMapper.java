package pl.luxmed.company.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.luxmed.company.demo.dto.TeamDto;
import pl.luxmed.company.demo.entity.Department;
import pl.luxmed.company.demo.entity.Project;
import pl.luxmed.company.demo.entity.Team;

@Mapper(componentModel = "spring", uses = ProjectMapper.class)
public interface TeamMapper {

    Team toEntity(TeamDto teamDto);

    @Mapping(source = "department", target = "departmentId", qualifiedByName = "mapTeamToTeamId")
    @Mapping(source = "project", target = "projectId", qualifiedByName = "mapProjectToProjectId")
    TeamDto toDto(Team team);

    @Named("mapTeamToTeamId")
    default Integer mapTeamToTeamId(Department department) {
        if (department == null) {
            return null;
        }
        return department.getId();
    }

    @Named("mapProjectToProjectId")
    default Integer mapProjectToProjectId(Project project) {
        if (project == null) {
            return null;
        }
        return project.getId();
    }

}