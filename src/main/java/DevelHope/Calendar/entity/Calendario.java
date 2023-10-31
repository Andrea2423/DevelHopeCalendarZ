package DevelHope.Calendar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
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
    private List<Utente> utenti;
    @NotNull
    private LocalDate data;
    }
