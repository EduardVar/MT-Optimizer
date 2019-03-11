/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 11, 2019
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

public class MTOptimizer
{
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
			passengers.add(new Passenger(content[0], content[1], content[2],
					content[3], content[4]));
		}
		catch (NumberFormatException | ArrayIndexOutOfBoundsException |
				IDFormatException | AgeFormatException | 
				ModalityFormatException | HourOutOfRangeException | 
				InvalidDateException e)
		{
			if (!(errorContent.equals("")))				
				errorContent += "\r\n";
			
			errorContent +=  e + "\r\nOn line: " + line + "\r\n";
		}
		
		return errorContent;
	}
	
	//Fix this up or just replace it lol
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
		
		System.out.println(vehicles); //del later
		
		
//		for (ArrayList<Vehicle> vehicleList : vehicles)
//			for (Vehicle vehicle : vehicleList)
//				System.out.println(vehicle);
	}
}
