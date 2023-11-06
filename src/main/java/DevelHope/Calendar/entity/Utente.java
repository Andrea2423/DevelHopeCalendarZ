package DevelHope.Calendar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;



@Entity
@Table(name = "users")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @ManyToMany
    private Set<Evento> eventi;

    public Utente() {
    }
}