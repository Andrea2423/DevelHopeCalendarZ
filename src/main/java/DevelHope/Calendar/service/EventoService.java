package DevelHope.Calendar.service;

import DevelHope.Calendar.entity.Evento;
import DevelHope.Calendar.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    EventoRepository eventoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public void createEvento(Evento evento) {
        eventoRepository.save(evento);
    }

    public void deleteEvento(long id) {
        eventoRepository.deleteById(id);
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
                    if (evento.getColor() != null) {
                        eventoEsistente.setColor(evento.getColor());

                    }
                    if (evento.getStartTime() != null) {
                        eventoEsistente.setStartTime(evento.getStartTime());
                    }
                    if (evento.getEndTime() != null) {
                        eventoEsistente.setEndTime(evento.getEndTime());
                    }
                    if (evento.getInvitati() != null) {
                        eventoEsistente.setInvitati(evento.getInvitati());
                    }
                    if (evento.getCalendari() != null) {
                        eventoEsistente.setCalendari(evento.getCalendari());
                    }

                    return eventoRepository.save(eventoEsistente);
                })
                .orElse(null);
    }

    public Optional<Evento> vediEvento(long id) {
        return eventoRepository.findById(id);
    }

    public List<Evento> vediEvento() {
        return eventoRepository.findAll();
    }
}