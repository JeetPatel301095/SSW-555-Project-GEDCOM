import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

class Project03 {
    public static boolean testing = false;

    public static void main(String args[]) throws Exception {
        // int f=0;
        // Person A[] = new Person();
        int i = 0;

        ArrayList<Person> Indi = new ArrayList<Person>();

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Please enter filename under the same directory: ");
        String fileName = myObj.nextLine();  // Read user input
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> gedComStrings = new ArrayList<String>();

        String newLine;
        while ((newLine = bufferedReader.readLine()) != null) {
            gedComStrings.add(newLine);
        }

        fileReader.close();
        bufferedReader.close();
        int f = gedComStrings.size();
        for (int j = 0; j < f; j++) {
            String inLine = gedComStrings.get(j);
            String[] splitTokens = inLine.split("\\s+");
            // System.out.println(splitTokens[0]);
            if (splitTokens[0].equals("0")) {
                if ((splitTokens[1].equals("NOTE")) || (splitTokens[1].equals("HEAD")) || (splitTokens[1].equals("TRLR"))) {
                    continue;
                }
                if (splitTokens[2].equals("INDI")) {
                    int y = 0;
                    String[] Indidetails = new String[100];
                    Indidetails[y] = inLine;
                    y++;
                    String gline = gedComStrings.get(++j);
                    // System.out.println(gline);
                    String[] split = gline.split("\\s+");
                    while (!(split[0].equals("0"))) {
                        Indidetails[y] = gline;
                        y++;
                        gline = gedComStrings.get(++j);
                        split = gline.split("\\s+");

                    }
                    j--;
                    Indiparser a = new Indiparser();
                    Person temp = a.parseindi(Indidetails);
                    Indi.add(temp);
                }
            }
        }
        
        /*
            I'm sorry about having this entire family parser not be in a separate class like 
            how the indi's were in their own parser, but i wanted to avoid complicating things.
            This should work when all the individuals are defined, the families are put into the 
            "fams" ArrayList below and each family has appropriate getters for formatting the 
            information. 
            For the proj02test.ged file they provided, the getWifeFullName/getWifeId getters do not work
            because the code is trying to lookup the person but the wife was never declared as an INDI.
        */
        ArrayList<Family> fams = new ArrayList<>();
        for (int famI = 0; famI < f; famI++) {
            String currentLine = gedComStrings.get(famI);
            String words[] = currentLine.split(" ");
            if (words[0].equals("0")) {
                if (words[1].equals("HEAD") || words[1].equals("TRLR") || words[1].equals("NOTE")) {/* I do it like this to avoid an out of bounds error */} else if (words[2].equals("FAM")) {
                    Family tempFam = new Family();
                    tempFam.setId(words[1]);
                    int count = 1;
                    boolean nextLineMarried = false;
                    boolean nextLineDivorced = false;
                    boolean divorced = false;
                    while ((famI + count < gedComStrings.size()) && (gedComStrings.get(famI + count).charAt(0) == '1' || gedComStrings.get(famI + count).charAt(0) == '2')) {
                        String nextLineWords[] = gedComStrings.get(famI + count).split(" ");
                        if (nextLineWords[1].equals("HUSB")) {
                            for (Person hubby : Indi) {
                                if (hubby.getId().equals(nextLineWords[2])) {
                                    tempFam.setDad(hubby);
                                }
                            }
                        }
                        if (nextLineWords[1].equals("WIFE")) {
                            for (Person wifey : Indi) {
                                if (wifey.getId().equals(nextLineWords[2])) {
                                    tempFam.setMom(wifey);
                                }
                            }
                        }
                        if (nextLineWords[1].equals("CHIL")) {

                            tempFam.addChild(nextLineWords[2]);
                        }

                        if (nextLineWords[1].equals("MARR")) {
                            nextLineMarried = true;
                        }
                        if (nextLineMarried && nextLineWords[0].equals("2")) {
                            String date = nextLineWords[2] + " " + nextLineWords[3] + " " + nextLineWords[4];
                            tempFam.setMarriageDate(nextLineWords[2], nextLineWords[3], nextLineWords[4]);
                            nextLineMarried = false;
                        }
                        if (nextLineWords[1].equals("DIV")) {
                            nextLineDivorced = true;
                            divorced = true;
                        }
                        if (nextLineDivorced && nextLineWords[0].equals("2")) {
                            String date = nextLineWords[2] + " " + nextLineWords[3] + " " + nextLineWords[4];
                            tempFam.setDivorceDate(nextLineWords[2], nextLineWords[3], nextLineWords[4]);
                            tempFam.setDivorced(true);
                            nextLineDivorced = false;
                        }

                        count++;
                    }

//                    if(!divorced){
//                        tempFam.setDivorceDate("NA");
//                    }
//                    if(tempFam.getMarriageDate() == null){
//                        tempFam.setMarriageDate("NA");
//                    }
                    fams.add(tempFam);
                }
            }
        }
        System.out.println("Individuals");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%10s %28s %25s %20s %20s %20s %20s %20s %20s", "ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Collections.sort(Indi, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        for (Person in : Indi) {
            System.out.format("%10s %20s %20s %20s %20s %20s %20s %20s %20s",
                    in.getId(), in.getFirstName() + in.getLastName(), in.getSex(), simpleDateFormat.format(in.getBirthDate()), in.getAge(), !in.getDead(), (in.getDeathDate() == null ? "NA" : simpleDateFormat.format(in.getDeathDate())), in.getFamc(), in.getFams());
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Families");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        Collections.sort(fams, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        System.out.format("%10s %28s %25s %20s %20s %20s %20s %20s", "ID", "Married", "Divorced", "Husband ID", "Husband Name", "Wife ID", "Wife Name", "Children");
        System.out.println();
        for (Family family : fams) {
            System.out.format("%10s %20s %20s %20s %20s %20s %20s %20s",
                    family.getId(), (family.getMarriageDate() == null ? "NA" : simpleDateFormat.format(family.getMarriageDate())), family.getDivorced(), family.getHusbandId() == null ? "NA" : family.getHusbandId(), family.getHusbandFullName() == null ? "NA" : family.getHusbandFullName(), family.getWifeId() == null ? "NA" : family.getWifeId(), family.getWifeFullName() == null ? "NA" : family.getWifeFullName(), family.getChildrenIds() == null ? "NA" : family.getChildrenIds());
            System.out.println();
        }

        Sprint1 sprint1 = new Sprint1();
        sprint1.US01(Indi, fams);
        sprint1.US02(Indi, fams);
        sprint1.US03(Indi);
        sprint1.US04(fams);
        sprint1.US05(Indi, fams);
        sprint1.US06(Indi, fams);
        sprint1.US07(Indi);
        sprint1.US08(fams, Indi);

//        sprint 02 user stories
        Sprint2 sprint2 = new Sprint2();
        sprint2.US09(Indi, fams);
        sprint2.US10(Indi, fams);
        sprint2.US11(fams);
        sprint2.US12(Indi, fams);
        sprint2.US13(fams, Indi);
        sprint2.US14(fams,Indi);
        sprint2.US15(fams);
        sprint2.US16(fams, Indi);

        Sprint3 sprint3 = new Sprint3();

        sprint3.US21(Indi, fams);
        sprint3.US22(Indi, fams);
        sprint3.US23(Indi);
        sprint3.US24(fams);
        sprint3.US25(fams,Indi);
        ArrayList<Person> us29 = sprint3.US29(Indi);
        ArrayList<Person> us30 = sprint3.US30(Indi);
        ArrayList<Person> us31 = sprint3.US31(Indi);
        System.out.println("US29: List of People Deceased");
        System.out.printf("%10s %28s %25s %20s %20s %20s %20s %20s %20s", "ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
        for(Person in : us29)
        {
            System.out.format("%10s %20s %20s %20s %20s %20s %20s %20s %20s",
                    in.getId(), in.getFirstName() + in.getLastName(), in.getSex(), simpleDateFormat.format(in.getBirthDate()), in.getAge(), !in.getDead(), (in.getDeathDate() == null ? "NA" : simpleDateFormat.format(in.getDeathDate()) ),  in.getFamc(), in.getFams());
            System.out.println();
        }
        System.out.println("US30: Living people who are married.");
        System.out.printf("%10s %28s %25s %20s %20s %20s %20s %20s %20s", "ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
        for(Person in : us30)
        {
            System.out.format("%10s %20s %20s %20s %20s %20s %20s %20s %20s",
                    in.getId(), in.getFirstName() + in.getLastName(), in.getSex(), simpleDateFormat.format(in.getBirthDate()), in.getAge(), !in.getDead(), (in.getDeathDate() == null ? "NA" : simpleDateFormat.format(in.getDeathDate()) ),  in.getFamc(), in.getFams());
            System.out.println();
        }
        System.out.println("US31: All living people over 30 who have never been married.");
        System.out.printf("%10s %28s %25s %20s %20s %20s %20s %20s %20s", "ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
        for(Person in : us31)
        {
            System.out.format("%10s %20s %20s %20s %20s %20s %20s %20s %20s",
                    in.getId(), in.getFirstName() + in.getLastName(), in.getSex(), simpleDateFormat.format(in.getBirthDate()), in.getAge(), !in.getDead(), (in.getDeathDate() == null ? "NA" : simpleDateFormat.format(in.getDeathDate()) ),  in.getFamc(), in.getFams());
            System.out.println();
        }

        //sprint04 user stories
        Sprint4 sprint4 = new Sprint4();
        
        sprint4.US32(Indi, fams);
        sprint4.US33(Indi, fams);
        
        sprint4.US34(Indi, fams);
        sprint4.US35(Indi);

        ArrayList<Person> us36 = sprint4.US36(Indi);
        ArrayList<Person> us37 = sprint4.US37(Indi,fams);
        System.out.println("US36: People Deceased in Last 30 days.");
        System.out.printf("%10s %28s %25s %20s %20s %20s %20s %20s %20s", "ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
        for(Person in : us36)
        {
            System.out.format("%10s %20s %20s %20s %20s %20s %20s %20s %20s",
                    in.getId(), in.getFirstName() + in.getLastName(), in.getSex(), simpleDateFormat.format(in.getBirthDate()), in.getAge(), !in.getDead(), (in.getDeathDate() == null ? "NA" : simpleDateFormat.format(in.getDeathDate()) ),  in.getFamc(), in.getFams());
            System.out.println();
        }
        System.out.println("US37: Family Members of People Deceased in Last 30 days.");
        System.out.printf("%10s %28s %25s %20s %20s %20s %20s %20s %20s", "ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
        for(Person in : us37) {
            System.out.format("%10s %20s %20s %20s %20s %20s %20s %20s %20s",
                    in.getId(), in.getFirstName() + in.getLastName(), in.getSex(), simpleDateFormat.format(in.getBirthDate()), in.getAge(), !in.getDead(), (in.getDeathDate() == null ? "NA" : simpleDateFormat.format(in.getDeathDate())), in.getFamc(), in.getFams());
            System.out.println();
        }
        ArrayList<Person> US38 = sprint4.US38(Indi);
        System.out.println("US38: Living people whose birthday is in 30 days:");
        System.out.printf("%10s %28s %25s %20s %20s %20s %20s %20s %20s", "ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
        System.out.println();
        for(Person in : US38)

        {
            System.out.format("%10s %20s %20s %20s %20s %20s %20s %20s %20s",
                    in.getId(), in.getFirstName() + in.getLastName(), in.getSex(), simpleDateFormat.format(in.getBirthDate()), in.getAge(), !in.getDead(), (in.getDeathDate() == null ? "NA" : simpleDateFormat.format(in.getDeathDate()) ),  in.getFamc(), in.getFams());
            System.out.println();
        }


        ArrayList<Family> US39 = sprint4.US39(fams, Indi);
        System.out.println("US39: Living couple whose marriage anniversaries occur in the next 30 days:");
        System.out.printf("%20s %20s %20s", "Husband Name", "Wife Name", "anniversary date");
        System.out.println();
        for(Family fam : US39)
        {
            System.out.format("%20s %20s %20s",fam.getHusbandFullName(),fam.getWifeFullName(), simpleDateFormat.format(fam.getMarriageDate()));
            System.out.println();
        }

        if(testing) {
	        Sprint1Test test = new Sprint1Test();
	        test.testUS01();
	        test.testUS02();
	        test.testUS07();
	        test.testUS08();
	        test.testUS05();
	        test.testUS04();
	        //test.testUS03();
	        test.testUS06();


	        Sprint02Test sprint02Test = new Sprint02Test();
	        sprint02Test.testUS09();
	        sprint02Test.testUS10();
            sprint02Test.testUS11();
            sprint02Test.testUS12();
            sprint02Test.testUS13();
            sprint02Test.testUS14();
            sprint02Test.testUS15();
	        sprint02Test.testUS16();

	        Sprint03Test sprint03Test = new Sprint03Test();

	        sprint03Test.testUS21();
	        sprint03Test.testUS22();
	        sprint03Test.testUS23();
	        sprint03Test.testUS24();
	        sprint03Test.testUS30();
	        sprint03Test.testUS31();

	        Sprint04Test sprint04test = new Sprint04Test();
	        sprint04test.testUS32();
	        sprint04test.testUS33();
	        sprint04test.testUS34();
	        sprint04test.testUS35();
            sprint04test.testUS36();
            sprint04test.testUS37();
            sprint04test.testUS38();
            sprint04test.testUS39();
        }

    }
}