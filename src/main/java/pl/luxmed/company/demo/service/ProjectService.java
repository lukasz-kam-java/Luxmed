package pl.luxmed.company.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.luxmed.company.demo.configuration.CommonValidator;
import pl.luxmed.company.demo.dto.ProjectDto;
import pl.luxmed.company.demo.entity.Manager;
import pl.luxmed.company.demo.entity.Project;
import pl.luxmed.company.demo.exception.BadRequestException;
import pl.luxmed.company.demo.exception.NotFoundException;
import pl.luxmed.company.demo.mapper.ProjectMapper;
import pl.luxmed.company.demo.repository.ManagerRepository;
import pl.luxmed.company.demo.repository.ProjectRepository;
import pl.luxmed.company.demo.service.validator.ServiceValidator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final ManagerRepository managerRepository;
    private final CommonValidator commonValidator;


    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        if (projects.isEmpty()) {
            throw new NotFoundException("No projects found");
        }
        return projects
                .stream()
                .map(projectMapper::toDto).toList();
    }

    public ProjectDto getProjectById(Integer projectId) {
        if (projectId == null) {
            throw new BadRequestException("Project Id not provided.");
        }
        return projectRepository
                .findById(projectId)
                .map(projectMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Project with the id=%s not found.", projectId)));
    }

    public ProjectDto saveProject(ProjectDto projectDto, ServiceValidator serviceValidator) {

        serviceValidator.validate(projectDto, projectRepository);
        commonValidator.validateOrThrow(projectDto);

        Manager manager = null;
        if (projectDto.getManagerId() != null) {
            manager = managerRepository
                    .findById(projectDto.getManagerId())
                    .orElseThrow(() -> new NotFoundException(String.format("Manager with id=%s has not been found.", projectDto.getManagerId())));
        }

        Project project = projectMapper.toEntity(projectDto);
        project.setManager(manager);

        return projectMapper.toDto(projectRepository.save(project));
    }

    public void delete(Integer projectId) {
        if (projectId == null) {
            throw new BadRequestException("Project Id not provided.");
        }
        projectRepository
                .findById(projectId)
                .ifPresentOrElse(projectRepository::delete, () -> {
                    throw new NotFoundException(String.format("Project with id=%s has not been found.", projectId));
                });
    }
}
