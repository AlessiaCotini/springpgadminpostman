package alessia.cotini.springpgadminpostman.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GestioneGenerale {

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GestioneErrore notFound(NotFound ex){
        return new GestioneErrore(ex.getMessage(), LocalDateTime.now());
    }
}
