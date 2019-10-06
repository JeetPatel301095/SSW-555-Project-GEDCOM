import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sprint2 {
	
	public int US09(ArrayList<Person> person, ArrayList<Family> family) {
		
		Person husb = null;
		Person wife = null;
		Date husbDeath = null;
		Date wifeDeath= null;
		int errorCode = 0;
		
		for(int i = 0; i < family.size(); i++) {
			Family fam = family.get(i);
			ArrayList<String> children = fam.getChildrenIds();
			if(!children.isEmpty()) { // the family has children!
				for(int j = 0; j < person.size(); j++) {
					husb = person.get(j);
					if(fam.getHusbandId().equals(husb.getId())) { // check if husband
						if(husb.getDead()) {
							husbDeath = husb.getDeathDate();
						}
					}
					else { // check if wife
						wife = person.get(j);
						if(fam.getWifeId().equals(wife.getId())) {
							if(wife.getDead()) {
								wifeDeath = wife.getDeathDate();
							}
						}
					}
				}
			
				for(String child: children) { // find each children
					for(Person c: person) {
						if(child.equals(c.getId())) {
							if(wife.getDead()) {
								if(wifeDeath.compareTo(c.getBirthDate()) < 1) {
									System.out.println("ERROR: INDIVIDUAL: US09: Child " + c.getId() + " born after mother's death");
									errorCode = 1;
								}
							}
							if(husb.getDead()) {
								if(husbDeath.compareTo(c.getBirthDate()) < 1) {
									if(HelperMethods.calculateMonth(husbDeath, c.getBirthDate()) > 9) {
										System.out.println("ERROR: INDIVIDUAL: US09: Child " + c.getId() + " born after 9 months of father's death");
										if(errorCode == 1) {
											errorCode = 3;
										}
										else {
											errorCode = 2;
										}
									}
								}
							}
						}
					}
				}
				
			}
		}
		
		return errorCode;
	}
	
	public int US10(ArrayList<Person> person, ArrayList<Family> family) {
		int errorCode = 0;
		for(Family f: family) {
			for(Person p: person) {
				if(f.getHusbandId().equals(p.getId())) {
					if(HelperMethods.calculateYear(p.getBirthDate(), f.getMarriageDate()) < 14) {
						System.out.println("ERROR: FAMILY: US10: Husband got married before age of 14 in family " + f.getId());
						errorCode = 1;
					}
				}
				else {
					if(f.getWifeId().equals(p.getId())) {
						if(HelperMethods.calculateYear(p.getBirthDate(), f.getMarriageDate()) < 14) {
							System.out.println("ERROR: FAMILY: US10: Wife got married before age of 14 in family " + f.getId());
							if(errorCode == 1) {
								errorCode = 3;
							}
							else {
								errorCode = 2;
							}
						}
					}
				}
			}
		}
		
		return errorCode;
	}
}