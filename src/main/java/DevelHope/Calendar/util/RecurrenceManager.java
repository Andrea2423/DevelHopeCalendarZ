package DevelHope.Calendar.util;


import DevelHope.Calendar.entity.Evento;

import java.time.LocalDateTime;

public class RecurrenceManager {

    public void setRecurrenceTimeAndDuration(Evento event, LocalDateTime startTime, int duration){

        event.setStartTime(startTime);
        event.setRecurrenceInterval(duration);

        switch (event.getRecurrenceType()){

            case DAILY:
                event.setEndTime(startTime.plusDays(duration));
                break;
            case WEEKLY:
                event.setEndTime(startTime.plusWeeks(duration));
                break;
            case MONTHLY:
                event.setEndTime(startTime.plusMonths(duration));
                break;
            case YEARLY:
                event.setEndTime(startTime.minusYears(duration));
                break;
        }
    }

}
