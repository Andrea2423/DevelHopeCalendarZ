package DevelHope.Calendar.controller;

import DevelHope.Calendar.entity.Utente;
import DevelHope.Calendar.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(utenteService.viewUserToId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(utenteService.viewUserByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/utente/{id}")
    public ResponseEntity updateUtente(@PathVariable long id, @RequestBody Utente utente) {
        try {
            return ResponseEntity.ok(utenteService.updateUtente(id, utente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/delete-utente/{id}")
    public ResponseEntity deleteUtente(@PathVariable long id) {
        try {
            return ResponseEntity.ok(utenteService.deleteUtente(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity geAllUser() {
        try {
            return ResponseEntity.ok(utenteService.viewUsers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/create-utente")
    public ResponseEntity createUtente(@RequestBody Utente utente) {
        try {
            return ResponseEntity.ok(utenteService.createUtente(utente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}