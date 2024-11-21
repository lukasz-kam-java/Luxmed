package pl.luxmed.company.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeamDto implements IdentificableDto  {
    private Integer id;
    private String name;
    private ProjectDto project;
    @NotNull(message = "The department Id must not be null")
    private Integer departmentId;
    private Integer projectId;
}