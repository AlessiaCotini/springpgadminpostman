package alessia.cotini.springpgadminpostman.services;

import alessia.cotini.springpgadminpostman.entities.Autore;
import alessia.cotini.springpgadminpostman.entities.Post;
import alessia.cotini.springpgadminpostman.exceptions.NotFound;
import alessia.cotini.springpgadminpostman.records.NewPostRecord;
import alessia.cotini.springpgadminpostman.repositories.AutoreRepository;
import alessia.cotini.springpgadminpostman.repositories.PostRepository;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

@Service
public class PostService {
    private  final PostRepository postRepository;
    private final AutoreRepository autoreRepository;
    private final Cloudinary cloudinary;

    public PostService(PostRepository postRepository, AutoreRepository autoreRepository, Cloudinary cloudinary) {
        this.postRepository = postRepository;
        this.autoreRepository = autoreRepository;
        this.cloudinary = cloudinary;
    }
    public Page<Post> findAll(int page){
        Pageable pageable = PageRequest.of(page,10);
        return this.postRepository.findAll(pageable);
    }

    public Post findById (UUID postId){
        return postRepository.findById(postId).orElse(null);
    }
    public void save(NewPostRecord payload){
        Autore autore = autoreRepository.findById(payload.authorId()).orElse(null);
        Post post = new Post(payload.titolo(), payload.categoria(), payload.contenuto(), payload.tempoLettura(), autore );
        this.postRepository.save(post);
    }
    public Post modifyPost(@PathVariable UUID postId, NewPostRecord payloads){
        Post trovato = postRepository.findById(postId).orElse(null);
        trovato.setTitolo(payloads.titolo());
        trovato.setCategoria(payloads.categoria());
        trovato.setContenuto(payloads.contenuto());
        trovato.setTempoLettura(payloads.tempoLettura());
        return trovato;
    }

    public void deletePost (@PathVariable UUID postId){
        Post trovato = postRepository.findById(postId).orElse(null);
        postRepository.delete(trovato);
    }

    //utilizzo del cloud
    public String changePostImg(UUID postId, MultipartFile file) throws Exception {
        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = (String) result.get("url");
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFound("Post non trovato con ID: " + postId));
        post.setCover(url);
        postRepository.save(post);
        return url;
    }
}
