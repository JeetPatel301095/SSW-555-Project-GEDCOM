import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.*;
import static java.util.Calendar.DATE;

public class Sprint1 {
	
	/* Raj Mehta US01 implementation
	 * Tests birth, death, marriage and divorce dates should not be after current date
	 * @param person - ArrayList which contains all individuals
	 * 		  fam - ArrayList which contains all families
	 * @return errorCode - Integer value to denote what went wrong
	 * 	0: all good, 1: birth date after current date, 2: death date,
	 *  3: marriage date, 4: divorce date, 5: multiple
	 */
	public int US01(ArrayList<Person> person, ArrayList<Family> fam) {
		Date currentDate = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		int errorCode = 0;
		
		for(int i = 0; i < person.size(); i++) {
			//Birth date
			Date birthDate = person.get(i).getBirthDate();
			
			if(birthDate != null) {
				if(currentDate.compareTo(birthDate) < 1) {
					errorCode = 1;
					System.out.println("ERROR: INDIVIDUAL: US01: Birthday " + s.format(birthDate) + " occurs in the future");
				}
			}
			
			//Death date
			Date deathDate = person.get(i).getDeathDate();
			if(deathDate != null) {
				if(currentDate.compareTo(deathDate) < 1) {
					if(errorCode == 0)
						errorCode = 2;
					else
						errorCode = 5;
					System.out.println("ERROR: INDIVIDUAL: US01: Death date " + s.format(deathDate) + " occurs in the future");
				}
			}
		}
		
		for(int i = 0; i < fam.size(); i++) {
			//Marriage date
			Date mDate = fam.get(i).getMarriageDate();
			
			if(mDate != null) {
				if(currentDate.compareTo(mDate) < 1) {
					if(errorCode == 0)
						errorCode = 3;
					else
						errorCode = 5;
					System.out.println("ERROR: FAMILY: US01: Marriage date " + s.format(mDate) + " occurs in the future");
				}
			}
			
			//Divorce date
			Date dDate = fam.get(i).getDivorceDate();
			
			if(dDate != null) {
				if(currentDate.compareTo(dDate) < 1) {
					if(errorCode == 0)
						errorCode = 4;
					else
						errorCode = 5;
					System.out.println("ERROR: FAMILY: US01: Divorce date " + s.format(dDate) + " occurs in the future");
				}
			}
		}
		return errorCode;
	}
	
	/* Raj Mehta US02 implementation
	 * Tests marriage and divorce date should not occur before birth date of spouse
	 * @param person - ArrayList which contains all individuals
	 * 		  fam - ArrayList which contains all families
	 * @return errorCode - Integer value to denote what went wrong
	 * 	0: all good
	 * 	1: huband's birthdate after marriage date
	 * 	2: wife's birthdate after marriage date
	 *  3: both
	 */
	public int US02(ArrayList<Person> person, ArrayList<Family> fam) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		int errorCode = 0;
		Date marriage, bd;
		for(int i = 0; i < fam.size(); i++) {
			marriage = fam.get(i).getMarriageDate();
			if(marriage != null) {
				// husband
				for(int j = 0; j < person.size(); j++) {
					if(fam.get(i).getHusbandId().equals(person.get(j).getId())) {
						bd = person.get(j).getBirthDate();
						if(bd != null) {
							if(marriage.compareTo(bd) < 1) {
								errorCode = 1;
								System.out.println("ERROR: FAMILY: US02: Marriage date " + s.format(marriage) + " occurs before husband's birth date " + s.format(person.get(j).getBirthDate()));
							}
						}
					}
				}
				
				// wife
				for(int j = 0; j < person.size(); j++) {
					if(fam.get(i).getWifeId().equals(person.get(j).getId())) {
						bd = person.get(j).getBirthDate();
						if(bd != null) {
							if(marriage.compareTo(bd) < 1) {
								if(errorCode == 0)
									errorCode = 2;
								else
									errorCode = 3;
								System.out.println("ERROR: FAMILY: US02: Marriage date " + s.format(marriage) + " occurs before wife's birth date " + s.format(person.get(j).getBirthDate()));
							}
						}
					}
				}
			}
		}
		
