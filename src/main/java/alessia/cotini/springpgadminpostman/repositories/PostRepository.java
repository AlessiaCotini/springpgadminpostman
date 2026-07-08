package alessia.cotini.springpgadminpostman.repositories;

import alessia.cotini.springpgadminpostman.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
