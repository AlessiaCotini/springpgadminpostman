package alessia.cotini.springpgadminpostman.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class GestioneErrore {
    private String message;
    private LocalDateTime localDateTime;

    public GestioneErrore(String message,LocalDateTime localDateTime) {
        this.message = message;
        this.localDateTime = localDateTime;
    }
}
