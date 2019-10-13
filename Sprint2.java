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

	/* Chris Rudel US11
	 * Checks to ensure there is no bigamy in the families
	 * @param fams - ArrayList which contains all families
	 * @return
	 * 	0: no errors
	 * 	1: there is bigamy for a husband
	 * 	2: bigamy for a wife
	 *  3: error, missing information
	 */
	public int US11(ArrayList<Family> fams){
		int retVal = 0;
		for(int i=0; i<fams.size(); i++){
			String currentFamID = fams.get(i).getId();
			String dadID = fams.get(i).getHusbandId();
			String momID = fams.get(i).getWifeId();
			if(currentFamID == null || currentFamID.equals("")){
				System.out.println("Error: US11: One of the families has no ID");
				return 3;
			}
			if(dadID == null || dadID.equals("")){
				System.out.println("Error: US11: One of the fathers has no ID");
				return 3;
			}
			if(momID == null || momID.equals("")){
				System.out.println("Error: US11: One of the mothers has no ID");
				return 3;
			}

			for(int j=i; j<fams.size(); j++){
				if(j==i){
					continue;
				}
				String famCheck = fams.get(j).getId();
				String dadCheck = fams.get(j).getHusbandId();
				String momCheck = fams.get(j).getWifeId();
				if(dadID.equals(dadCheck)){
					System.out.println("ERROR: FAMILY: US11: Families with IDs " + currentFamID + " and " + famCheck + ": Father with ID: " + dadID + " is father of both families");
					retVal = 1;
				}
				else if(momID.equals(momCheck)){
					System.out.println("ERROR: FAMILY: US11: Families with IDs " + currentFamID + " and " + famCheck + ": Mother with ID: " + momID + " is mother of both families");
					retVal = 2;
				}
			}
		}
		return retVal;
	}
	/* Chris Rudel US12
	 * Checks to ensure mother is < 60 years older than child and father is < 80 years older
	 * @param people - ArrayList which contains all individuals
	 * 		  fams - ArrayList which contains all families
	 * @return
	 * 	0: no errors
	 * 	1: husband is too old for child
	 * 	2: wife is too old for child
	 *  3: error, missing information
	 */
	public int US12(ArrayList<Person> people, ArrayList<Family> fams){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int retVal = 0;
		for(Family fam: fams){
			String dadId = fam.getHusbandId();
			String momId = fam.getWifeId();
			Person dad = null;
			Person mom = null;
			for(Person individ: people){
				if(individ.getId().equals(dadId)){
					dad = individ;
				}
				else if(individ.getId().equals(momId)){
					mom = individ;
				}
			}
			if(dad == null || mom == null){
				System.out.println("Error: US12: Mom or dad not found");
				return 3;
			}
			for(String childId: fam.getChildrenIds()){
				Person child = null;
				for(Person individ: people){
					if(individ.getId().equals(childId)){
						child = individ;
					}
				}
				if(child == null){
					System.out.println("Error: US12: Child with id: " + childId + " not found.");
					return 3;
				}
				if(dad.getBirthDate() == null){
					System.out.println("Error: US12: Dad with id: " + dad.getId() + " has no birthdate.");
					return 3;
				}
				if(mom.getBirthDate() == null){
					System.out.println("Error: US12: Mom with id: " + mom.getId() + " has no birthdate.");
					return 3;

				}
				if(child.getBirthDate() == null){
					System.out.println("Error: US12: Child with id: " + child.getId() + " has no birthdate.");
					return 3;

				}
				if(HelperMethods.calculateYear(dad.getBirthDate(), child.getBirthDate()) > 80){
					System.out.println("Error: FAMILY: US12: " + fam.getId() + ": Father's birth date: " +  simpleDateFormat.format(dad.getBirthDate()) + " more than 80 years past child " + childId + " birth date: " + simpleDateFormat.format(child.getBirthDate()));
					retVal = 1;
					continue;
				}
				else if(HelperMethods.calculateYear(mom.getBirthDate(), child.getBirthDate()) > 60){
					System.out.println("Error: FAMILY: US12: " + fam.getId() + ": Mothers's birth date: " +  simpleDateFormat.format(mom.getBirthDate()) + " more than 60 years past child " + childId + " birth date: " + simpleDateFormat.format(child.getBirthDate()));
					retVal = 2;
					continue;
				}
			}

		}
		return retVal;
	}
	
	/*
	Jeet Patel US13
	Siblings Spacing- Birthdays atleast 8 months or less than 2 days
	*/
	public boolean US13(ArrayList<Family> fam, ArrayList<Person> indi)
	{
		boolean err = true;
		for(Family fa:fam)
		{
			ArrayList<Person> children = new ArrayList<Person>();
			ArrayList<String> a = fa.getChildrenIds();
			// System.out.println(a);
			for(String e:a)
			{
				// System.out.println(e);
				for(Person we: indi)
				{
					if(e.equals(we.getId()))
					{
						children.add(we);
						break;
					}
				}
			}
			if(children.size()<=1)
				return true;
			if(children.size()==2)
			{
				long difference = (children.get(1).getBirthDate().getTime()-children.get(0).getBirthDate().getTime())/(86400000);
				if(difference<0)
					difference*=-1;
				if((difference>2)&&(difference<244))
				{
					System.out.println("ERROR: FAMILY: US13: "+fa.getId()+ " has siblings spacing of less than 8 months and more than 2 days.");
					err=false;
				}
			}
        	else
			{
				for(int y=0; y<children.size();y++)
				{
					// System.out.println(children.get(y).getId());
					for(int z=y+1;z<children.size();z++)
					{
						long difference = (children.get(y).getBirthDate().getTime()-children.get(z).getBirthDate().getTime())/(86400000);
						if(difference<0)
							difference*=-1;
						if((difference>2)&&(difference<244))
						{
							System.out.println("ERROR: FAMILY: US13: "+fa.getId()+ " has siblings spacing of less than 8 months and more than 2 days.");
							err=false;
							// System.out.println(children.get(y).getBirthDate()+" ,  "+children.get(z).getBirthDate()+"  , "+difference);
						}
					}
				}
			}
			// System.out.println(children);
		}
		return err;
	}


	/*
	Jeet Patel US14
	Multiple Births- No more than 5 siblings should be born at the same time
	*/

	public boolean US14(ArrayList<Family> fam, ArrayList<Person> ind)
	{
		SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
		boolean err = true;
		for(Family fa:fam)
		{
			ArrayList<Person> children = new ArrayList<Person>();
			ArrayList<String> a = fa.getChildrenIds();
			System.out.println(a);
			for(String e:a)
			{
				// System.out.println(e);
				for(Person we: ind)
				{
					if(e.equals(we.getId()))
					{
						children.add(we);
						// System.out.println(we.getFirstName());
						break;
					}
				}
			}
			int count=0;
			for(int i=0;i<children.size();i++)
			{
				for(int j=i+1;j<children.size();j++)
				{
					String c1 = sdfo.format(children.get(i).getBirthDate());
					String c2 = sdfo.format(children.get(j).getBirthDate());
					// System.out.println(c1+"  , "+c2+"    ,   =  "+c2.compareTo(c1));
					if(c1.equals(c2))
					{
						count++;
					}
				}
			}
			if(count>5)
			{
				err= false;
				System.out.println("ERROR: FAMILY: US16: "+fa.getId()+ " has more than 5 siblings born at the same time. ");
			}
		}
		return err;
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