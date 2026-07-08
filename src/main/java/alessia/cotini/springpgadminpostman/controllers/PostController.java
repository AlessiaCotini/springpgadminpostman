package alessia.cotini.springpgadminpostman.controllers;


import alessia.cotini.springpgadminpostman.entities.Post;
import alessia.cotini.springpgadminpostman.payloads.NewPostPayload;
import alessia.cotini.springpgadminpostman.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public void createPost(@RequestBody NewPostPayload payload){
       this.postService.save(payload);
    }
    //PUT - http://localhost:3027/blogPost/{postId} + payload
    @PutMapping("/{postId}")
    public Post modifyPost(@RequestBody NewPostPayload payloads, @PathVariable UUID postId){
        return this.postService.modifyPost(postId, payloads);
    }
    //DELETE - http://localhost:3027/{postId}
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable UUID postId){
        this.postService.deletePost(postId);
    }
}
