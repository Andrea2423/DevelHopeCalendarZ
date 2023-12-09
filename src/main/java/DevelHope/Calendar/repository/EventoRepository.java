package DevelHope.Calendar.repository;

import DevelHope.Calendar.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    @Modifying
    @Query("UPDATE Evento e SET e.titolo = :titolo, " +
            "e.descrizione = :descrizione, " +
            "e.startTime = :startTime, " +
            "e.endTime = :endTime " +
            "WHERE e.id = :id")
    void updateEvent(@Param("id") int id,
                     @Param("titolo") String titolo,
                     @Param("descrizione") String descrizione,
                     @Param("startTime") LocalDateTime startTime,
                     @Param("endTime") LocalDateTime endTime);}
