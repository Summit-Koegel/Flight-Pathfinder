import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author charlie jungwirth
 * ONLY WORKS WITH THE Airports2.csv file
 * finds all flights on date 4/1/2005, puts them into .json file
 * fair warning there are some duplicates on csv
 */
public class csvTojson {
	private static int[] needed = new int[] {0,1,2,3,7};//necessary fields to include for flights
	
	/**
	 * converts inputed Airports2.csv into a .json file to be used
	 * to get flight data
	 * only includes data with date 4/1/2005 (I think this is the entire month)
	 * and ommits unused data
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String filep = "Airports2.csv";
		Scanner scan = new Scanner(new File(filep));
		String firstL = scan.nextLine(); 
		ArrayList<String> terms = new ArrayList<String>();
		
		//get terms/parameters for csv
		int commaInd = -1;
		do {
			firstL = firstL.substring(commaInd+1);
			commaInd = firstL.indexOf(',');
			if(commaInd == -1) {
				terms.add(firstL);
				break;
			}else {
				terms.add(firstL.substring(0, commaInd));
			}
			
		}while(commaInd !=-1);
		PrintWriter pw= new PrintWriter(new File("flights.json"));
		
		
		while(scan.hasNextLine()) {
			String[] current = elements(scan.nextLine(),terms.size());
			if(current[8].equals("2005-04-01")) {//only include this month
				pw.println(elemStr(current, terms));
			}
			
		}
		
		
		pw.close();
		
	}
	
	/**
	 * returns an array of all "elements" seperated by commas
	 * @param inpt
	 * @param size number of elements
	 * @return
	 */
	private static String[] elements(String inpt, int size) {
		String[] exOut = new String[size];
		int c = 0;
		int commaInd = -1;
		do {
			inpt = inpt.substring(commaInd+1);
			commaInd = inpt.indexOf(',');
			
			//need to check for comma in term
			while(inpt.indexOf((char)34)==0&&commaInd<inpt.indexOf((char)34,1)) {
				commaInd = inpt.indexOf(',',commaInd+1);//try next comma
			}
			if(commaInd == -1) {
				exOut[c] = inpt;
				break;
			}else {
				exOut[c] = (inpt.substring(0, commaInd));
			}
			c++;
			
		}while(commaInd !=-1);
		return exOut;
	}
	private static String elemStr(String[] contents,ArrayList<String> labels) {
		//.json value of individual element
		String exOut = "{\n";
		for(int i = 0; i< needed.length; i++) {
			//add fields specified by needed array
			exOut += "\t"+labels.get(needed[i])+":"+contents[needed[i]]+"\n";
		}
		exOut = exOut+"}\n";
		return exOut;
	}
}
