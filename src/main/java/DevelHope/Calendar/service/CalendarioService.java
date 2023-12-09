package DevelHope.Calendar.service;

import DevelHope.Calendar.entity.Calendario;
import DevelHope.Calendar.entity.Utente;
import DevelHope.Calendar.repository.CalendarioRepository;
import DevelHope.Calendar.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {
    @Autowired
    CalendarioRepository calendarioRepository;
    @Autowired
    UtenteRepository utenteRepository;



    public Calendario createCalendar(long userID, Calendario calendar) throws Exception{
        if (utenteRepository.findById(userID).isPresent()){
            Utente utente = utenteRepository.findById(userID).orElse(null);

            calendar.setUtente(utente);

        } else {
            throw new Exception(String.format("User with ID %s not exist", userID));
        }

        return calendarioRepository.save(calendar);
    }
    public String deleteCalendario(long id) throws Exception {
        if (calendarioRepository.findById(id).isPresent()) {
            calendarioRepository.deleteById(id);
            return String.format("Calendar id n° %S deleted", id);
        } else {
            throw new Exception(String.format("Calendar with ID %s not found", id));
        }
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
                    if (calendario.getDataCreazioneCalendario() != null) {
                        calendarioEsistente.setData(calendario.getDataCreazioneCalendario());
                    }
                    if (calendario.getUtente() != null) {
                        calendarioEsistente.setUtente(calendario.getUtente());
                    }
                    return calendarioRepository.save(calendarioEsistente);
                })
                .orElse(null);
    }

    public Optional<Calendario> vediCalendario(long id) {
        return calendarioRepository.findById(id);
    }

    public List<Calendario> vediCalendariByUser(long userID) throws Exception {
        Optional<Utente> utente = utenteRepository.findById(userID);

        if (utente.isPresent()) {
            return new ArrayList<>(utente.get().getCalendario());
        } else {
            throw new Exception(String.format("User with ID %s don't have calendars", userID));
        }
    }
}