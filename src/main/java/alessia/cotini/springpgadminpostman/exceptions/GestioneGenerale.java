package alessia.cotini.springpgadminpostman.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GestioneGenerale {

    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GestioneErrore badRequest(BadRequest ex){
        return new GestioneErrore(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GestioneErrore notFound(NotFound ex){
        return new GestioneErrore(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Conflict.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public GestioneErrore conflict(Conflict ex){
        return new GestioneErrore(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GestioneErrore errGenerale(Exception ex){
        ex.printStackTrace();
        return new GestioneErrore("Eccezione generale", LocalDateTime.now());
    }
}
