package alessia.cotini.springpgadminpostman.records;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.util.UUID;

public record NewPostRecord(
        @NotBlank(message = "La categoria è obbligatoria")
        String categoria,

        @NotBlank(message = "Il titolo è obbligatorio")
        String titolo,

        @Min(value = 1, message = "Il tempo di lettura deve essere di almeno 1 minuto")
        int tempoLettura,

        @NotBlank(message = "Il contenuto è obbligatorio")
        String contenuto,

        @NotNull(message = "Identificare l'autore è obbligatorio")
        UUID authorId
    ){}
