package pl.luxmed.company.demo.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.luxmed.company.demo.dto.ErrorResponseDto;
import pl.luxmed.company.demo.exception.InternalServerErrorException;
import pl.luxmed.company.demo.exception.BadRequestException;
import pl.luxmed.company.demo.exception.NotFoundException;

@ControllerAdvice
@RequiredArgsConstructor
public class ErrroHandlerControlerAdvice {

    private final pl.luxmed.company.demo.common.ErrorResponseDtoFactory ErrorResponseDtoFactory;

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseDtoFactory.createErrorResponseDto(exception));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDtoFactory.createErrorResponseDto(exception));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleException(BadRequestException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDtoFactory.createErrorResponseDto(exception));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponseDto> handleException(InternalServerErrorException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseDtoFactory.createErrorResponseDto(exception));
    }

}
