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

    public ArrayList<Person> US36(ArrayList<Person> indi)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        ArrayList<Person> a = new ArrayList<Person>();
        for(Person p:indi) {
            if (p.getDead()) {
                Date dDate = p.getDeathDate();
                if (today.before(dDate)) {
                    continue;
                }
                int days = HelperMethods.calculateDays(today, dDate);
                if (days < 30) {
                    a.add(p);
                }
            }
        }
        return a;
    }
    public ArrayList<Person> US37(ArrayList<Person> indi ,ArrayList<Family> fams)
    {
        ArrayList<String> famids = new ArrayList<String>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        int i=0;
        ArrayList<Person> a = new ArrayList<Person>();
        for(Person p:indi) {
            if (p.getDead()) {
                Date dDate = p.getDeathDate();
                if (today.before(dDate)) {
                    continue;
                }
                int days = HelperMethods.calculateDays(today, dDate);
                if (days < 30) {
                    String sp = p.getFams();
                    if(sp.equals("NA"))
                        continue;
                    else
                    {
                        for(Family fa :fams)
                        {
                            if(fa.getId().equals(sp))
                            {
                                if(p.getId().equals(fa.getHusbandId()))
                                {
                                    famids.add(fa.getWifeId());
                                }
                                else
                                {
                                    famids.add(fa.getHusbandId());
                                }
                                for(String t:fa.getChildrenIds())
                                {
                                    famids.add(t);
                                }
                                for(String g:famids)
                                {
                                    for(Person r:indi)
                                    {
                                        if(g.equals(r.getId()))
                                        {
                                            if(!r.getDead())
                                            {
                                                a.add(r);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return a;
    }
}
