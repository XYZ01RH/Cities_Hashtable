import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author rileyZ
 *
 */
public class cmsc401 {
	
	
	/**
	 * @param args
	 * Accepts cities.csv file and stores each city name as a string for Key in HashMap
	 * Stores City name latitude and longitude in a Cities Object as the Value in HashMap
	 */
	public static void main(String[] args) throws ParseException, IOException, FileNotFoundException, NullPointerException {
		
		RH_HashMap<String, Cities> map = new RH_HashMap<String, Cities>();
		String filepath = null;
		String line = null;
		String regex = ",";
		
		/**
		 *  Read csv file and initiate City objects for each entry in file
		 *  Put each entry into the HashMap
		 */
		if( args[0] != null ) {
			filepath = args[0];
			BufferedReader br;
			try {
				br = new BufferedReader( new FileReader(filepath));
				while( (line = br.readLine()) != null ) {
					String[] str = line.split(regex);
					Cities c = new Cities();
					c.setCity(str[0]);
					c.setLatitude(Double.parseDouble(str[1]));
					c.setLongitude(Double.parseDouble(str[2]));
					map.put(c.getCity(), c);
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch(NullPointerException e) {
				e.printStackTrace();
			}
		} 
		
		Scanner in = new Scanner(System.in);
		
		/**
		 * separates input and outputs based on the commands
		 * "retrieve Some City" - coordinates of that city
		 * "distance From Ciy, To City" - distance in km based on the two cities coordinates
		 *  "stop" - outputs average number of collisions in each set / bucket then terminates program
		 */
		try {
			while( in.hasNextLine() ) {
				String ln = in.nextLine();
				String keyword = "";
				String c1 = "";
				String c2 = "";
				Cities first;
				Cities second;
				Double dist;
				Double buckets;
				
				if( ln.equals("stop") ) {
					buckets = map.getAvgSize();
					System.out.println(buckets);
					in.close();
					System.exit(0);
				} else {
					
					if( keyword != "stop") {
						keyword = ln.substring(0, 8);
						ln = ln.substring(9);
						if(ln.contains(regex)) {
							String words[] = ln.split(",");
							c1 = words[0];
							c2 = words[1].trim();
						} else {
							c1 = ln;
							System.out.println(c1);
						} 
					} else { continue; }				
				} 
				
				switch(keyword) {
				case "distance" :
					if( (map.getVal(c1) != null) && (map.getVal(c2) != null) ) {
						first = map.getVal(c1);
						second = map.getVal(c2);
						dist = first.getDistance(second);
						System.out.println(dist);
					}
					break;
				case "retrieve" :
					if( (map.getVal(c1)) != null) {
						first = map.getVal(c1);
						System.out.println(first.toString());
					}
					break;

				default :
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} in.close();
	}

	

}
