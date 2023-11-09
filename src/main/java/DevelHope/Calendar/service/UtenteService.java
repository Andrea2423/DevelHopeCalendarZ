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
                .map(utenteEsistente -> {
                    utenteEsistente.setNome(utente.getNome());
                    utenteEsistente.setCognome(utente.getCognome());
                    utenteEsistente.setEmail(utente.getEmail());
                    utenteEsistente.setPassword(utente.getPassword());

                    // Pulisci e aggiorna la relazione @ManyToMany con gli eventi
                    if (utente.getEventi() != null) {
                        utenteEsistente.getEventi().clear();
                        utenteEsistente.getEventi().addAll(utente.getEventi());
                    }

                    return utenteRepository.save(utenteEsistente);
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