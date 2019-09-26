import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.*;
import static java.util.Calendar.DATE;

public class Sprint1 {

    //Xueshi Wang Sprint 1 US07/US08
    public boolean US07(ArrayList<Person> person){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean res=true;
        for(Person p:person){
            if(p.getDead()){
                Date deathDate = p.getDeathDate();
                Date birthDate = p.getBirthDate();
                if(calculateYear(birthDate, deathDate) > 150){
                    res = false;
                    System.out.println("ERROR: INDIVIDUAL: US07: "+p.getId()+": More than 150 years old at death. Birth date "+simpleDateFormat.format(p.getBirthDate())+" Death date "+simpleDateFormat.format(p.getDeathDate()));
                }
            }

            else if(p.getAge() > 150){
                res = false;
                System.out.println("ERROR: INDIVIDUAL: US07: "+p.getId()+": More than 150 years old. Birth date "+simpleDateFormat.format(p.getBirthDate()));
            }
        }
        return res;
    }

    public boolean US08(ArrayList<Family> fams, ArrayList<Person> person){
        boolean res = true;
        for(Family fam: fams){
            if(fam.getChildrenIds()!=null){ //if the family has child
                for(Person p: person){
                    for(String id: fam.getChildrenIds()) {
                        if (p.getId().equals(id)) { //find the child
                            if (p.getBirthDate().before(fam.getMarriageDate())) {  //if the child birthday is after marriage day.
                                res = false;
                                System.out.println("ANOMALY: FAMILY: US08: " + fam.getId() + "Child " + p.getId() + " born " + p.getBirthDate() + " before marriage on " + fam.getMarriageDate());
                            }
                            if (fam.getDivorced()) {
                                if (calculateMonth(p.getBirthDate(), fam.getDivorceDate()) > 9) {
                                    res = false;
                                    System.out.println("ANOMALY: FAMILY: US08: " + fam.getId() + "Child " + p.getId() + " born " + p.getBirthDate() + " after divorce on " + fam.getDivorceDate());
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    public int calculateMonth(Date start, Date end){
        YearMonth m1 = YearMonth.from(start.toInstant());
        YearMonth m2 = YearMonth.from(end.toInstant());

        return (int)m1.until(m2, ChronoUnit.MONTHS) + 1;
    }

    public int calculateYear(Date start, Date end){
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
