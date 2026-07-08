package alessia.cotini.springpgadminpostman.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;


@Entity
@Table(name = "autori")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID autoreId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(name = "date_of_birth",nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private String avatarImg;


    public Autore( String name, String surname, String email, String dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.avatarImg =  "https://ui-avatars.com/api/?name=" + name + "+" + surname ;
    }
}
