import java.util.*;
import java.io.*;

class Project03
{
    public static void main(String args[]) throws Exception
    {
        // int f=0;
        // Person A[] = new Person();
        int i=0;
        int y=0;
        ArrayList<Person> Indi = new ArrayList<Person>();
        String[] Indidetails = new String[15];
        FileReader fileReader = new FileReader("proj02test.ged");
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
                            tempFam.setMarriageDate(date);
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
                            tempFam.setDivorceDate(date);
                            nextLineDivorced = false;
                        }

                        count++;
                    }
                    if(!divorced){
                        tempFam.setDivorceDate("NA");
                    }
                    fams.add(tempFam);
                }
            }
        }
        /*
        System.out.println("testing my stuff");
        System.out.println(fams.get(0).getHusbandId());
        //System.out.println(fams.get(0).getWifeId()); THIS LINE DOESN'T WORK FOR TEST because wife was never declared as an INDI
        System.out.println(fams.get(0).getMarriageDate());
        System.out.println(fams.get(0).getDivorceDate());

        */
        System.out.println("-----------------");
        for(Person in : Indi)
        {
            System.out.println(in.getId());
        }
        // for(String line : gedComStrings)
        // {
        //     i++;
            // String inLine = line;
            // String[] splitTokens = inLine.split("\\s+"); 
            // System.out.println(splitTokens[0]);
            // if(splitTokens[])
        // }
        
    }
}   