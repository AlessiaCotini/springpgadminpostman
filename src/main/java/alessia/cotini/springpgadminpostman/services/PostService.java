package alessia.cotini.springpgadminpostman.services;

import alessia.cotini.springpgadminpostman.entities.Autore;
import alessia.cotini.springpgadminpostman.entities.Post;
import alessia.cotini.springpgadminpostman.payloads.NewPostPayload;
import alessia.cotini.springpgadminpostman.repositories.AutoreRepository;
import alessia.cotini.springpgadminpostman.repositories.PostRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    private  final PostRepository postRepository;
    private final AutoreRepository autoreRepository;

    public PostService(PostRepository postRepository, AutoreRepository autoreRepository) {
        this.postRepository = postRepository;
        this.autoreRepository = autoreRepository;
    }
    public List<Post> findAll(){
        return this.postRepository.findAll();
    }

    public Post findById (UUID postId){
        return postRepository.findById(postId).orElse(null);
    }
    public void save(NewPostPayload payload){
        Autore autore = autoreRepository.findById(payload.getAuthorId()).orElse(null);
        Post post = new Post(payload.getTitolo(), payload.getCategoria(), payload.getContenuto(), payload.getTempoLettura(), autore );
        this.postRepository.save(post);
    }
    public Post modifyPost(@PathVariable UUID postId, NewPostPayload payloads){
        Post trovato = postRepository.findById(postId).orElse(null);
        trovato.setTitolo(payloads.getTitolo());
        trovato.setCategoria(payloads.getCategoria());
        trovato.setContenuto(payloads.getContenuto());
        trovato.setTempoLettura(payloads.getTempoLettura());
        return trovato;
    }

    public void deletePost (@PathVariable UUID postId){
        Post trovato = postRepository.findById(postId).orElse(null);
        postRepository.delete(trovato);
    }
}
