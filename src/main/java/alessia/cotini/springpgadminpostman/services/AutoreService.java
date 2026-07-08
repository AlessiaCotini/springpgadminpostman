package alessia.cotini.springpgadminpostman.services;

import alessia.cotini.springpgadminpostman.entities.Autore;

import alessia.cotini.springpgadminpostman.payloads.NewAutorePayload;
import alessia.cotini.springpgadminpostman.repositories.AutoreRepository;
//import giusti ricordare !!!!!!
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class AutoreService {
    private final AutoreRepository autoreRepository;

    public AutoreService(AutoreRepository autoreRepository) {
        this.autoreRepository = autoreRepository;
    }
    public Page<Autore> findAll(int page){
        Pageable pageable = PageRequest.of(page,10);
        return this.autoreRepository.findAll(pageable);
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
        assert trovato != null;
        trovato.setName(payloads.getName());
        trovato.setSurname(payloads.getSurname());
        trovato.setEmail(payloads.getEmail());
        trovato.setDateOfBirth(payloads.getDateOfBirth());
        return trovato;
    }

    public void deleteAuthor (@PathVariable UUID authorId){
        Autore trovato = autoreRepository.findById(authorId).orElse(null);
        assert trovato != null;
        autoreRepository.delete(trovato);
    }
}
