package DevelHope.Calendar.service;

import DevelHope.Calendar.entity.Utente;
import DevelHope.Calendar.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    UtenteRepository utenteRepository;

    @Autowired
    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public void createUtente(Utente utente) {
        utenteRepository.save(utente);
    }

    public void deleteUtente(long id) {
        utenteRepository.deleteById(id);
    }

    public Utente updateUtente(long id, Utente utente) {
        return utenteRepository.findById(id)
                .map(UtenteEsistente -> {
                    if (utente.getNome() != null) {
                        UtenteEsistente.setNome(utente.getNome());
                    }
                    if (utente.getCognome() != null){
                        UtenteEsistente.setCognome(utente.getCognome());
                    }
                    if (utente.getEmail() != null){
                        UtenteEsistente.setEmail(utente.getEmail());
                    }
                    if (utente.getPassword() != null){
                        UtenteEsistente.setPassword(utente.getPassword());
                    }
                    return utenteRepository.save(UtenteEsistente);
                })
                .orElse(null);
    }

    public Optional<Utente> viewUtente(long id) {
        return utenteRepository.findById(id);
    }

    public List<Utente> viewUtenti() {
        return utenteRepository.findAll();
    }
}