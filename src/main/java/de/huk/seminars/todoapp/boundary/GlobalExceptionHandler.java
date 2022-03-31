package de.huk.seminars.todoapp.boundary;

import de.huk.seminars.todoapp.control.CouldNotCreateItemException;
import de.huk.seminars.todoapp.control.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
}
