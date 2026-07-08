package alessia.cotini.springpgadminpostman.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class NewAutorePayload {
    String name;
    String surname;
    String email;
    String dateOfBirth;
}
