package alessia.cotini.springpgadminpostman.controllers;


import alessia.cotini.springpgadminpostman.entities.Autore;
import alessia.cotini.springpgadminpostman.payloads.NewAutorePayload;
import alessia.cotini.springpgadminpostman.services.AutoreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogAuthors")
public class AutoreController {
    private final AutoreService autoreService;

    public AutoreController(AutoreService autoreService) {
        this.autoreService = autoreService;
    }

    //GET - http://localhost:3027/blogAuthors
    @GetMapping
    public List<Autore> findAll(){
        return this.autoreService.findAll();
    }
    //GET - http://localhost:3027/blogAuthors/{authorId}
    @GetMapping("/{authorId}")
    public Autore findById(@PathVariable UUID authorId){
        return this.autoreService.findById(authorId);
    }
    //POST - http://localhost:3027/blogAuthors + payload
    @PostMapping
    public Autore createAuthor(@RequestBody NewAutorePayload payloads){
        return this.autoreService.createAuthor(payloads);
    }
    //PUT - http://localhost:3027/blogAuthors+ payload
    @PutMapping("/{authorId}")
    public Autore modifyAuthor(@RequestBody NewAutorePayload payloads, @PathVariable UUID authorId){
        return this.autoreService.modifyAuthor(authorId, payloads);
    }
    //DELETE - http://localhost:3027/blogAuthors/{authorId}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable UUID authorId){
        this.autoreService.deleteAuthor(authorId);
    }
}

