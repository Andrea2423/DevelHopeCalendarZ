
package DevelHope.Calendar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
    //  @OneToMany(mappedBy = "calendario", cascade = CascadeType.ALL, orphanRemoval = true)
    //  private List<Evento> eventi;
    @ManyToMany
    @JoinTable(name = "calendario_utente", joinColumns = @JoinColumn(name = "calendario_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id"))
    private List<Utente> utenti;
    @NotNull @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_creazione_calendario")
    private LocalDate data = LocalDate.now();

    public Calendario(String titolo, String Descrizione, String color) {
        this.titolo = titolo;
        this.descrizione = Descrizione;
        this.color = color;


    }

    public Calendario() {

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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

}

