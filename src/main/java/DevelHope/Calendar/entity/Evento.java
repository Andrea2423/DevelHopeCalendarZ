package DevelHope.Calendar.entity;
import DevelHope.Calendar.entity.Evento;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Data
@Entity
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
    private List<Utente> invitati;
    @NotNull
    private LocalDate data;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
}