/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 22, 2019
 * Desc:	This is an abstract class for Vehicle sub-types to inherit from.
 * 			Contains general attributes and behaviors used by any vehicle.
 */

import java.util.ArrayList;

public abstract class Vehicle
{
	// *** CLASS VARIABLES ***
	
	//Stores the unit number of the Vehicle object as an integer
	private int unitNumber;
	
	//Stores the identification number of the Vehicle object as a String
	private String idNumber;
	
	//Stores the capacity of the Vehicle object as an integer
	private int capacity;
	
	// *** CONSTRUCTOR METHODS ***
	
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
	
	// *** OBJECT-BEHAVIOUR METHODS ***
	
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
		
		/*
		 * NOTE:
		 * I am aware there is a method in assembling the fleet by sorting the
		 * tempAvailable list and then picking the highest capacity vehicles
		 * until satisfied being an option for this problem.
		 * 
		 * I instead opted to implement a true optimization algorithm that
		 * minimizes the amount of vehicles and also optimizes the space the
		 * vehicles have to not send an excessively large vehicles to 
		 * accommodate riders that don't meed tje,
		 */
		
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
	
	// *** MUTATOR METHODS ***
	
	// *** ACCESSOR METHODS ***
	
	/**
	 * Overridden method meant to convert the call for this Vehicle object as a
	 * String to automatically replace it with a formatted String version of it
	 */
	public String toString()
	{
		//Creates a new String containing the Vehicle object's class name and
		//attributes separated by commas (similar to ridership.txt)
		String toReturn = this.getClass().toString().toLowerCase() + ": " +
				unitNumber + "," + idNumber + "," + capacity;
		
		//Returns toReturn cut so the first part where it says "class"
		return toReturn.substring(6);
	}

	/**
	 * Getter used to access the Vehicle object's Unit Number attribute
	 * @return integer of the Vehicle's unit number
	 */
	public int getUnitNumber()
	{
		return unitNumber;
	}

	/**
	 * Getter used to access Vehicle object's identification number attribute
	 * @return String of the Vehicle's ID number
	 */
	public String getIdNumber()
	{
		return idNumber;
	}

	/**
	 * Getter used to access Vehicle object's capacity attribute
	 * @return integer of the Vehicle's capacity
	 */
	public int getCapacity()
	{
		return capacity;
	}
}
