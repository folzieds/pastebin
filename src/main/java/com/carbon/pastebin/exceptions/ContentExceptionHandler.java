package com.carbon.pastebin.exceptions;

import com.carbon.pastebin.exceptions.data.ContentExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ContentExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContentNotFoundException.class)
    public final ResponseEntity<ContentExceptionResponse> handleNotFoundException(ContentNotFoundException ex){

        ContentExceptionResponse exceptionResponse = new ContentExceptionResponse(HttpStatus.NOT_FOUND.getReasonPhrase(),ex.getLocalizedMessage(),ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContentExpiredException.class)
    public final ResponseEntity<ContentExceptionResponse> handleExpiredContentException(ContentExpiredException ex){

        ContentExceptionResponse exceptionResponse = new ContentExceptionResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(),ex.getLocalizedMessage(),ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidContentException.class)
    public final ResponseEntity<ContentExceptionResponse> handleInvalidContentException(InvalidContentException ex){

        ContentExceptionResponse exceptionResponse = new ContentExceptionResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(),ex.getLocalizedMessage(),ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
