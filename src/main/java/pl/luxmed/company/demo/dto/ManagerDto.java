package pl.luxmed.company.demo.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ManagerDto implements IdentificableDto {
    private Integer id;
    @Length(min=3, max=500)
    private String name;
    @Email
    @Length(min=3, max=1000)
    private String email;
}