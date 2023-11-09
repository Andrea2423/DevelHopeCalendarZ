package DevelHope.Calendar.controller;

import DevelHope.Calendar.entity.Evento;
import DevelHope.Calendar.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EventoController {

    @Autowired
    EventoService eventoService;

    @GetMapping("/evento/{id}")
    public ResponseEntity<Evento> viewEvento(@PathVariable long id) {
        Optional<Evento> evento = eventoService.vediEvento(id);
        return evento.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/eventi")
    public List<Evento> viewEventi() {
        return eventoService.vediEvento();
    }

    @PutMapping("/update-evento/{id}")
    public ResponseEntity<?> updateEvento(@PathVariable long id, @RequestBody Evento evento) {
        Evento eventoModified = eventoService.updateEvento(id, evento);
        return eventoModified != null ?
                ResponseEntity.ok(eventoModified) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete-evento/{id}")
    public ResponseEntity<String> deleteEvento(@PathVariable long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.ok("Evento eliminato");
    }

    @PostMapping("/create-evento")
    public ResponseEntity<String> createEvento(@RequestBody Evento evento) {
        eventoService.createEvento(evento);
        return ResponseEntity.ok("Evento creato");
    }
}
