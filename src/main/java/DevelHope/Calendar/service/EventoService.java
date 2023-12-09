package DevelHope.Calendar.service;

import DevelHope.Calendar.entity.Calendario;
import DevelHope.Calendar.entity.Evento;
import DevelHope.Calendar.entity.Utente;
import DevelHope.Calendar.repository.CalendarioRepository;
import DevelHope.Calendar.repository.EventoRepository;
import DevelHope.Calendar.repository.UtenteRepository;
import DevelHope.Calendar.util.RecurrenceManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    CalendarioRepository calendarioRepository;
    @Autowired
    UtenteRepository utenteRepository;

    public Evento createEvent(long calendarioID, Evento event, LocalDateTime startTime, int duration) throws Exception {

        RecurrenceManager recurrenceManager = new RecurrenceManager();

        if (calendarioRepository.findById(calendarioID).isPresent()) {
            Calendario calendario = calendarioRepository.findById(calendarioID).orElse(null);

            event.setCalendario(calendario);

            recurrenceManager.setRecurrenceTimeAndDuration(event, startTime, duration);
        } else {
            throw new Exception(String.format("Calendar with ID %s not found", calendarioID));
        }

        return eventoRepository.save(event);
    }

    public String deleteEvento(long id) throws Exception {
        if (eventoRepository.findById(id).isPresent()) {
            eventoRepository.deleteById(id);
            return String.format("Event id n° %S deleted", id);
        } else {
            throw new Exception(String.format("Event with ID %s not found", id));
        }
    }

    public Evento updateEvento(long id, Evento evento) {
        return eventoRepository.findById(id)
                .map(eventoEsistente -> {
                    if (evento.getTitolo() != null) {
                        eventoEsistente.setTitolo(evento.getTitolo());
                    }
                    if (evento.getDescrizione() != null) {
                        eventoEsistente.setDescrizione(evento.getDescrizione());

                    }
                    if (evento.getStartTime() != null) {
                        eventoEsistente.setStartTime(evento.getStartTime());
                    }
                    if (evento.getEndTime() != null) {
                        eventoEsistente.setEndTime(evento.getEndTime());
                    }

                    return eventoRepository.save(eventoEsistente);
                })
                .orElse(null);
    }

    public List<Evento> vediEvento(long calendarioID) throws Exception {
        if (calendarioRepository.findById(calendarioID).isPresent()) {
            Calendario calendar = calendarioRepository.findById(calendarioID).orElse(null);

            List<Evento> eventi;
            eventi = calendar.getEventi();

            return eventi;

        } else {
            throw new Exception(String.format("Calendar with ID %s not exist", calendarioID));
        }
    }
    @Transactional
    public String inviteUser(long eventoID, long utenteID) throws Exception{

        Evento event = eventoRepository.findById(eventoID).get();
        Utente user = utenteRepository.findById(utenteID).get();

        if (event == null || user == null){

            throw new Exception("Event or user not found");
        } else {
            event.getInvitati().add(user);
            user.getEventi().add(event);
            eventoRepository.save(event);
            utenteRepository.save(user);
            return "User successfully invited ";
        }

    }
}