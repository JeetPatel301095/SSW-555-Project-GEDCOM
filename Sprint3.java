import java.lang.reflect.Array;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sprint3 {
	
	/* User stories of Raj Mehta */
	
	/* Husband/wife gender not correct */
	public int US21(ArrayList<Person> person, ArrayList<Family> fam) {
		int errorCode = 0;
		
		for(Family f: fam) {
			if(!f.getHusbandId().equals("NA")) {
				for(Person p: person) {
					if(f.getHusbandId().equals(p.getId())) {
						if(!p.getSex().equals("")) {
							if(!p.getSex().equals("M")) {
								System.out.println("ERROR: FAMILY: US21: Family " + f.getId() + "'s husband sex is not male");
								errorCode = 1;
							}
						}
						break;
					}
				}
			}
			
			if(!f.getWifeId().equals("NA")) {
				for(Person p: person) {
					if(f.getWifeId().equals(p.getId())) {
						if(!p.getSex().equals("")) {
							if(!p.getSex().equals("F")) {
								
								System.out.println("ERROR: FAMILY: US21: Family " + f.getId() + "'s wife sex is not female");
								
								if(errorCode == 1)
									errorCode = 3;
								else
									errorCode = 2;
							}
						}
						break;
					}
				}
			}
			
		}
		
		return errorCode;
	}
	
	// same ID
	public int US22(ArrayList<Person> person, ArrayList<Family> fam) {
		int errorCode = 0;
		
		for(int i = 0; i < person.size() - 1; i++) {
			for(int j = i + 1; j < person.size(); j++) {
				if(person.get(i).getId().equals(person.get(j).getId())) {
					System.out.println("ERROR: INDIVIDUAL: US22: 2 individuals have same IDs: " + person.get(i).getId());
					errorCode = 1;
				}
			}
		}
		
		for(int i = 0; i < fam.size(); i++) {
			for(int j = i + 1; j < fam.size(); j++) {
				if(fam.get(i).getId().equals(fam.get(j).getId())) {
					System.out.println("ERROR: FAMILY: US22: 2 families have same IDs: " + fam.get(i).getId());
					if(errorCode == 1)
						errorCode = 3;
					else
						errorCode = 2;
				}
			}
		}
		
		return errorCode;
	}

    /* Chris Rudel US23
     * Checks to ensure individuals do not have the same first name, last name, and birthdate
     * @param individs - ArrayList which contains all individuals
     * @return
     * 	true: no errors
     * 	false: individuals that have the same first name, last name and birthdate exist
     */
    public boolean US23(ArrayList<Person> individs){
        boolean retVal = true;
        for(int i=0; i<individs.size(); i++){
            String fName = individs.get(i).getFirstName();
            String lName = individs.get(i).getLastName();
            String bDate = individs.get(i).getBirthDate().toString();
            String id = individs.get(i).getId();
            for(int j=i; j<individs.size(); j++){
                if(j==i){
                    continue;
                }else{
                    String fNameCompare = individs.get(j).getFirstName();
                    String lNameCompare = individs.get(j).getLastName();
                    String bDateCompare = individs.get(j).getBirthDate().toString();
                    String idTest = individs.get(j).getId();
                    if(fName.equals(fNameCompare) && lName.equals(lNameCompare) && bDate.equals(bDateCompare)){
                        System.out.println("ERROR: INDIVIDUAL: US23: User with ID: " + idTest + " has the same first name, last name, and birthday as user with ID: " + id);
                        retVal = false;
                    }
                }
            }
        }

        return retVal;
    }

    /* Chris Rudel US24
     * Checks to ensure families have unique spouses; a family's mom and dad cannot have the same name and marriage date as another family
     * @param fams - ArrayList which contains all families
     * @return
     * 	true: no errors
     * 	false: families that have the same name and marriage date exist
     */
    public boolean US24(ArrayList<Family> fams){
        boolean retVal = true;
        for(int i=0; i<fams.size(); i++){
            String husbandName = fams.get(i).getHusbandFullName();
            String wifeName = fams.get(i).getWifeFullName();
            Date firstFamDate = fams.get(i).getMarriageDate();
            String firstFamID = fams.get(i).getId();
            for(int j=i; j<fams.size(); j++){
                if(j==i){
                    continue;
                }else{
                    if(husbandName == null || wifeName == null || firstFamDate == null){
                        continue;
                    }
                    String husbandCompare = fams.get(j).getHusbandFullName();
                    String wifeCompare = fams.get(j).getWifeFullName();
                    Date dateCompare = fams.get(j).getMarriageDate();
                    String idCompare = fams.get(j).getId();
                    if(husbandCompare == null || wifeCompare == null || dateCompare == null){
                        continue;
                    }
                    if(husbandName.equals(husbandCompare) && wifeName.equals(wifeCompare) && firstFamDate.equals(dateCompare)){
                        System.out.println("ERROR: FAMILIES: US24: Family with ID: " + idCompare + " has the same husband name, wife name, and marriage date as family with ID: " + firstFamID);
                        retVal = false;
                    }
                }
            }
        }
        return retVal;
    }

    public boolean US25(ArrayList<Family> fam, ArrayList<Person> Indi) {
		int count = 0;
		for (Family fa : fam) {
			boolean err = true;

            ArrayList<String> familyName = new ArrayList<String>();
            familyName.add(fa.getHusbandFullName());
            familyName.add(fa.getWifeFullName());
            ArrayList<String> a = fa.getChildrenIds();

            for (String e : a) {
                for (Person we : Indi) {
                    if (e.equals(we.getId()))
                    {
                        familyName.add(we.getFullName());
                        break;
                    }
                }
            }
//            System.out.println(familyName.size());
//            System.out.println(familyName);
            for(int i=0;i<familyName.size();i++)
			{
				for(int j=i+1;j<familyName.size();j++)
				{
					String h=familyName.get(i);
					String g= familyName.get(j);
//					System.out.println(h+"  "+g);
					if(h.equals(g))
					{
						count++;
						err=false;
					}
				}
			}
            if(err==false)
			{
				System.out.println("ERROR: FAMILIES: US25: Family with ID: "+fa.getId()+" Have Individuals with the same Name");
			}
        }
		if(count>0)
        	return false;
		else
			return true;
    }
	public ArrayList US29(ArrayList<Person> perList){
		ArrayList<Person> res = new ArrayList<>();
		for(Person p:perList)
		{
			if(p.getDead())
			{
				res.add(p);
			}

		}
		return res;
	}

    public ArrayList US30(ArrayList<Person> personArrayList){
    	ArrayList<Person> res = new ArrayList<>();
		for(Person p:personArrayList){
			if(!p.getFams().equals("NA") && !p.getDead()){//means this person is married and is alive
				res.add(p);
			}

		}
    	return res;
	}

	public ArrayList US31(ArrayList<Person> personArrayList){
		ArrayList<Person> res = new ArrayList<>();
		for(Person p:personArrayList){
			if(p.getAge() > 30){
				if(p.getFams().equals("NA") && !p.getDead()){//means this person does not have a spouse and single.
					res.add(p);
				}
			}
		}

		return res;
	}
}