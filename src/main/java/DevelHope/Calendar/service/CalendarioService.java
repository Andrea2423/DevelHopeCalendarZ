package DevelHope.Calendar.service;

import DevelHope.Calendar.entity.Calendario;
import DevelHope.Calendar.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {

    CalendarioRepository calendarioRepository;

    @Autowired
    public CalendarioService(CalendarioRepository calendarioRepository) {
        this.calendarioRepository = calendarioRepository;
    }

    public void createCalendario(Calendario calendario) {
        calendarioRepository.save(calendario);
    }

    public void deleteCalendario(long id) {
        calendarioRepository.deleteById(id);
    }

    public Calendario updateCalendario(long id, Calendario calendario) {
        return calendarioRepository.findById(id)
                .map(calendarioEsistente -> {
                    if (calendario.getTitolo() != null) {
                        calendarioEsistente.setTitolo(calendario.getTitolo());
                    }
                    if (calendario.getDescrizione() != null) {
                        calendarioEsistente.setDescrizione(calendario.getDescrizione());
                    }
                    if (calendario.getColor() != null) {
                        calendarioEsistente.setColor(calendario.getColor());
                    }
                    if (calendario.getData() != null) {
                        calendarioEsistente.setData(calendario.getData());
                    }
                    if (calendario.getUtenti() != null) {
                        calendarioEsistente.setUtenti(calendario.getUtenti());
                    }
                    return calendarioRepository.save(calendarioEsistente);
                })
                .orElse(null);
    }

    public Optional<Calendario> vediCalendario(long id) {
        return calendarioRepository.findById(id);
    }

    public List<Calendario> vediCalendari() {
        return calendarioRepository.findAll();
    }
}