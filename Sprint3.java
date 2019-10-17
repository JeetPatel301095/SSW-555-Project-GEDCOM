import java.lang.reflect.Array;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sprint3 {

    /* Chris Rudel US23
     * Checks to ensure individuals do not have the same first name, last name, and birthdate
     * @param individs - ArrayList which contains all individuals
     * @return
     * 	true: no errors
     * 	1: individuals that have the same first name, last name and birthdate exist
     */
    public boolean US23(ArrayList<Person> individs){
        boolean retVal = true;
        for(int i=0; i<individs.size(); i++){
            String fName = individs.get(i).getFirstName();
            String lName = individs.get(i).getLastName();
            Date bDate = individs.get(i).getBirthDate();
            String id = individs.get(i).getId();
            for(int j=i; j<individs.size(); j++){
                if(j==i){
                    continue;
                }else{
                    String fNameCompare = individs.get(j).getFirstName();
                    String lNameCompare = individs.get(j).getLastName();
                    Date bDateCompare = individs.get(j).getBirthDate();
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
                    String husbandCompare = fams.get(j).getHusbandFullName();
                    String wifeCompare = fams.get(j).getWifeFullName();
                    Date dateCompare = fams.get(j).getMarriageDate();
                    String idCompare = fams.get(j).getId();
                    if(husbandName.equals(husbandCompare) && wifeName.equals(wifeCompare) && firstFamDate.equals(dateCompare)){
                        System.out.println("ERROR: FAMILIES: US24: Family with ID: " + idCompare + " has the same husband name, wife name, and marriage date as family with ID: " + firstFamID);
                        retVal = false;
                    }
                }
            }
        }
        return retVal;
    }

}