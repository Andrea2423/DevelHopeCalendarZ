package DevelHope.Calendar.controller;

import DevelHope.Calendar.entity.Evento;
import DevelHope.Calendar.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class EventoController {

    @Autowired
    EventoService eventoService;



    @GetMapping("/eventi")
    public ResponseEntity viewEventi(long id) {
        try {
            return ResponseEntity.ok(eventoService.vediEvento(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/evento/{id}")
    public ResponseEntity updateEvento(@PathVariable long id, @RequestBody Evento evento) {
        try {
            return ResponseEntity.ok(eventoService.vediEvento(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/{eventID}/user/{userID}")
    public ResponseEntity invitedUser(@PathVariable long eventID, @PathVariable int userID){

        try {
            return ResponseEntity.ok(eventoService.inviteUser(eventID, userID));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity deleteEvento( long id) {
        try {
            return ResponseEntity.ok(eventoService.deleteEvento(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{calendarID}/start/{startTime}/duration/{duration}")
    public ResponseEntity createNewEvent(@PathVariable int calendarID,
                                         @RequestBody Evento event,
                                         @PathVariable LocalDateTime startTime,
                                         @PathVariable int duration) {

        try {
            return ResponseEntity.ok(eventoService.createEvent(calendarID, event, startTime, duration));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
