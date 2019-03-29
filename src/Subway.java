import java.util.ArrayList;

/**
 * Author:	Eduard Varshavsky
 * Date:	March 27, 2019
 * Desc:	Class inherited from Vehicle to store Subway related properties
 */

public final class Subway extends Vehicle
{
	// *** CLASS VARIABLES ***
	
	//Integers to store number of cars per train and number of people in each
	private int numOfCars;
	private int passPerCar;
	
	//Character to store the operational status of the subway
	private char operationalStatus;
	
	//String that stores the operational date of the subway
	private String operationalDate;
	
	// *** CONSTRUCTOR METHODS ***
	
	/**
	 * Constructor for initializing an instance of a subway object
	 * @param unitNumber String representing Subway object's unit number
	 * @param idNumber String representing Subway's identification number
	 * @param numOfCars String representing the number of cars on this Subway
	 * @param passPerCar String representing the number of passengers per car
	 * @param operationalStatus String meant to be stored as char for status
	 * @param operationalDate String representing Subway's operational date
	 */
	public Subway(String unitNumber, String idNumber,
			String numOfCars, String passPerCar, String operationalStatus,
			String operationalDate)
	{
		//Sets parent class variables using constructor from Vehicle,
		//calculates capacity first within the call using given parameters
		super(unitNumber, idNumber, calcCapacity(numOfCars, passPerCar));
		
		//Parses numOfCars and passPerCar into classes attributes as integers
		this.numOfCars = Integer.parseInt(numOfCars);
		this.passPerCar = Integer.parseInt(passPerCar);
		
		//Sets operationalStatus to the given parameter as a character
		this.operationalStatus = operationalStatus.charAt(0);
		
		//Sets operationalDate to the parameter given 
		this.operationalDate = operationalDate;
	}
	
	// *** OBJECT-BEHAVIOUR METHODS ***
	
	/**
	 * Function used to calculate capacity of Subway before sending it to super
	 * @param numCars is a String mean to represent number of cars Subway has
	 * @param perCar is a String mean to represent passengers per car on Subway
	 * @return the product of two parameters to represent total capacity
	 */
	private static String calcCapacity(String numCars, String perCar)
	{
		//Calculates the product by parsing both parameters as integers
		return (Integer.parseInt(numCars) * Integer.parseInt(perCar)) + "";
	}
	
	/**
	 * Main function to be called when assembling a fleet for hourly service
	 * @param availableVehicles list of Vehicles that stores all the Subways
	 * @param toFill is float representing capacity left to fill
	 * @return a list of Vehicle objects of the fleet to serve current hour
	 */
	public ArrayList<Vehicle> assembleFleet(
			ArrayList<Vehicle> availableVehicles, float toFill)
	{
		//New List of Subway objects mean to store all possible Subways
		ArrayList<Subway> allSubways = new ArrayList<>();
		
		//Iterates through every single possible subway 
		for (Vehicle vehicle : availableVehicles)
			//Adds it to list of subways by casting the vehicle to type subway
			allSubways.add((Subway)vehicle);
		
		//Returns a fleet using the parent class's assembleFlee function.
		//Within the call,filters the subways using getAvailable
		return super.assembleFleet(getAvailable(allSubways), toFill);
	}
	
	/**
	 * Function used to filter out Subways to get list of only available ones
	 * @param allSubways is a list of Subway objects of all Subways given
	 * @return a list of Vehicle objects containing available Subways
	 */
	public static ArrayList<Vehicle> getAvailable(ArrayList<Subway> allSubways)
	{
		//Creates a new list of Vehicle objects to store only available subways
		ArrayList<Vehicle> available = new ArrayList<>();
		
		//Goes through every single possible subway 
		for (Subway subway : allSubways)
			//Checks of its operational status is 'A' for available
			if (subway.getOperationalStatus() == 'A')
				//Add the subway to the available list
				available.add(subway);
		
		//Returns the list of available Subway objects in a Vehicle list
		return available;
	}
	
	// *** MUTATOR METHODS ***
	
	// *** ACCESSOR METHODS ***

	/**
	 * Overridden method used to get a string representation of a Subway object
	 * @return a String in the same form as the input lines given in input file
	 */
	public String toString()
	{
		//toReturn String containing all the information of the Subway objects
		String toReturn = this.getClass() + ": " + this.getUnitNumber() + ","
				+ this.getIdNumber() + "," + numOfCars + "," + passPerCar + ","
				+ operationalStatus + "," + operationalDate;
		
		//Returns toString from the 6th char onwards to not contain "class" at
		//the beginning of the String
		return toReturn.substring(6);
	}

	/**
	 * Getter used to access the operationalStatus attribute of the Subway
	 * @return String of the operationalStatus the Subway is in
	 */
	public char getOperationalStatus()
	{
		return operationalStatus;
	}	
}
