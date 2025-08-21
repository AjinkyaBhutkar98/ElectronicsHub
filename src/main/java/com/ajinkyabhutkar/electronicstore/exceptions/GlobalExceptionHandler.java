package com.ajinkyabhutkar.electronicstore.exceptions;

import com.ajinkyabhutkar.electronicstore.dtos.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){

        ApiResponse apiResponse=new ApiResponse(ex.getMessage(),true, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){

        Map<String,Object> response=new HashMap<>();

        List<ObjectError> allErrors=ex.getBindingResult().getAllErrors();

        allErrors.forEach(
                objectError ->{
                        String field=((FieldError)objectError).getField();
                        String msg=objectError.getDefaultMessage();
                        response.put(field,msg);

                }
        );

        return new ResponseEntity<>(response,HttpStatus.OK);

    }
}
