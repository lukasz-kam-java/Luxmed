package pl.luxmed.company.demo.dto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


import java.util.List;

@Data
public class CompanyDto implements IdentificableDto {
    private Integer id;
    @Length(min=3, max=500)
    private String name;
    private List<DepartmentDto> departments;

}
