package alessia.cotini.springpgadminpostman.services;

import alessia.cotini.springpgadminpostman.entities.Autore;

import alessia.cotini.springpgadminpostman.exceptions.NotFound;
import alessia.cotini.springpgadminpostman.records.NewAutoreRecord;
import alessia.cotini.springpgadminpostman.repositories.AutoreRepository;
//import giusti ricordare !!!!!!
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
public class AutoreService {
    private final AutoreRepository autoreRepository;
    private final Cloudinary cloudinary;

    public AutoreService(AutoreRepository autoreRepository, Cloudinary cloudinary) {
        this.autoreRepository = autoreRepository;
        this.cloudinary = cloudinary;
    }

    public Page<Autore> findAll(int page){
        Pageable pageable = PageRequest.of(page,10);
        return this.autoreRepository.findAll(pageable);
    }

    public Autore findById (UUID authorId) {
        return autoreRepository.findById(authorId).orElseThrow(()-> new NotFound("L'autore con id "+ authorId+ " non è stato trovato."));
    }

    public Autore createAuthor(NewAutoreRecord payloads){
        Autore nuovoAutore = new Autore(payloads.name(), payloads.surname(), payloads.email(), payloads.dateOfBirth());
        this.autoreRepository.save(nuovoAutore);
        return nuovoAutore;
    }

    public Autore modifyAuthor(@PathVariable UUID authorId, NewAutoreRecord payloads){
        Autore trovato = autoreRepository.findById(authorId).orElseThrow(()-> new NotFound("L'autore con id "+ authorId+ " non è stato trovato."));
        assert trovato != null;
        trovato.setName(payloads.name());
        trovato.setSurname(payloads.surname());
        trovato.setEmail(payloads.email());
        trovato.setDateOfBirth(payloads.dateOfBirth());
        return trovato;
    }

    public void deleteAuthor (@PathVariable UUID authorId){
        Autore trovato = autoreRepository.findById(authorId).orElseThrow(()-> new NotFound("L'autore con id "+ authorId+ " non è stato trovato."));
        assert trovato != null;
        autoreRepository.delete(trovato);
    }


    //utilizzo del cloud
    public String changeAvatar(UUID autoreId, MultipartFile file) throws Exception {
        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = (String) result.get("url");
        Autore autore = autoreRepository.findById(autoreId)
                .orElseThrow(() -> new NotFound("Autore non trovato con ID: " + autoreId));
        autore.setAvatarImg(url);
        autoreRepository.save(autore);
        return url;
    }
}
