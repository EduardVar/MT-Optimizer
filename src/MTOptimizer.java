import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	
 */

public class MTOptimizer
{
	private static ArrayList<Subway> subways = new ArrayList<>();
	private static ArrayList<GoTrain> goTrains = new ArrayList<>();
	private static ArrayList<StreetCar> streetCars = new ArrayList<>();
	private static ArrayList<Bus> buses = new ArrayList<>();
	private static ArrayList<GoBus> goBuses = new ArrayList<>();
	
	private static ArrayList<Vehicle>[] vehicles = 
			(ArrayList<Vehicle>[])new ArrayList[]
			{subways, goTrains, streetCars, buses, goBuses};
	
	private static ArrayList<Person> people = new ArrayList<>();
	
	
	public static void readInFile(String pathname) throws IOException
	{
		File file = new File(pathname);
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line = "";
		
		while ((line = br.readLine()) != null)
		{
			String[] content = line.split(",");
			
			switch (pathname)
			{
			case "subways.txt":
				subways.add(new Subway(content[0], content[1], content[2],
						content[3], content[4], content[5]));
				break;
			case "gotrains.txt":
				goTrains.add(new GoTrain(content[0], content[1], content[2]));
				break;
			case "streetcars.txt":
				streetCars.add(new StreetCar(content[0], content[1], 
						content[2]));
				break;
			case "buses.txt":
				buses.add(new Bus(content[0], content[1], content[2]));
				break;
			case "gobuses.txt":
				goBuses.add(new GoBus(content[0], content[1], content[2]));
				break;
			case "ridership.txt":
				addRider(line);
				break;
			default:
				break;
			}
		}
		
		br.close();
	}
	
	public static void addRider(String line)
	{
		String[] content = line.split(",");
		
		try
		{
			String ID = content[0];
			char modality = content[1].charAt(0);
			char ageGroup = content[2].charAt(0);
			int hour = Integer.parseInt(content[3]);
			String date = content[4];
			
			if (!(ID.length() == 7 || ID.length() == 14 || ID.equals("*")))
			{
				System.out.println("ID ERROR");
				throw new Exception();
			}
			
			if (!(modality == 'S' || modality == 'G' || modality == 'X' || modality == 'C' || modality == 'D'))
			{
				System.out.println("MODALITY ERROR");
				throw new Exception();
			}
			
			if (!(ageGroup == 'C' || ageGroup == 'A' || ageGroup == 'S'))
			{
				System.out.println("AGE GROUP ERROR");
				throw new Exception();
			}
			
			
			if (hour < 1 || hour > 24)
			{
				System.out.println("HOUR ERROR");
				throw new Exception();
			}
			
			people.add(new Person(ID, modality, ageGroup, hour, date));
		}
		catch (NumberFormatException e) {
			System.out.println(e + " [CAUGHT]");
			//System.out.println(line);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e + " [CAUGHT]");
			//System.out.println(line);
		}
		catch (Exception e) {
			//System.out.println(line);
		}
		
		
	}
	
	public static void main(String[] args) throws IOException
	{
		String[] fileNames = {"subways.txt", "gotrains.txt", "streetcars.txt",
				"buses.txt", "gobuses.txt", "ridership.txt"};
		
		for (String name: fileNames)
			readInFile(name);
		
//		for (ArrayList<Vehicle> vehicleList : vehicles)
//			for (Vehicle vehicle : vehicleList)
//				System.out.println(vehicle);
	}
}
