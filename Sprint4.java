import java.lang.reflect.Array;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sprint4 {


    /*  Chris Rudel US34
     *  List couples were one spouse was at least twice as old as the other when married
     * @param individs - ArrayList which contains all individuals
     *        fams - ArrayList which contains all families
     */
    public void US34(ArrayList<Person> individs, ArrayList<Family> fams){
        for(Family fam: fams){
            Date marriageDate = fam.getMarriageDate();
            if(marriageDate == null){
                continue;
            }
            String dadID = fam.getHusbandId();
            String momID = fam.getWifeId();
            Date dadBDay = null;
            Date momBDay = null;
            for(Person person: individs){
                if(person.getId().equals(dadID)){
                    dadBDay = person.getBirthDate();
                }
            }
            for(Person person: individs){
                if(person.getId().equals(momID)){
                    momBDay = person.getBirthDate();
                }
            }
            if(dadBDay == null || momBDay == null){
                continue;
            }
            if(marriageDate.before(dadBDay) || marriageDate.before(momBDay)){
                System.out.println("ERROR: US34: Father or mother of family ID: " + fam.getId() + " was born after marriage date");
                continue;
            }
            int dadMarriageAge = HelperMethods.calculateYear(dadBDay, marriageDate);
            int momMarriageAge = HelperMethods.calculateYear(momBDay, marriageDate);
            if( dadMarriageAge >= momMarriageAge * 2 ){
                System.out.println("US34: FAMILIES: Family with ID: " + fam.getId() + "s father was more than twice the age of the mother when they got married. (Dad was: " + dadMarriageAge + ", mom was: " + momMarriageAge + ")");
            }else if( momMarriageAge >= dadMarriageAge * 2 ){
                System.out.println("US34: FAMILIES: Family with ID: " + fam.getId() + "s mother was more than twice the age of the father when they got married. (Mom was: " + momMarriageAge + ", dad was: " + dadMarriageAge + ")");
            }
        }

    }
    /*  Chris Rudel US35
     *  List individuals who were born in the past 30 days
     * @param individs - ArrayList which contains all individuals
     */
    public void US35(ArrayList<Person> individs){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        //System.out.println(simpleDateFormat.format(today));
        for(Person person: individs){
            Date bDate = person.getBirthDate();
            if(today.before(bDate) || bDate == null || bDate.equals("")){
                continue;
            }
            int days = HelperMethods.calculateDays(today, bDate);
            if(days <= 30){
                System.out.println("US35: INDIVIDUALS: Individual with ID: " + person.getId() + " was born within the past 30 days (" + days + " days ago)");
            }
        }
    }

    public ArrayList US38(ArrayList<Person> indi){
        Date now = new Date();
        ArrayList<Person> res = new ArrayList<>();
        for(Person p:indi){
            if(HelperMethods.calculateDays(p.getBirthDate(), now) <= 30 && !p.getDead()){
                res.add(p);
            }
        }
        return res;
    }

    public ArrayList US39(ArrayList<Family> families, ArrayList<Person> people){
        ArrayList<Person> res = new ArrayList<>();

        for(Family family:families){

        }
    }

    public boolean calDaysIgnoreYear(Date date, Family family){
        //return true if the dates are within 30 days ignoring year.
        boolean res = false;
        Date now = new Date();
        int years = HelperMethods.calculateYear(family.getMarriageDate(), now);
        int months = HelperMethods.calculateMonth(family.getMarriageDate(), now);
        if(years > 1 && months > 1){

        }

        int month = family.getMarriageDate().getMonth();
        if(month == 1){

        }
        return res;
    }
}
