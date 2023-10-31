package DevelHope.Calendar.controller;

import DevelHope.Calendar.entity.Calendario;
import DevelHope.Calendar.service.CalendarioService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CalendarioController {
    @Autowired
    CalendarioService calendarioService;

    @GetMapping("/calendario")
    public Optional<Calendario> vediCalendario(@RequestParam long id) {
        {
            return calendarioService.vediCalendario(id);
        }
    }

    @PostMapping("/update-calendario")
    public void updateCalendario(@RequestBody Calendario calendario) {
        Optional<Calendario> calendarioEsistente = calendarioService.vediCalendario(calendario.getId());
        if (calendarioEsistente.isPresent()) {
            Calendario calendarioAggiornato = calendarioEsistente.get();
            calendarioAggiornato.setTitolo(calendario.getTitolo());
            calendarioAggiornato.setDescrizione(calendario.getDescrizione());
            calendarioAggiornato.setColor(calendario.getColor());
            calendarioService.updateCalendario(calendarioAggiornato);
        }

    }

    @PostMapping("/delete-calendario")
    public String deleteCalendario(@RequestParam long id) {
        {
            calendarioService.deleteCalendario(id);
            return "Calendario eliminato";
        }
    }

    @PostMapping("/create-calendarario")
    public String createCalendario(@RequestBody Calendario calendario) {
        // Creare un oggetto Calendario a partire dai dati nel calendario
        Calendario nuovoCalendario = new Calendario();
        nuovoCalendario.setTitolo(calendario.getTitolo());
        nuovoCalendario.setDescrizione(calendario.getDescrizione());
        nuovoCalendario.setColor(calendario.getColor());
        nuovoCalendario.setUtenti(calendario.getUtenti());
        nuovoCalendario.setData(calendario.getData());

        // Chiamare il servizio per creare il nuovo calendario
        calendarioService.createCalendario(nuovoCalendario);
        return "Calendario creato";
    }
}