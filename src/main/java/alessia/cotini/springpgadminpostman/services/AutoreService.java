package alessia.cotini.springpgadminpostman.services;

import alessia.cotini.springpgadminpostman.entities.Autore;
import alessia.cotini.springpgadminpostman.payloads.NewAutorePayload;
import alessia.cotini.springpgadminpostman.repositories.AutoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
public class AutoreService {
    private final AutoreRepository autoreRepository;

    public AutoreService(AutoreRepository autoreRepository) {
        this.autoreRepository = autoreRepository;
    }
    public List<Autore> findAll(){
        return this.autoreRepository.findAll();
    }

    public Autore findById (UUID authorId) {
        return autoreRepository.findById(authorId).orElse(null);
    }

    public Autore createAuthor(NewAutorePayload payloads){
        Autore nuovoAutore = new Autore(payloads.getName(), payloads.getSurname(), payloads.getEmail(), payloads.getDateOfBirth());
        this.autoreRepository.save(nuovoAutore);
        return nuovoAutore;
    }

    public Autore modifyAuthor(@PathVariable UUID authorId, NewAutorePayload payloads){
        Autore trovato = autoreRepository.findById(authorId).orElse(null);
        trovato.setName(payloads.getName());
        trovato.setSurname(payloads.getSurname());
        trovato.setEmail(payloads.getEmail());
        trovato.setDateOfBirth(payloads.getDateOfBirth());
        return trovato;
    }

    public void deleteAuthor (@PathVariable UUID authorId){
        Autore trovato = autoreRepository.findById(authorId).orElse(null);
        autoreRepository.delete(trovato);
    }
}
