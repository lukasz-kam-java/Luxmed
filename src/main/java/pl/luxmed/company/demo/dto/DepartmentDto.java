package pl.luxmed.company.demo.dto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pl.luxmed.company.demo.entity.Company;

import java.util.List;
@Data
public class DepartmentDto implements IdentificableDto {
    private Integer id;
    @Length(min=3, max=500)
    private String name;
    private List<TeamDto> teams;
    @NotNull
    private Integer companyId;
}