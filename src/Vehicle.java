/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 19, 2019
 * Desc:	This is an abstract class for Vehicle sub-types to inherit from.
 * 			Contains general attributes and behaviors used by any vehicle.
 */

import java.util.ArrayList;
import java.util.Random;

public abstract class Vehicle
{
	//Creates a Random object called random with a default seed
	private static Random random = new Random();
	
	//Stores the unit number of the Vehicle object as an integer
	private int unitNumber;
	
	//Stores the identification number of the Vehicle object as a String
	private String idNumber;
	
	//Stores the capacity of the Vehicle object as an integer
	private int capacity;
	
	/**
	 * Constructor used to initialize a new Vehicle object
	 * @param unitNumber as a String object for the Vehicle's unit number
	 * @param idNumber as a String object for the Vehicle's ID number
	 * @param capacity as a String object for the Vehicle's carrying capacity
	 */
	protected Vehicle(String unitNumber, String idNumber, String capacity)
	{
		//Sets Vehicle object's unitNumber to the parsed String of same name
		this.unitNumber = Integer.parseInt(unitNumber);
		
		//Sets Vehicle object's idNumber to the String given with same name
		this.idNumber = idNumber;
		
		//Sets Vehicle object's capacity to the parsed String of same name
		this.capacity = Integer.parseInt(capacity);
	}
	
	/**
	 * Function used by an instance of Vehicle object to assemble an optimized
	 * fleet of Vehicle objects (used to serve hourly passengers)
	 * @param availableVehicles is ArrayList of available Vehicle objects
	 * @param toFill is a float that represents the capacity required to fill
	 * @return ArrayList of Vehicles with an optimized specific fleet for hour
	 */
	public ArrayList<Vehicle> assembleFleet(
			ArrayList<Vehicle> availableVehicles, float toFill)
	{
		//Stores a float for the space left to fill by the vehicles
		float leftToFill = toFill;
		
		//Makes a new list to store available vehicles without overriding the
		//original array when manipulating for optimization
		ArrayList<Vehicle> tempAvailable = new ArrayList<>();
		
		//Makes a deep copy of the availableVehicles List
		for (Vehicle vehicle : availableVehicles)
			tempAvailable.add(vehicle);
		
		//New list to store the optimized Vehicle objects to serve current hour
		ArrayList<Vehicle> fleet = new ArrayList<>();
		
		//Keeps looping until leftToFill is "filled" --> THIS IS THE ALGORITHM
		while (leftToFill > 0)
		{
			//Store best Vehicle object for the current loop
			Vehicle best = tempAvailable.get(0);
			
			//Iterates through each available vehicle left to optimize better
			for (Vehicle curr : tempAvailable)
			{
				//Stores the capacity of the best and current Vehicle Objects
				float bestSize = best.getCapacity();
				float currSize = curr.getCapacity();
				
				//Checks if the current Vehicle stores less than the best but
				//still exceeds toFill (this is to save on bigger vehicles
				if (currSize >= toFill && currSize < bestSize)
					//Sets the best Vehicle to the current Vehicle
					best = curr;
				else if (bestSize < toFill && currSize > bestSize)
					//Sets the best Vehicle to the current Vehicle is the best
					//Vehicle can't exceed toFill value but the current does
					best = curr;
			}
			
			//Adds the best Vehicle to the fleet
			fleet.add(best);
			
			//Updates leftToFill by Deducting the capacity of the best vehicle
			leftToFill -= best.getCapacity();
			
			//Removes the best Vehicle for current loop from available Vehicles
			tempAvailable.remove(best);
		}
		
		//Returns the fleet for this hour
		return fleet;
	}
	
	/**
	 * Overridden method meant to convert the call for this Vehicle object as a
	 * String to automatically replace it with a formatted String version of it
	 */
	public String toString()
	{
		//Creates a new String containing the Vehicle object's class name and
		//attributes separated by commas (similar to ridership.txt)
		String toReturn = this.getClass() + ": " + unitNumber + "," + 
				idNumber + "," + capacity;
		
		return toReturn.substring(6);
	}
	
	//Non inclusive
	public static int generateNumber(int low, int high)
	{
		return random.nextInt(high - low) + low;
	}

	public int getUnitNumber()
	{
		return unitNumber;
	}

	public String getIdNumber()
	{
		return idNumber;
	}

	public int getCapacity()
	{
		return capacity;
	}
}
