package pl.luxmed.company.demo.dto;

import lombok.Data;

@Data
public class ErrorResponseDto {
    String url;
    String timestamp;
    String message;
}
