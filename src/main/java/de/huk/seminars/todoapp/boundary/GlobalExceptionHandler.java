package de.huk.seminars.todoapp.boundary;

import de.huk.seminars.todoapp.control.CouldNotCreateItemException;
import de.huk.seminars.todoapp.control.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiErrorDto> handleNotFoundException(NotFoundException exception) {
    ApiErrorDto errorDto = new ApiErrorDto();
    errorDto.setErrorCode("0815");
    errorDto.setErrorMessage(exception.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
  }


  @ExceptionHandler(CouldNotCreateItemException.class)
  public ResponseEntity<ApiErrorDto> handleCouldNotCreateException(CouldNotCreateItemException ex) {
    ApiErrorDto errorDto = ApiErrorDto.builder()
        .errorCode("0816")
        .errorMessage(ex.getMessage())
        .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
  }


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {

    List<FieldError> fieldErrors = ex.getFieldErrors();

    List<ApiErrorDto> apiErrorDtos = fieldErrors
        .stream()
        .map(this::getApiError)
        .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorDtos);
  }


  private ApiErrorDto getApiError(FieldError fieldError) {
    String message = "field: " + fieldError.getField()
        + ", message: " + fieldError.getDefaultMessage();

    return ApiErrorDto.builder()
        .errorCode("0818")
        .errorMessage(message)
        .build();
  }
}
