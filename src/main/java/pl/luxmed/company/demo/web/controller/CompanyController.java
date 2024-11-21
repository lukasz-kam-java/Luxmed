package pl.luxmed.company.demo.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.luxmed.company.demo.dto.*;
import pl.luxmed.company.demo.service.*;
import pl.luxmed.company.demo.service.validator.ServiceAddValidator;
import pl.luxmed.company.demo.service.validator.ServiceUpdateValidator;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/company", produces = "application/json; charset=UTF-8")
public class CompanyController {

    private final CompanyService companyService;
    private final DepartmentService departmentService;
    private final TeamService teamService;
    private final ProjectService projectService;
    private final ManagerService managerService;
    private final ServiceUpdateValidator serviceUpdateValidator;
    private final ServiceAddValidator serviceAddValidator;

    /***
     * Company
     */

    @GetMapping
    List<CompanyDto> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "{companyId}")
    CompanyDto getCompanyById(@PathVariable("companyId") Integer companyId) {
        return companyService.getCompanyById(companyId);
    }

    @PostMapping
    CompanyDto addCompany(@RequestBody CompanyDto companyDto) {
        return companyService.saveCompany(companyDto, serviceAddValidator);
    }

    @PutMapping
    CompanyDto updateCompany(@RequestBody CompanyDto companyDto) {
        return companyService.saveCompany(companyDto, serviceUpdateValidator);
    }

    @DeleteMapping(value = "/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCompany(@PathVariable Integer companyId) {
        companyService.deleteCompany(companyId);
    }

    /***
     * Department
     */

    @GetMapping(path = "/department")
    List<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping(path = "/department/{departmentId}")
    DepartmentDto getDepartmentById(@PathVariable("departmentId") Integer departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping(path = "/department")
    DepartmentDto addDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.saveDepartment(departmentDto, serviceAddValidator);
    }

    @PutMapping(path = "/department")
    DepartmentDto updateDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.saveDepartment(departmentDto, serviceUpdateValidator);
    }

    @DeleteMapping(value = "/department/{departmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteDepartment(@PathVariable Integer departmentId) {
        departmentService.delete(departmentId);
    }

    /***
     * Team
     */

    @GetMapping(path = "/team")
    List<TeamDto> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping(path = "/team/{teamId}")
    TeamDto getTeamById(@PathVariable("teamId") Integer teamId) {
        return teamService.getTeamById(teamId);
    }

    @PostMapping(path = "/team")
    TeamDto addTeam(@RequestBody TeamDto teamDto) {
        return teamService.saveTeam(teamDto, serviceAddValidator);
    }

    @PutMapping(path = "/team")
    TeamDto updateTeam(@RequestBody TeamDto teamDto) {
        return teamService.saveTeam(teamDto, serviceUpdateValidator);
    }

    @DeleteMapping(value = "/team/{teamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTeam(@PathVariable Integer teamId) {
        teamService.delete(teamId);
    }

    /***
     * Project
     */

    @GetMapping(path = "/project")
    List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping(path = "/project/{projectId}")
    ProjectDto getProjectById(@PathVariable("projectId") Integer projectId) {
        return projectService.getProjectById(projectId);
    }

    @PostMapping(path = "/project")
    ProjectDto addProject(@RequestBody ProjectDto projectDto) {
        return projectService.saveProject(projectDto, serviceAddValidator);
    }

    @PutMapping(path = "/project")
    ProjectDto updateProject(@RequestBody ProjectDto projectDto) {
        return projectService.saveProject(projectDto, serviceUpdateValidator);
    }

    @DeleteMapping(value = "/project/{projectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProject(@PathVariable Integer projectId) {
        projectService.delete(projectId);
    }

    /***
     * Manager
     */

    @GetMapping(path = "/manager")
    List<ManagerDto> getAllManagers() {
        return managerService.getAllManages();
    }

    @GetMapping(path = "/manager/{managerId}")
    ManagerDto getManagerById(@PathVariable("managerId") Integer managerId) {
        return managerService.getManagerById(managerId);
    }

    @PostMapping(path = "/manager")
    ManagerDto addManager(@RequestBody ManagerDto managerDto) {
        return managerService.saveManager(managerDto, serviceAddValidator);
    }

    @PutMapping(path = "/manager")
    ManagerDto updateManager(@RequestBody ManagerDto managerDto) {
        return managerService.saveManager(managerDto, serviceUpdateValidator);
    }

    @DeleteMapping(value = "/manager/{managerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteManager(@PathVariable Integer managerId) {
        managerService.delete(managerId);
    }

}
