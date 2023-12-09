package DevelHope.Calendar.controller;

import DevelHope.Calendar.entity.Calendario;
import DevelHope.Calendar.service.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalendarioController {
    @Autowired
    CalendarioService calendarioService;

    @GetMapping("/calendario/{id}")
    public ResponseEntity vediCalendario(@PathVariable long id) {
        try {
            return ResponseEntity.ok(calendarioService.vediCalendariByUser(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/calendario/{id}")
    public ResponseEntity updateCalendario(@PathVariable long id, @RequestBody Calendario calendario) {
        try {
            return ResponseEntity.ok(calendarioService.updateCalendario(id, calendario));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteCalendar(@PathVariable long id){
        try {
            return ResponseEntity.ok(calendarioService.deleteCalendario(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/{userID}")
    public ResponseEntity createCalendario(@PathVariable long userID, @RequestBody Calendario calendario) {
        try {
            return ResponseEntity.ok(calendarioService.createCalendar(userID, calendario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }}