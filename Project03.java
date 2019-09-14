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
        String[] Indidetails = new String[15];
        FileReader fileReader = new FileReader("C:\\Users\\Jeet\\Jeet\\SW-555\\SSW-555-Project-GEDCOM\\proj02test.ged");
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
                }
            }
        }
        for(int g = 0; g<y;g++)
        {
            System.out.println(Indidetails[g]);
        }
        System.out.println("-----------------");
        Indiparser a = new Indiparser();
        a.parseindi(Indidetails);
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