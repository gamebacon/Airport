package with.william.airport.util;

import with.william.airport.Flight;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Util {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");


    public static String timeUntil(LocalDateTime one, LocalDateTime two) {
        long millis = Duration.between(one, two).toMillis();

        return String.format("%d hours and %d minutes.",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))
        );
    }


}
