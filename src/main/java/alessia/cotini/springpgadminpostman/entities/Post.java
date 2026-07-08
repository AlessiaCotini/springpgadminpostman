package alessia.cotini.springpgadminpostman.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private UUID postId;

    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private Integer tempoLettura;

    @ManyToOne
    @JoinColumn(name = "authorId")
    Autore autore;

    public Post(String categoria, String titolo, String contenuto, Integer tempoLettura, Autore autore) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoLettura = tempoLettura;
        this.autore = autore;
        this.cover = "https://picsum.photos/200";
    }

}
