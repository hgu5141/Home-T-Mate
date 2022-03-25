package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class ExceptionController  {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestApiException> processValidationError(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getDefaultMessage());
        }
        System.out.println(builder.toString());
        return ResponseEntity.badRequest()
                .body(new RestApiException(builder.toString(), HttpStatus.BAD_REQUEST));
    }


    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<RestApiException> exceptionHandler(Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.badRequest()
                .body(new RestApiException(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    //CustomException 사용
    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

}

