
package DevelHope.Calendar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "calendar")
public class Calendario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String titolo;
    private String descrizione;
    @NotBlank
    private String color;
    @ManyToMany
    @JoinTable(
            name = "calendario_evento",
            joinColumns = @JoinColumn(name = "calendario_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id")
    )
    private List<Evento> eventi;
    @ManyToMany
    @JoinTable(name = "calendario_utente", joinColumns = @JoinColumn(name = "calendario_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id"))
    private List<Utente> utenti;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_creazione_calendario")
    private LocalDate dataCreazioneCalendario;

    public Calendario(long id, String titolo, String descrizione, String color, List<Evento> eventi, List<Utente> utenti, LocalDate dataCreazioneCalendario) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.color = color;
        this.eventi = eventi;
        this.utenti = utenti;
        this.dataCreazioneCalendario = dataCreazioneCalendario;
    }


    public Calendario() {

    }

    public List<Evento> getEventi() {
        return eventi;
    }

    public void setEventi(List<Evento> eventi) {
        this.eventi = eventi;
    }

    public LocalDate getDataCreazioneCalendario() {
        return dataCreazioneCalendario;
    }

    public void setDataCreazioneCalendario(LocalDate dataCreazioneCalendario) {
        this.dataCreazioneCalendario = dataCreazioneCalendario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }


    public void setData(LocalDate data) {
        this.dataCreazioneCalendario = dataCreazioneCalendario;
    }

}

