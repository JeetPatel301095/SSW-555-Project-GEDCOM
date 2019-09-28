import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

class Project03
{
    public static void main(String args[]) throws Exception
    {
        // int f=0;
        // Person A[] = new Person();
        int i=0;

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
        for(int j=0;j<f;j++)
        {
            String inLine = gedComStrings.get(j);
            String[] splitTokens = inLine.split("\\s+");
            // System.out.println(splitTokens[0]);
            if(splitTokens[0].equals("0"))
            {
                if((splitTokens[1].equals("NOTE"))||(splitTokens[1].equals("HEAD"))||(splitTokens[1].equals("TRLR")))
                {
                    continue;
                }
                if(splitTokens[2].equals("INDI"))
                {
                    int y=0;
                    String[] Indidetails = new String[100];
                    Indidetails[y] = inLine;
                    y++;
                    String gline = gedComStrings.get(++j);
                    // System.out.println(gline);
                    String[] split = gline.split("\\s+");
                    while(!(split[0].equals("0")))
                    {
                        Indidetails[y]=gline;
                        y++;
                        gline = gedComStrings.get(++j);
                        split = gline.split("\\s+");

                    }
                    j--;
                    Indiparser a = new Indiparser();
                    Person temp=a.parseindi(Indidetails);
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
        for(int famI = 0; famI<f; famI++)
        {
            String currentLine = gedComStrings.get(famI);
            String words[] = currentLine.split(" ");
            if(words[0].equals("0"))
            {
                if(words[1].equals("HEAD") || words[1].equals("TRLR") || words[1].equals("NOTE"))
            {/* I do it like this to avoid an out of bounds error */} else if(words[2].equals("FAM"))
                {
                    Family tempFam = new Family();
                    tempFam.setId(words[1]);
                    int count = 1;
                    boolean nextLineMarried = false;
                    boolean nextLineDivorced = false;
                    boolean divorced = false;
                    while(gedComStrings.get(famI + count).charAt(0) == '1' || gedComStrings.get(famI + count).charAt(0) == '2')
                    {
                        String nextLineWords[] = gedComStrings.get(famI + count).split(" ");
                        if(nextLineWords[1].equals("HUSB"))
                        {
                            for(Person hubby : Indi)
                            {
                                if(hubby.getId().equals(nextLineWords[2]))
                                {
                                    tempFam.setDad(hubby);
                                }
                            }
                        }
                        if(nextLineWords[1].equals("WIFE"))
                        {
                            for(Person wifey : Indi)
                            {
                                if(wifey.getId().equals(nextLineWords[2]))
                                {
                                    tempFam.setMom(wifey);
                                }
                            }
                        }
                        if(nextLineWords[1].equals("CHIL"))
                        {
                            
                            tempFam.addChild(nextLineWords[2]);
                        }

                        if(nextLineWords[1].equals("MARR"))
                        {
                            nextLineMarried = true;
                        }
                        if(nextLineMarried && nextLineWords[0].equals("2"))
                        {
                            String date = nextLineWords[2] + " " +  nextLineWords[3] + " " + nextLineWords[4];
                            tempFam.setMarriageDate(nextLineWords[2], nextLineWords[3], nextLineWords[4]);
                            nextLineMarried = false;
                        }
                        if(nextLineWords[1].equals("DIV"))
                        {
                            nextLineDivorced = true;
                            divorced = true;
                        }
                        if(nextLineDivorced && nextLineWords[0].equals("2"))
                        {
                            String date = nextLineWords[2] + " " + nextLineWords[3] +  " " + nextLineWords[4];
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
        System.out.printf("%10s %20s %20s %20s %20s %20s %20s %20s %20s", "ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Collections.sort(Indi, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        for(Person in : Indi)
        {
            System.out.format("%10s %20s %20s %20s %20s %20s %20s %20s %20s",
                    in.getId(), in.getFirstName() + in.getLastName(), in.getSex(), simpleDateFormat.format(in.getBirthDate()), in.getAge(), !in.getDead(), (in.getDeathDate() == null ? "NA" : simpleDateFormat.format(in.getDeathDate()) ),  in.getFamc(), in.getFams());
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Families");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        Collections.sort(fams, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        System.out.format("%10s %20s %20s %20s %20s %20s %20s %20s", "ID", "Married", "Divorced", "Husband ID", "Husband Name", "Wife ID", "Wife Name", "Children");
        System.out.println();
        for(Family family : fams)
        {
            System.out.format("%10s %20s %20s %20s %20s %20s %20s %20s",
                    family.getId(), (family.getMarriageDate()==null ? "NA" : simpleDateFormat.format(family.getMarriageDate())) , family.getDivorced(), family.getHusbandId() == null ? "NA" : family.getHusbandId() , family.getHusbandFullName() == null?"NA":family.getHusbandFullName(), family.getWifeId()== null ? "NA" : family.getWifeId(), family.getWifeFullName()== null ? "NA" : family.getWifeFullName(), family.getChildrenIds()== null ? "NA" : family.getChildrenIds());
            System.out.println();
        }

        Sprint1 sprint1 = new Sprint1();
        sprint1.US07(Indi);
        sprint1.US08(fams, Indi);
        sprint1.US03(Indi);
        sprint1.US06(Indi, fams);

        // Sprint1Test test = new Sprint1Test();
        // test.testUS07();
        // test.testUS08();
        // test.testUS05();
    }
}   