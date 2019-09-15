import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class Project02 {

	public static void main(String[] args) {
		
		HashMap<String, String> tags = new HashMap<>();
		tags.put("HEAD", "0");
		tags.put("TRLR", "0");
		tags.put("NOTE", "0");
		tags.put("NAME", "1");
		tags.put("SEX", "1");
		tags.put("BIRT", "1");
		tags.put("DEAT", "1");
		tags.put("FAMC", "1");
		tags.put("FAMS", "1");
		tags.put("MARR", "1");
		tags.put("HUSB", "1");
		tags.put("WIFE", "1");
		tags.put("CHIL", "1");
		tags.put("DIV", "1");
		tags.put("DATE", "2");
		char valid;
		String line, output;
		String[] tokens;
		
		try {
			File file = new File(args[0]);
			Scanner sc = new Scanner(file);
			
			while(sc.hasNextLine()) {
				
				valid = 'N';
				output = "";
				line = sc.nextLine();
				System.out.println("--> " + line);
				tokens = line.split(" ", 3);
				
				if(tags.containsKey(tokens[1])) {
					if(tokens[0].equals(tags.get(tokens[1]))) {
						valid = 'Y';
					}
				}
				else if(tokens.length > 2) {
					if(tokens[2].equals("INDI") || tokens[2].equals("FAM")) {
						if(tokens[0].equals("0")) {
							valid = 'Y';
						}
						output = "<-- " + tokens[0] + "|" + tokens[2] + "|" + valid + "|" + tokens[1]; 
					}
				}
				
				if(output.isEmpty()) {
					try {
						output = "<-- " + tokens[0] + "|" + tokens[1] + "|" + valid + "|" + tokens[2];
					}
					catch(ArrayIndexOutOfBoundsException e) {
						output = "<-- " + tokens[0] + "|" + tokens[1] + "|" + valid + "|";
					}
				}
				
				System.out.println(output);
			}
			
			sc.close();
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}