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

	
	public boolean US13(ArrayList<Family> fam, ArrayList<Person> indi)
	{
		System.out.println(fam);
		return true;
	}


	public boolean US15(ArrayList<Family> family){
		boolean res = true;
		int count = 0;
		for(Family fam:family){
			count = fam.getChildrenIds().size();
			if(count>=15) {
				res = false;
				System.out.println("ERROR: FAMILY: US15: "+fam.getId()+ " has more than 15 siblings. ");
			}
		}
		return res;
	}

	public boolean US16(ArrayList<Family> family, ArrayList<Person> person){
		boolean res = true;
		int count = 0;
		String temp="";
		for(Family fam:family){
			temp = fam.getHusbandLastName();
			for(Person p:person){
				if(fam.getChildrenIds().contains(p.getId())){
					res = temp.equals(p.getLastName());
					if(!res){
						System.out.println("ERROR: FAMILY: US16: "+fam.getId()+ " Male family members do not have the same last names. ");
						break;
					}
				}
			}
		}
		return res;
	}
}