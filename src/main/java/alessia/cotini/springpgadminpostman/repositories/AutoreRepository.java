package alessia.cotini.springpgadminpostman.repositories;

import alessia.cotini.springpgadminpostman.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutoreRepository extends JpaRepository<Autore, UUID> {

}
