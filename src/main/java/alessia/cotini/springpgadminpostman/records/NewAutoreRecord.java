package alessia.cotini.springpgadminpostman.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewAutoreRecord(
    @NotBlank(message = "La categoria è obbligatoria")
    @Size(min = 2, max = 30, message = "La categoria deve essere tra 2 e 30 caratteri")
    String name,

    @NotBlank(message = "Il cognome è obbligatorio")
    String surname,

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "L'email inserita non è valida")
    String email,

    @NotBlank(message = "La data di nascita è obbligatoria")
    String dateOfBirth
){}
