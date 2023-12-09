package DevelHope.Calendar.service;

import DevelHope.Calendar.entity.Calendario;
import DevelHope.Calendar.entity.Utente;
import DevelHope.Calendar.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {
    @Autowired
    UtenteRepository utenteRepository;

    public Utente createUtente(Utente utente) throws Exception {
        try {
            Calendario calendario = new Calendario();
            calendario.setTitolo("Calendario di " + utente.getNome());
            calendario.setUtente(utente);

            utente.getCalendario().add(calendario);
            return utenteRepository.save(utente);
           } catch (Exception e) {
               throw new Exception(String.format("Email %s already exist", utente.getEmail()));
           }
    }

    public String deleteUtente(long id) throws Exception {
        if (utenteRepository.findById(id).isPresent()) {
            utenteRepository.deleteById(id);
            return String.format("User with ID %s deleted", id);
        } else {
            throw new Exception(String.format("User with ID %s not exist", id));
        }
    }
    public Utente updateUtente(long id, Utente utente) throws Exception {
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
                } ).orElseThrow(() -> new Exception(String.format("User with ID %s not found", id)));
        }


    public Optional<Utente> viewUserByEmail(String email) throws Exception{

        try {
            return utenteRepository.findByEmail(email);
        } catch (Exception e){
            throw new Exception(String.format("User with email %s not found", email));
        }

    }
    public List<Utente> viewUsers() throws Exception{
        if (utenteRepository.findAll().isEmpty()){
            return utenteRepository.findAll();
        } else {
            throw new Exception("Users not found");
        }

    }

    public Optional<Utente> viewUserToId(long id) throws Exception{
        if (utenteRepository.findById(id).isPresent()){
            return utenteRepository.findById(id);
        } else {
            throw new Exception(String.format("User with ID %s not found", id));
        }

    }}