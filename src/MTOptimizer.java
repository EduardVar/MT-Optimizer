/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 15, 2019
 * Desc:	
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MTOptimizer
{
	/*
	 * WHAT TO FIX
	 * - MORE DESCRIPTIVE DESCRIPTIONS FOR EXCEPTIONS
	 * 		Ex: "missing value" or "looking for ... "
	 * - Need 10 errors (only have 8 rn)
	 * 		Convert Passenger ID and DATE to a int
	 */
	
	private static ArrayList<Subway> subways = new ArrayList<>();
	private static ArrayList<GoTrain> goTrains = new ArrayList<>();
	private static ArrayList<StreetCar> streetCars = new ArrayList<>();
	private static ArrayList<Bus> buses = new ArrayList<>();
	private static ArrayList<GoBus> goBuses = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	//All vehicle types inherit from Vehicle class so array of Vehicle objects
	private static ArrayList<Vehicle>[] vehicles = 
			(ArrayList<Vehicle>[])new ArrayList[]
			{subways, goTrains, streetCars, buses, goBuses};
	
	private static ArrayList<Passenger> passengers = new ArrayList<>();
	
	public static void readInFile(String pathname) throws IOException
	{
		File file = new File(pathname);
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line = "";	
		String errorContent = "";
		
		while ((line = br.readLine()) != null)
		{
			//System.out.println(line);
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
				errorContent = addRider(line, errorContent);
				break;
			}
		}
		
		br.close();
		
		writeToFile("errorlog.txt", errorContent);
	}
	
	public static String addRider(String line, String errorContent)
	{
		String[] content = line.split(",");
		
		try
		{
			if (content.length != 5)
				throw new InvalidFieldNumberException(line);
			
			passengers.add(new Passenger(content[0], content[1], content[2],
					content[3], content[4]));
		}
		catch (NumberFormatException | ArrayIndexOutOfBoundsException |
				IDFormatException | AgeFormatException | 
				ModalityFormatException | HourOutOfRangeException | 
				InvalidDateException | InvalidFieldNumberException e)
		{
			if (!(errorContent.equals("")))				
				errorContent += "\r\n";
			
			errorContent +=  e + "\r\nOn line: " + line + "\r\n";
		}
		
		return errorContent;
	}
	
	public static void getInOperations() throws IOException
	{
		Map<Integer, Map<Character, Float>>allInfo = new HashMap<>();	
		initialSet(allInfo);
		
		for (Passenger passenger : passengers)
			passenger.addToInfo(allInfo);
		
		Map<Integer, Map<Character, ArrayList<Vehicle>>> todayFleet = getFleets(allInfo);

		writeToFile("InOperationFleets.txt", formatOperations(todayFleet));
	}
	
	public static void initialSet(Map<Integer, Map<Character, Float>>allInfo)
	{
		for (int hour = 1; hour < 25; hour++)
		{
			Map<Character, Float> inner = new HashMap<>();
			inner.put('S', 0f);
			inner.put('G', 0f);
			inner.put('X', 0f);
			inner.put('C', 0f);
			inner.put('D', 0f);
			
			allInfo.put(hour, inner);
		}
	}
	
	public static Map<Integer, Map<Character, ArrayList<Vehicle>>> getFleets(Map<Integer, Map<Character, Float>>allInfo)
	{
		Map<Character, ArrayList<Vehicle>> vehicleDic = new HashMap<>();
		vehicleDic.put('S', vehicles[0]);
		vehicleDic.put('G', vehicles[1]);
		vehicleDic.put('X', vehicles[2]);
		vehicleDic.put('C', vehicles[3]);
		vehicleDic.put('D', vehicles[4]);
		
		Map<Integer, Map<Character, ArrayList<Vehicle>>> fleetInfo = new HashMap<>();
		
		for (int hour = 1; hour < 25; hour++)
		{
			Map<Character, Float> allInner = allInfo.get(hour);
			
			Map<Character, ArrayList<Vehicle>> fleetInner = new HashMap<>();
			
			for (char key : allInner.keySet())
			{
				ArrayList<Vehicle> allAvailable = vehicleDic.get(key);
				
				Vehicle sample = allAvailable.get(0);
				float toFill = allInner.get(key);
				
				fleetInner.put(key, sample.assembleFleet(allAvailable, toFill));
			}
			
			fleetInfo.put(hour, fleetInner);
		}
		
		return fleetInfo;
	}
	
	public static String formatOperations(Map<Integer, Map<Character, ArrayList<Vehicle>>> todayFleet)
	{
		String content = "";
		String[] sections = {"[Trains]", "[GoTrains]", "[Streetcars]", "[Buses]", "[GoBuses]"};
		
		Map<String, Character> indexDic = new HashMap<>();
		indexDic.put(sections[0], 'S');
		indexDic.put(sections[1], 'G');
		indexDic.put(sections[2], 'X');
		indexDic.put(sections[3], 'C');
		indexDic.put(sections[4], 'D');
		
		for (String section : sections)
		{
			content += section + "\r\n";
			
			for (int hour = 1; hour < 25; hour++)
			{
				content += String.format("[Hour = %1$02d]\r\n", hour);
				
				Map<Character, ArrayList<Vehicle>> hourInner = todayFleet.get(hour);
				ArrayList<Vehicle> sectionFleet = hourInner.get(indexDic.get(section));
				
				for (Vehicle vehicle : sectionFleet)
					content += vehicle.toString() + "\r\n";
				
				content += String.format("[Count = %1$02d]", sectionFleet.size());
				
				if (!(hour == 24 && section.equals(sections[4])))
					content += "\r\n";
			}
			
			if (!(section.equals(sections[4])))
				content += "\r\n";
		}
		
		return content;
	}
	
	/**
	 * This function is a general function to writing to a file
	 * @param fileName is a String for the name of the file being written to
	 * @param content is a String for what is to be written to the file
	 * @throws IOException in case file wasn't found
	 */
	public static void writeToFile(String fileName, String content) 
			throws IOException
	{
		//Creates a new FileOutputSteam object using fileName given
		FileOutputStream outFile = new FileOutputStream(fileName);
		
		//Creates a new OutputStreamWriter object in utf-8 using outFile
		OutputStreamWriter outStream = new OutputStreamWriter(outFile, 
				"utf-8");
		
		//Makes a new BufferedWrite object called writer using outStream
		BufferedWriter writer = new BufferedWriter(outStream);
		
		//Writes to writer with the content given as a parameter
		writer.write(content);
		
		//Closes writer to not use any extra resources
		writer.close();
	}
	
	public static void main(String[] args) throws IOException
	{
		String[] fileNames = {"subways.txt", "gotrains.txt", "streetcars.txt",
				"buses.txt", "gobuses.txt", "ridership.txt"};
		
		for (String name: fileNames)
			readInFile(name);
		
		getInOperations();
	}
}
