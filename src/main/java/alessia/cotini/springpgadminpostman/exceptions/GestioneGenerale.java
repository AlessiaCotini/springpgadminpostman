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

    @ExceptionHandler(Conflict.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public GestioneErrore conflict(Conflict ex){
        return new GestioneErrore(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GestioneErrore exGenerale(Exception ex){
        //GIUSTO PER LA RIGA DELL'ERRORE
        ex.printStackTrace();
        return new GestioneErrore("SERVER ERROR", LocalDateTime.now());
    }
}
