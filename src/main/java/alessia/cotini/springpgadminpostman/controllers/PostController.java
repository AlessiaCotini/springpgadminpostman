package alessia.cotini.springpgadminpostman.controllers;


import alessia.cotini.springpgadminpostman.entities.Post;
import alessia.cotini.springpgadminpostman.exceptions.BadRequest;
import alessia.cotini.springpgadminpostman.exceptions.Conflict;
import alessia.cotini.springpgadminpostman.records.NewPostRecord;
import alessia.cotini.springpgadminpostman.services.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/blogPosts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    //GET - http://localhost:3027/blogPost
    //SERVE PER LE PAGINE
    @GetMapping
    public Page<Post> getAll(@RequestParam("0") int page){
        return this.postService.findAll(page);
    }


    //GET - http://localhost:3027/blogPost/{postId}
    @GetMapping("/{postId}")
    public Post findById(@PathVariable UUID postId){
        return this.postService.findById(postId);
    }
    //POST - http://localhost:3027/blogPost + payload
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody @Valid NewPostRecord payload, BindingResult validation){
      if(validation.hasErrors()){throw new BadRequest("Errore nell'inserimento dei campi di creazione");}
       this.postService.save(payload);
    }
    //PUT - http://localhost:3027/blogPost/{postId} + payload
    @PutMapping("/{postId}")
    public Post modifyPost(@RequestBody @Valid NewPostRecord payloads, @PathVariable UUID postId, BindingResult validation){
        if(validation.hasErrors()){throw new BadRequest("Errore nella modifica dei campi inseriti");}
        return this.postService.modifyPost(postId, payloads);
    }
    //DELETE - http://localhost:3027/{postId}
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable UUID postId){
        this.postService.deletePost(postId);
    }

    //PATCH PER IMMAGINE - http://localhost:3001/blogAuthors/{authorId}/changeimg
    @PatchMapping("/{postId}/changeimg")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String uploadAvatar(@PathVariable UUID postId, @RequestParam("avatar") MultipartFile file) {
        try {
            return postService.changePostImg(postId, file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore nel caricamento del file fisico: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore generico: " + e.getMessage());
        }
    }
}
