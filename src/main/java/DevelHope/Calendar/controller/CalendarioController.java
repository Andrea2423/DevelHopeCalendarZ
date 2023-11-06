package DevelHope.Calendar.controller;

import DevelHope.Calendar.entity.Calendario;
import DevelHope.Calendar.service.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CalendarioController {
    @Autowired
    CalendarioService calendarioService;

    @GetMapping("/calendario/{id}")
    public ResponseEntity<Calendario> vediCalendario(@PathVariable long id) {
        Optional<Calendario> calendario = calendarioService.vediCalendario(id);
        if (calendario.isPresent()) {
            return new ResponseEntity<>(calendario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/calendari")
    public List<Calendario> vediCalendari() {
        return calendarioService.vediCalendari();
    }

    @PutMapping("/update-calendario/{id}")
    public ResponseEntity<?> updateCalendario(@PathVariable long id, @RequestBody Calendario calendario) {
        Calendario calendarioModificato = calendarioService.updateCalendario(id, calendario);
        if (calendarioModificato == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(calendarioModificato);
    }
    //return ResponseEntity<Calendar>.ok(calendarioModificato);


    @DeleteMapping("/delete-calendario/{id}")
    public ResponseEntity<String> deleteCalendario(@PathVariable long id) {
        calendarioService.deleteCalendario(id);
        return ResponseEntity.ok("Calendario eliminato");
    }

    @PostMapping("/create-calendario")
    public ResponseEntity<String> createCalendario(@RequestBody Calendario calendario) {
        calendarioService.createCalendario(calendario);
        return ResponseEntity.ok("Calendario creato");
    }
}