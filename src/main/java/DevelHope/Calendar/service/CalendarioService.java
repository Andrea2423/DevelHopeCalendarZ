package DevelHope.Calendar.service;

import DevelHope.Calendar.entity.Calendario;
import DevelHope.Calendar.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalendarioService {
    @Autowired
    CalendarioRepository calendarioRepository;

    public void createCalendario(Calendario calendario) {
        calendarioRepository.save(calendario);
    }

    public void deleteCalendario(long id) {
        calendarioRepository.deleteById(id);
    }

    public void updateCalendario(Calendario calendario) {
        Optional<Calendario> calendarioEsistente = calendarioRepository.findById(calendario.getId());
        if (calendarioEsistente.isPresent()) {
            //se il calendario esiste me lo prendi
            Calendario calendarioAggiornato = calendarioEsistente.get();
            calendarioAggiornato.setTitolo(calendario.getTitolo());
            calendarioAggiornato.setDescrizione(calendario.getDescrizione());
            calendarioAggiornato.setColor(calendario.getColor());
            calendarioRepository.save(calendarioAggiornato);

        }

    }

    public Optional<Calendario> vediCalendario(long id) {
        return calendarioRepository.findById(id);
    }
}