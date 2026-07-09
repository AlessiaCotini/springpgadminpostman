package alessia.cotini.springpgadminpostman.controllers;


import alessia.cotini.springpgadminpostman.entities.Autore;
import alessia.cotini.springpgadminpostman.exceptions.BadRequest;
import alessia.cotini.springpgadminpostman.exceptions.Conflict;
import alessia.cotini.springpgadminpostman.records.NewAutoreRecord;
import alessia.cotini.springpgadminpostman.services.AutoreService;
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
@RequestMapping("/blogAuthors")
public class AutoreController {
    private final AutoreService autoreService;

    public AutoreController(AutoreService autoreService) {
        this.autoreService = autoreService;
    }

    //GET - http://localhost:3027/blogAuthors

    @GetMapping
    public Page<Autore> getAll(@RequestParam(value = "page", defaultValue = "0") int page){
        return this.autoreService.findAll(page);
    }
    //GET - http://localhost:3027/blogAuthors/{authorId}
    @GetMapping("/{authorId}")
    public Autore findById(@PathVariable UUID authorId){
        return this.autoreService.findById(authorId);
    }
    //POST - http://localhost:3027/blogAuthors + payload
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autore createAuthor(@Validated BindingResult validation,@RequestBody NewAutoreRecord payloads){
        if (validation.hasErrors())throw new BadRequest("Errore nella creazione di un nuovo autore");
        return this.autoreService.createAuthor(payloads);
    }
    //PUT - http://localhost:3027/blogAuthors+ payload
    @PutMapping("/{authorId}")
    public Autore modifyAuthor(@Validated BindingResult validation,@RequestBody NewAutoreRecord payloads, @PathVariable UUID authorId){
        if (validation.hasErrors())throw new BadRequest("Errore nella modifica del campo autore");
        return this.autoreService.modifyAuthor(authorId, payloads);
    }
    //DELETE - http://localhost:3027/blogAuthors/{authorId}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable UUID authorId){
        this.autoreService.deleteAuthor(authorId);
    }

    //PATCH PER IMMAGINE - http://localhost:3001/blogAuthors/{authorId}/changeimg
    @PatchMapping("/{authorId}/changeimg")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String uploadAvatar(@PathVariable UUID authorId, @RequestParam("avatar") MultipartFile file) {
        try {
            return autoreService.changeAvatar(authorId, file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore nel caricamento del file fisico: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore generico: " + e.getMessage());
        }
    }



}

