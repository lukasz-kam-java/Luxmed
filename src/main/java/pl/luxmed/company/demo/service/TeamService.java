package pl.luxmed.company.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.luxmed.company.demo.configuration.CommonValidator;
import pl.luxmed.company.demo.dto.TeamDto;
import pl.luxmed.company.demo.entity.Department;
import pl.luxmed.company.demo.entity.Project;
import pl.luxmed.company.demo.entity.Team;
import pl.luxmed.company.demo.exception.BadRequestException;
import pl.luxmed.company.demo.exception.NotFoundException;
import pl.luxmed.company.demo.mapper.TeamMapper;
import pl.luxmed.company.demo.repository.DepartmentRepository;
import pl.luxmed.company.demo.repository.ProjectRepository;
import pl.luxmed.company.demo.repository.TeamRepository;
import pl.luxmed.company.demo.service.validator.ServiceValidator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamService {

    private final DepartmentRepository departmentRepository;
    private final TeamMapper teamMapper;
    private final ProjectRepository projectRepository;
    private final CommonValidator commonValidator;
    private final TeamRepository teamRepository;

    public List<TeamDto> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        if (teams.isEmpty()) {
            throw new NotFoundException("No teams found");
        }
        return teams
                .stream()
                .map(teamMapper::toDto).toList();
    }

    public TeamDto getTeamById(Integer teamId) {
        if (teamId == null) {
            throw new BadRequestException("Team Id not provided.");
        }
        return teamRepository
                .findById(teamId)
                .map(teamMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Team with the id=%s not found.", teamId)));
    }

    public TeamDto saveTeam(TeamDto teamDto, ServiceValidator serviceValidator) {

        serviceValidator.validate(teamDto, teamRepository);
        commonValidator.validateOrThrow(teamDto);

        Department department = departmentRepository
                .findById(teamDto.getDepartmentId())
                .orElseThrow(() -> new NotFoundException(String.format("Department with id=%s has not been found.", teamDto.getDepartmentId())));

        Project project = null;

        if (teamDto.getProjectId() != null) {
            project = projectRepository
                    .findById(teamDto.getProjectId())
                    .orElseThrow(() -> new NotFoundException(String.format("Project with id=%s has not been found.", teamDto.getProjectId())));
        }

        Team team = teamMapper.toEntity(teamDto);
        team.setDepartment(department);
        team.setProject(project);

        return teamMapper.toDto(teamRepository.save(team));
    }

    public void delete(Integer teamId) {
        if (teamId == null) {
            throw new BadRequestException("Team Id not provided.");
        }
        teamRepository
                .findById(teamId)
                .ifPresentOrElse(teamRepository::delete, () -> {
                    throw new NotFoundException(String.format("Project with id=%s has not been found.", teamId));
                });
    }
}
