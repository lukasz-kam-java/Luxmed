package pl.luxmed.company.demo.dto;

import lombok.Data;

@Data
public class ProjectDto implements IdentificableDto  {
    private Integer id;
    private String name;
    private ManagerDto manager;
    private Integer managerId;
}