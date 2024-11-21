package pl.luxmed.company.demo.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.luxmed.company.demo.dto.ErrorResponseDto;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class ErrorResponseDtoFactory {
    private final HttpServletRequest request;

    public ErrorResponseDto createErrorResponseDto(Exception ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setTimestamp(ZonedDateTime.now().toString());
        errorResponseDto.setUrl(request.getRequestURI());
        errorResponseDto.setMessage(ex.getMessage());
        return errorResponseDto;
    }

}
