package org.example.adapter.in;

import org.example.adapter.in.dto.ErrorResponse;
import org.example.adapter.in.exception.BadRequestException;
import org.example.adapter.in.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(BadRequestException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                "Bad request",
                ex.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(GeneralException ex) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(
                "Internal server error",
                ex.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(
                "Internal server error",
                ex.getMessage()
        ));
    }
}
