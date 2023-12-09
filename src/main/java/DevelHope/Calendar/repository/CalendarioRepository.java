package DevelHope.Calendar.repository;

import DevelHope.Calendar.entity.Calendario;
import DevelHope.Calendar.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Long> {

    @Modifying
    @Query("UPDATE Calendario c SET c.titolo = :titolo, " +
            "c.descrizione = :descrizione, " +
            "c.utente = :utente " +
            "WHERE c.id = :id")
    void updateCalendar(@Param("id") int id,
                        @Param("titolo") String titolo,
                        @Param("descrizione") String descrizione,
                        @Param("utente") Utente utente);

}
