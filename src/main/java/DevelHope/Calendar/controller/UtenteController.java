package DevelHope.Calendar.controller;

import DevelHope.Calendar.entity.Utente;
import DevelHope.Calendar.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @GetMapping("/utente/{id}")
    public ResponseEntity<Utente> viewUtente(@PathVariable long id) {
        Optional<Utente> utente = utenteService.viewUtente(id);
        if (utente.isPresent()) {
            return new ResponseEntity<>(utente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/utenti")
    public List<Utente> viewUtenti() {
        return utenteService.viewUtenti();
    }

    @PutMapping("/update-utente/{id}")
    public ResponseEntity<?> updateUtente(@PathVariable long id, @RequestBody Utente utente) {
        Utente utenteModified = utenteService.updateUtente(id, utente);
        if (utenteModified == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(utenteModified);
    }

    @DeleteMapping("/delete-utente/{id}")
    public ResponseEntity<String> deleteUtente(@PathVariable long id) {
        utenteService.deleteUtente(id);
        return ResponseEntity.ok("Utente eliminato");
    }

    @PostMapping("/create-utente")
    public ResponseEntity<String> createUtente(@RequestBody Utente utente) {
        utenteService.createUtente(utente);
        return ResponseEntity.ok("Utente creato");
    }
}
