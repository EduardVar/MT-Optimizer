/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MTOptimizer
{
	private static ArrayList<Subway> subways = new ArrayList<>();
	private static ArrayList<GoTrain> goTrains = new ArrayList<>();
	private static ArrayList<StreetCar> streetCars = new ArrayList<>();
	private static ArrayList<Bus> buses = new ArrayList<>();
	private static ArrayList<GoBus> goBuses = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
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
			}
			
			count++;
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
				throw new IDFormatException(content[0]);
			
			if ((content[1].length() != 1) || !(modality == 'S' ||
					modality == 'G' || modality == 'X' ||
					modality == 'C' || modality == 'D'))
				throw new ModalityFormatException(content[1]);
			
			if ((content[2].length() != 1) || !(ageGroup == 'C' ||
					ageGroup == 'A' || ageGroup == 'S'))
				throw new AgeFormatException(content[2]);
						
			if (hour < 1 || hour > 24)
				throw new HourOutOfRangeException(content[3]);
			
			//CHECK IF DATE WORKS
			
			people.add(new Person(ID, modality, ageGroup, hour, date));
		}
		catch (NumberFormatException | ArrayIndexOutOfBoundsException |
				IDFormatException | AgeFormatException | 
				ModalityFormatException | HourOutOfRangeException e) 
		{
			System.out.println(e + " [CAUGHT]");
			System.out.println(line);
		}
		catch (Exception e) {
			System.out.println(line);
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
