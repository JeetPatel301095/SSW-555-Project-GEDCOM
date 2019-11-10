import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Sprint4 {
	
	public int US32(ArrayList<Person> indi, ArrayList<Family> fams) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int errorCode = 0;
		ArrayList<ArrayList<Person>> a = new ArrayList<ArrayList<Person>>();
		ArrayList<Person> temp;
		boolean flag;
		HashSet<Date> dates;
		
		for(Family fam: fams) {
			if(!fam.getChildrenIds().isEmpty()) {
				ArrayList<String> c = fam.getChildrenIds();
				Person p1 = new Person();
				Person p2 = new Person();
				dates = new HashSet<Date>(); ;
				for(int i = 0; i < c.size() - 1; i++) { // i loop
					for(Person p: indi) {
						if(c.get(i).equals(p.getId())) {
							p1 = p;
							break;
						}
					}
					
					if(p1.getBirthDate() != null) {
						if(dates.contains(p1.getBirthDate()))
							continue;
					}
					
					temp = new ArrayList<Person>();
					flag = false;
					
					for(int j = i + 1; j < c.size(); j++) { // j loop
						
						for(Person p: indi) {
							if(c.get(j).equals(p.getId())) {
								p2 = p;
								break;
							}
						}
						
						if(p1.getBirthDate() != null && p2.getBirthDate() != null) {
							if(p1.getBirthDate().compareTo(p2.getBirthDate()) == 0) {
								if(flag == false)
									flag = true;
								
								if(errorCode == 0)
									errorCode = 1;
								
								dates.add(p1.getBirthDate());
								
								temp.add(p2);
							}
						}
						
					}
					
					if(flag == true) {
						temp.add(p1);
					}
					
					if(!temp.isEmpty())
						a.add(temp);
				}
			}
		}
		
		if(!a.isEmpty()) {
			
			System.out.println("US32: List of multiple births:");
			System.out.printf("%10s %28s %25s %20s %20s %20s %20s %20s %20s", "ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
	        System.out.println();
	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			for(ArrayList<Person> births: a) {
				for(Person in: births) {
					System.out.format("%10s %20s %20s %35s %20s %20s %20s %20s %20s",
		                    in.getId(), in.getFirstName() + in.getLastName(), in.getSex(), simpleDateFormat.format(in.getBirthDate()), in.getAge(), !in.getDead(), (in.getDeathDate() == null ? "NA" : simpleDateFormat.format(in.getDeathDate()) ),  in.getFamc(), in.getFams());
		            System.out.println();
				}
			}
		}
		
		return errorCode;
	}
	
	public int US33(ArrayList<Person> indi, ArrayList<Family> fams) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int errorCode = 0;
		boolean husDead, wifeDead;
		ArrayList<Person> orphans = new ArrayList<Person>();
		for(Family fam: fams) {
			husDead = false;
			wifeDead = false;
			if(!fam.getHusbandId().equals("NA")) {
				if(!fam.getWifeId().equals("NA")) {
					String hid = fam.getHusbandId();
					String wid = fam.getWifeId();
					for(Person p: indi) {
						if(p.getId().equals(hid)){
							if(p.getDead()) {
								husDead = true;
							}
						}
					}
					
					for(Person p: indi) {
						if(p.getId().equals(wid)){
							if(p.getDead()) {
								wifeDead = true;
							}
						}
					}
					
					if(husDead && wifeDead) {
						
						if(!fam.getChildrenIds().isEmpty()) {
							for(String id: fam.getChildrenIds()) {
								for(Person p: indi) {
									if(id.equals(p.getId())) {
										if(p.getAge() < 18) {
											orphans.add(p);
											
											if(errorCode == 0)
												errorCode = 1; // means 1 orphan
											else if(errorCode == 1)
												errorCode = 2; // means multiple orphans
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println("US33: List of all orphans:");
		System.out.printf("%10s %28s %25s %20s %20s %20s %20s %20s %20s", "ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for(Person in: orphans) {
			System.out.format("%10s %20s %20s %35s %20s %20s %20s %20s %20s",
                    in.getId(), in.getFirstName() + in.getLastName(), in.getSex(), simpleDateFormat.format(in.getBirthDate()), in.getAge(), !in.getDead(), (in.getDeathDate() == null ? "NA" : simpleDateFormat.format(in.getDeathDate()) ),  in.getFamc(), in.getFams());
            System.out.println();
		}
		
		return errorCode;
	}


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
    public ArrayList<Person> US37(ArrayList<Person> indi ,ArrayList<Family> fams) {
        ArrayList<String> famids = new ArrayList<String>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        int i = 0;
        ArrayList<Person> a = new ArrayList<Person>();
        for (Person p : indi) {
            if (p.getDead()) {
                Date dDate = p.getDeathDate();
                if (today.before(dDate)) {
                    continue;
                }
                int days = HelperMethods.calculateDays(today, dDate);
                if (days < 30) {
                    String sp = p.getFams();
                    if (sp.equals("NA"))
                        continue;
                    else {
                        for (Family fa : fams) {
                            if (fa.getId().equals(sp)) {
                                if (p.getId().equals(fa.getHusbandId())) {
                                    famids.add(fa.getWifeId());
                                } else {
                                    famids.add(fa.getHusbandId());
                                }
                                for (String t : fa.getChildrenIds()) {
                                    famids.add(t);
                                }
                                for (String g : famids) {
                                    for (Person r : indi) {
                                        if (g.equals(r.getId())) {
                                            if (!r.getDead()) {
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
    public ArrayList US38(ArrayList<Person> indi){
        Date now = new Date();
        ArrayList<Person> res = new ArrayList<>();
        for(Person p:indi){
            if(isInThirty(now, p.getBirthDate()) && !p.getDead()){
                res.add(p);
            }
        }
        return res;
    }

    public ArrayList US39(ArrayList<Family> families, ArrayList<Person> people){
        ArrayList<Family> res = new ArrayList<>();
        Date now = new Date();
        for(Family family:families){
            try {
                if (isInThirty(now, family.getMarriageDate())) {
                    res.add(family);
                }
            }catch (Exception e){
                continue;
            }
        }
        return res;
    }

    //If the anniversaries date is in Jan, then to compare the difference between days, make the year of the anniversaries date one year greater than current date. Then calculates difference of days
    //otherwise, just make the year of anniversaries the same as now and others remain the unchanged.

    public boolean isInThirty(Date now, Date anniversaries){
        //return true if the dates are within 30 days ignoring year.
        Calendar cal = Calendar.getInstance();
        cal.setTime(anniversaries);
        int monthOfMarriage = cal.get(Calendar.MONTH);    //Note month is zero based which means Jan is 0
        int dayOfMarriage = cal.get(Calendar.DAY_OF_MONTH);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(now);
        int yearOfNow = cal2.get(Calendar.YEAR);

        if(monthOfMarriage == 0){
            Date fakeAnni = new GregorianCalendar(yearOfNow+1, monthOfMarriage, dayOfMarriage).getTime();
            int difference = HelperMethods.calculateDays(fakeAnni, now);
            if(difference <= 30 && difference >= 0)
                return true;
            else
                return false;
        }else{
            Date fakeAnni = new GregorianCalendar(yearOfNow, monthOfMarriage, dayOfMarriage).getTime();
            int difference = HelperMethods.calculateDays(fakeAnni, now);
            if(difference <= 30 && difference >= 0)
                return true;
            else
                return false;
        }


    }
}
