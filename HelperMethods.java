import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HelperMethods {
	public static int calculateMonth(Date start, Date end){
        YearMonth m1 = YearMonth.from(start.toInstant().atZone(ZoneId.of("UTC")));
        YearMonth m2 = YearMonth.from(end.toInstant().atZone(ZoneId.of("UTC")));

        return (int)m1.until(m2, ChronoUnit.MONTHS) + 1;
    }

    public static int calculateYear(Date start, Date end){
        Calendar a = getCalendar(start);
        Calendar b = getCalendar(end);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }
    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
