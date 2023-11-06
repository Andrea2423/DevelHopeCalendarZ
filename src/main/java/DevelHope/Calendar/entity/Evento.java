package DevelHope.Calendar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String titolo;
    private String descrizione;
    @NotBlank
    private String color;
    @ManyToMany
    @JoinTable(name = "invited",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id"))
    private List<Utente> invitati;
    @NotNull @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data ;
    @NotNull @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startTime;
    @NotNull @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endTime;
    @ManyToOne
    @JoinColumn(name = "calendario_id")
    private Calendario calendario;

    public Evento() {
    }
}