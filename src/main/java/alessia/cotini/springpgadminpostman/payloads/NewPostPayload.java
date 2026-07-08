package alessia.cotini.springpgadminpostman.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NewPostPayload {
    private String categoria;
    private String titolo;
    private int tempoLettura;
    private String contenuto;
    private UUID authorId;
}
