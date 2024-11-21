package pl.luxmed.company.demo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperConfiguration {
    public ObjectMapperConfiguration(ObjectMapper objectMapper) {
        objectMapper.registerModule(new Hibernate5JakartaModule());
    }
}