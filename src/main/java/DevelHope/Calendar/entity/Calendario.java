
package DevelHope.Calendar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToMany(mappedBy = "calendario")
    private List<Evento> eventi ;
    @ManyToOne
    @JoinColumn(name = "user_calendar_id")
    @JsonIgnore
    private Utente utente;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_creazione_calendario")
    private LocalDate dataCreazioneCalendario;


    public Calendario() { }

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


    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void setData(LocalDate data) {
        this.dataCreazioneCalendario = dataCreazioneCalendario;
    }

}

