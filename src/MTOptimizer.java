/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 28, 2019
 * Desc:	Main class of the project. Contains general attributes and logic to
 * 			run MT optimization and maintain attributes
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
	//Creates a specific list for each vehicles sub-type in the program
	private static ArrayList<Subway> subways = new ArrayList<>();
	private static ArrayList<GoTrain> goTrains = new ArrayList<>();
	private static ArrayList<StreetCar> streetCars = new ArrayList<>();
	private static ArrayList<Bus> buses = new ArrayList<>();
	private static ArrayList<GoBus> goBuses = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	//Creates an array of lists of each vehicle sub-types. Casts the lists as a
	//list of Vehicle objects since each sub-type inherits from Vehicle class
	private static ArrayList<Vehicle>[] vehicles = 
			(ArrayList<Vehicle>[])new ArrayList[]
			{subways, goTrains, streetCars, buses, goBuses};
	
	//List of Passenger objects to store valid riders
	private static ArrayList<Passenger> passengers = new ArrayList<>();
	
	//Global counter and String to store current rider line and number in file
	private static int riderCount = 0;
	private static String line = "";
	
	/**
	 * Function used to read in data from any file and sort them into their
	 * specific Vehicle sub-type list based on the file name
	 * @param pathname is String for the filename to be read from
	 * @throws IOException in case an error occurs while reading the file
	 */
	public static void readInFile(String pathname) throws IOException
	{
		//Creates a new File object with the pathname as the parameter
		File file = new File(pathname);
		
		//Creates a new BufferedReader object using the file object
		BufferedReader br = new BufferedReader(new FileReader(file));

		//Creates a new String to store the errors occurred during reading
		String errorContent = "";
		
		//Keeps looping until the line read from br is null
		while ((line = br.readLine()) != null)
		{
			//An array of Strings created to store elements of line separated
			//by commas. Based on the input fields all Vehicle files share
			String[] content = line.split(",");
			
			//Switch statement to identify what file name pathname is
			switch (pathname)
			{
			case "subways.txt":
				//Creates a new Subway object from file and adds to subways
				subways.add(new Subway(content[0], content[1], content[2],
						content[3], content[4], content[5]));
				break;
			case "gotrains.txt":
				//Creates a new GoTrain object from file and adds to goTrains
				goTrains.add(new GoTrain(content[0], content[1], content[2]));
				break;
			case "streetcars.txt":
				//Creates a new StreetCar object from file, adds to streetCars
				streetCars.add(new StreetCar(content[0], content[1], 
						content[2]));
				break;
			case "buses.txt":
				//Creates a new Bus object from file and adds to buses
				buses.add(new Bus(content[0], content[1], content[2]));
				break;
			case "gobuses.txt":
				//Creates a new GoBus object from file and adds to goBuses
				goBuses.add(new GoBus(content[0], content[1], content[2]));
				break;
			case "ridership.txt":
				//Increments riderCount to count the current line in the file
				riderCount++;
				
				//Sets error content to the return of addRider function
				errorContent = addRider(line, errorContent);
				break;
			}
		}
		
		//Closes the file being rid since operations with it are complete
		br.close();

		//Writes the errorContent to errorlog.txt using wrtieToFile function
		writeToFile("errorlog.txt", errorContent);
	}
	
	/**
	 * Function used for adding to riders list and handling any issues with it
	 * @param line is a String of the line read in
	 * @param errorContent is the current String on the errors in the read file
	 * @return errorContent, updated if there are errors or as it was if not
	 */
	public static String addRider(String line, String errorContent)
	{
		//Array of Strings created for elements of line separated by commas
		String[] content = line.split(",");
		
		//Attempt to run code in block capable of catching parameter exceptions
		try
		{
			//Check if content contains exactly 5 parameters, if it doens't...
			if (content.length != 5)
				//... throws InvalidParameterNumberException 
				throw new InvalidParameterNumberException(line);
			
			//Creates a new passenger with parameters in content
			Passenger toAdd = new Passenger(content[0], content[1], content[2],
					content[3], content[4]);
			
			//Stores toAdd Passenger's errorLog in specificErrors String
			String specificErrors = toAdd.getErrorLog();
			
			//Checks if specificErrors is empty (no errors)
			if ((specificErrors.equals("")))
				//toAdd is a valid Passenger object and is added to passengers
				passengers.add(toAdd);
			else
			{
				//Checks if overall errorContent is not empty
				if (!(errorContent.equals("")))			
					//Adds line to space out new error being added
					errorContent += "\r\n";
				
				//Adds Passenger's specificErrors to program's errorContent
				errorContent +=  specificErrors + "\r\n";
			}
		}
		catch (InvalidParameterNumberException e)
		{
			//Checks if overall errorContent is not empty
			if (!(errorContent.equals("")))	
				//Adds line to space out new error being added
				errorContent += "\r\n";
			
			//Adds to error content the error message and the line it occurred
			errorContent +=  e + "\r\nOn line " + riderCount + ": \"" + line
					+ "\"\r\n";
		}
		
		//Returns the current rendition of errorContent
		return errorContent;
	}
	
	/**
	 * Driver function to achieve goal of getting in operations vehicles
	 * @throws IOException if file functions fail or cannot be completed
	 */
	public static void getInOperations() throws IOException
	{
		//Creates a map to store the info of all the riders each hour and
		//within each hour, the modality as a char and the to fill as a float
		Map<Integer, Map<Character, Float>>allInfo = new HashMap<>();	
		
		//Sets the initial structure for allInfo using initialSet function
		initialSet(allInfo);
		
		//Iterates through each rider in passengers
		for (Passenger passenger : passengers)
			//Adds the passenger to allInfo using instant function addToInfo
			passenger.addToInfo(allInfo);
		
		//Creates a map to store fleet info for each hour in the day and within
		//each hour, the modality as a char and the amount to fill as a float
		Map<Integer, Map<Character, ArrayList<Vehicle>>> todayFleet;
		todayFleet = getFleets(allInfo);

		//Write to InOpterationFleets.txt the todayFleet info, but formatted
		writeToFile("InOperationFleets.txt", formatOperations(todayFleet));
	}
	
	/**
	 * Function used to set the overall structure to work with allInfo
	 * @param allInfo is map containing rider statistics to fill for modality
	 */
	public static void initialSet(Map<Integer, Map<Character, Float>>allInfo)
	{
		//Iterates through each hour in the day
		for (int hour = 1; hour < 25; hour++)
		{
			//Creates an inner map for each modality toFill within this hour
			Map<Character, Float> inner = new HashMap<>();
			
			//Adds empty modality pair for each modality type
			inner.put('S', 0f);
			inner.put('G', 0f);
			inner.put('X', 0f);
			inner.put('C', 0f);
			inner.put('D', 0f);
			
			//Adds the inner map into allInfo for that specific hour
			allInfo.put(hour, inner);
		}
	}
	
	/**
	 * Function used to get the optimized fleet lists for each hour and store
	 *  it in a map sorted with a key of its modality type
	 * @param allInfo is map containing rider statistics to fill for modality
	 * @return a map similar to allInfo but with a list of vehicles (optimized
	 * fleet) instead of the float for each modality
	 */
	public static Map<Integer, Map<Character, ArrayList<Vehicle>>> getFleets(
			Map<Integer, Map<Character, Float>>allInfo)
	{
		//Creates a map that stores each overall vehicle list as a value of the
		//character representing its modality
		Map<Character, ArrayList<Vehicle>> vehicleDic = new HashMap<>();
		
		//Adds each vehicle list under its associated modality character key
		vehicleDic.put('S', vehicles[0]);
		vehicleDic.put('G', vehicles[1]);
		vehicleDic.put('X', vehicles[2]);
		vehicleDic.put('C', vehicles[3]);
		vehicleDic.put('D', vehicles[4]);
		
		//Creates a new map that Stores each map of modality, fleet list pair
		//within a map tracking the hour it is 
		Map<Integer, Map<Character, ArrayList<Vehicle>>> fleetInfo; 
		fleetInfo = new HashMap<>();
		
		//Iterates through each hour in the day
		for (int hour = 1; hour < 25; hour++)
		{
			//Stores inner rider info from allInfo using the hour as a key
			Map<Character, Float> allInner = allInfo.get(hour);
			
			//Creates a new inner map for fleetInfo to store optimized fleets
			Map<Character, ArrayList<Vehicle>> fleetInner = new HashMap<>();
			
			//Goes through each character key of modality in allInner
			for (char key : allInner.keySet())
			{
				//Stores specific available vehicles under optimization from
				//vehicleDic using the key of allInner
				ArrayList<Vehicle> allAvailable = vehicleDic.get(key);
				
				//Stores a sample vehicle from the available vehicles
				Vehicle sample = allAvailable.get(0);
				
				//Stores how much the vehicles need to fill from allInner map
				float toFill = allInner.get(key);
				
				//Sets optimized fleet using all the data taken from iteration
				fleetInner.put(key, sample.assembleFleet(
						allAvailable, toFill));
			}
			
			//Places fleetInner into its associated hour of fleetInfo
			fleetInfo.put(hour, fleetInner);
		}
		
		//Returns the complete fleetInfo
		return fleetInfo;
	}
	
	/**
	 * Function used to create a formated output using the data from today's
	 * fleet map
	 * @param todayFleet is the fleet of optimized vehicles serving riders
	 * @return a String of the formatted data of todayFleet
	 */
	public static String formatOperations(Map<Integer, Map<Character,
			ArrayList<Vehicle>>> todayFleet)
	{
		//Stores formatted content as a string
		String content = "";
		
		//Stores each section of the formatted output in an array of Strings
		String[] sections = {"[Trains]", "[GoTrains]", "[Streetcars]",
				"[Buses]", "[GoBuses]"};
		
		//Map that has section key with associated modality character
		Map<String, Character> indexDic = new HashMap<>();
		
		//Sets each key vlaue pair of indexDic
		indexDic.put(sections[0], 'S');
		indexDic.put(sections[1], 'G');
		indexDic.put(sections[2], 'X');
		indexDic.put(sections[3], 'C');
		indexDic.put(sections[4], 'D');
		
		//Iterates through each section in sections
		for (String section : sections)
		{
			//Adds to content the section header
			content += section + "\r\n";
			
			//Iterates through every hour of the day 
			for (int hour = 1; hour < 25; hour++)
			{
				//Formats the hour heading and adds it to content
				content += String.format("[Hour = %1$02d]\r\n", hour);
				
				//Stores the fleet of map of fleets for the hour
				Map<Character, ArrayList<Vehicle>> hourInner;
				hourInner = todayFleet.get(hour);
				
				//Stores the fleet list of vehicles for the section
				ArrayList<Vehicle> sectionFleet;
				sectionFleet = hourInner.get(indexDic.get(section));
				
				//Iterates through each vehicle in the section's fleet
				for (Vehicle vehicle : sectionFleet)
					//Adds the vehicle data to the content
					content += vehicle.toString() + "\r\n";
				
				//Formats the count heading and adds it to the content
				content += String.format("[Count = %1$02d]", sectionFleet.size());
				
				//Checks if function is at the last hour of the last section
				if (!(hour == 24 && section.equals(sections[4])))
					//If not, adds a new line to format data for next hour
					content += "\r\n";
			}
			
			//Checks if function is at the last hour of the last section
			if (!(section.equals(sections[4])))
				//If not, adds a new line to format data for next section
				content += "\r\n";
		}
		
		//Returns the fully formatted content
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
	
	/**
	 * Getter used to access the riderCount attribute
	 * @return integer of the riderCount in main class
	 */
	public static int getRiderCount()
	{
		return riderCount;
	}

	/**
	 * Getter used to access the line attribute
	 * @return String of the current line in main class
	 */
	public static String getLine()
	{
		return line;
	}

	/**
	 * Main driver class of the program. Executes functions to complete goals
	 * @throws IOException due to readInFile potentially throwing IOException
	 */
	public static void main(String[] args) throws IOException
	{
		//Stores the file names of project files in String array
		String[] fileNames = {"subways.txt", "gotrains.txt", "streetcars.txt",
				"buses.txt", "gobuses.txt", "ridership.txt"};
		
		//Goes through each file name in fileNames
		for (String name: fileNames)
			//Reads the file in using name
			readInFile(name);
		
		//Runs the main function to getInOperationVehicles
		getInOperations();
	}
}
