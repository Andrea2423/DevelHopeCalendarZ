package DevelHope.Calendar.repository;

import DevelHope.Calendar.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    @Modifying
    @Query("UPDATE Utente u SET u.nome = :nome, " +
            "u.cognome = :cognome, " +
            "u.password = :password " +
            "WHERE u.id = :id")
    void updateUser(@Param("id") int id,
                    @Param("nome") String nome,
                    @Param("cognome") String cognome,
                    @Param("password") String password);



    Optional<Utente> findByEmail(String email);
}