		return errorCode;
	}

    /*
    *   Christopher Rudel
    *   Test US04
    *   Checks that the divorce date does not occur before the marriage date
    *   @param family - an array of families to check
    *   @return - if the family's divorce date is after the marriage date -> true
    *             if the family's divorce date is before the marriage date -> false
     */
    public boolean US04(ArrayList<Family> family){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int f=0;
        for(Family fam: family){
            if(!fam.getDivorced()){ //If divorced, it will skip the return statement
                continue;
            }
            Date mDate = fam.getMarriageDate();
            Date dDate = fam.getDivorceDate();
            if(mDate == null){
                System.out.println("ERROR: US04: No marriage date available for family: " + fam.getId());
                continue;
            }
            if(dDate == null){
                System.out.println("ERROR: US04: No divorce date available for divorced family: " + fam.getId());
                continue;
            }
            if(dDate.before(mDate)){
                System.out.println("ERROR: FAMILY: US04: " + fam.getId() + ": Divorced " + simpleDateFormat.format(dDate) + " before married " + simpleDateFormat.format(mDate));
                f=1;
                continue;
            }
        }
        if(f==0)
	        return true;
        else
            return false;
    }
    /*
     *   Christopher Rudel
     *   Test US05
     *   Checks that the marriage date occurs before either spouse dies
     *   @param people - an array of individuals
     *          family - an array of families to check
     *   @return - if the death date of both the husband or wife is after the marriage date -> true
     *             if the death date of either the husband or wife is before the marriage date -> false
     */
    public boolean US05(ArrayList<Person> people, ArrayList<Family> family){
        int f=0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(Family fam: family){
            String dad = fam.getHusbandId();
            String mom = fam.getWifeId();
            boolean dadDead = false;
            Date dadDeathDate = null;
            boolean momDead = false;
            Date momDeathDate = null;
            for(Person p: people){
                if(p.getId().equals(dad)){
                    dadDead = p.getDead();
                    if(dadDead){
                        dadDeathDate = p.getDeathDate();
                    }
                }
                if(p.getId().equals(mom)){
                    momDead = p.getDead();
                    if(momDead){
                        momDeathDate = p.getDeathDate();
                    }
                }
            }
            Date mDate = fam.getMarriageDate();
            if(dadDeathDate == null && dadDead){
                System.out.println("ERROR: US05: No death date available for father");
                continue;
            }
            if(momDeathDate == null && momDead){
                System.out.println("ERROR: US05: No death date available for mother");
                continue;
            }
            if(mDate == null){
                System.out.println("ERROR: US05: No marriage date available for the family");
                continue;

            }
            if(dadDead) {
                if (dadDeathDate.before(mDate)) {
                    System.out.println("ERROR: FAMILY: US05 " + fam.getId() + " Married " + simpleDateFormat.format(fam.getMarriageDate()) + " after husband's (" + dad + ") death on " + simpleDateFormat.format(dadDeathDate));
                    f=1;
                    continue;
                }
            }
            if(momDead) {
                if (momDeathDate.before(mDate)) {
                    System.out.println("ERROR: FAMILY: US05 " + fam.getId() + " Married " + simpleDateFormat.format(fam.getMarriageDate()) + " after wife's (" + mom + ") death on " + simpleDateFormat.format(momDeathDate));
                    f=1;
                    continue;
                }
            }
        }
        if(f==0)
            return true;
        else
            return false;
    }

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(Family fam: fams){
            if(fam.getChildrenIds()!=null){ //if the family has child
                for(Person p: person){
                    for(String id: fam.getChildrenIds()) {
                        if (p.getId().equals(id)) { //find the child
                            if (p.getBirthDate().before(fam.getMarriageDate())) {  //if the child birthday is after marriage day.
                                res = false;
                                System.out.println("ANOMALY: FAMILY: US08: " + fam.getId() + "Child " + p.getId() + " born " + simpleDateFormat.format(p.getBirthDate()) + " before marriage on " + simpleDateFormat.format(fam.getMarriageDate()));
                            }
                            if (fam.getDivorced()) {
                                if (calculateMonth(fam.getDivorceDate(),p.getBirthDate()) > 9) {
                                    res = false;
                                    System.out.println("ANOMALY: FAMILY: US08: " + fam.getId() + "Child " + p.getId() + " born " + simpleDateFormat.format(p.getBirthDate()) + " after divorce on(more than 9 months) " + simpleDateFormat.format(fam.getDivorceDate()));
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
        YearMonth m1 = YearMonth.from(start.toInstant().atZone(ZoneId.of("UTC")));
        YearMonth m2 = YearMonth.from(end.toInstant().atZone(ZoneId.of("UTC")));

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

    //Jeet Patel Sprint 1 US03/US06
    public boolean US03(ArrayList<Person> a)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(Person b:a)
        {
            if(b.getDead())
            {
                Date birt = b.getBirthDate();
                Date deat = b.getDeathDate();
                if(birt.compareTo(deat)>0)
                {
                	System.out.println("ERROR: INDIVIDUAL: US03: Died "+simpleDateFormat.format(deat)+" before born "+simpleDateFormat.format(birt));
                	return false;
                }
            }
        }
        return true;
    }

    public boolean US06(ArrayList<Person> person, ArrayList<Family> family)
    {
        int f=0;
        int m=0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(Family fam: family){
            f=0;
            m=0;
            String dad = fam.getHusbandId();
            String mom = fam.getWifeId();
            boolean dadDead = false;
            Date dadDeathDate = null;
            boolean momDead = false;
            Date momDeathDate = null;
            for(Person p: person){
                if(p.getId().equals(dad)){
                    dadDead = p.getDead();
                    if(dadDead){
                        dadDeathDate = p.getDeathDate();
                    }
                }
                if(p.getId().equals(mom)){
                    momDead = p.getDead();
                    if(momDead){
                        momDeathDate = p.getDeathDate();
                    }
                }
            }
            Date dDate = fam.getDivorceDate();
            if(dadDeathDate == null && !dadDead){
                f=1;
            }
            if(momDeathDate == null && !momDead){
                m=1;
            }
            if(dDate==null);
            else
            {
                if(f==0)
                {
                    if(dadDeathDate.before(dDate)){
                        System.out.println("ERROR: FAMILY: US06 : Divorced " + simpleDateFormat.format(fam.getDivorceDate()) + " after husband's death on " + simpleDateFormat.format(dadDeathDate));
                        return false;
                    }
                }
                if(m==0)
                {
                    if(momDeathDate.before(dDate)){
                        System.out.println("ERROR: FAMILY: US06 : Divorced " + simpleDateFormat.format(fam.getMarriageDate()) + " after wife's death on " + simpleDateFormat.format(momDeathDate));
                        return false;
                    }
                }
            }
        }
        return true;
    }
}