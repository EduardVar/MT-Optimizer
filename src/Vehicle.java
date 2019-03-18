/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	
 */

import java.util.ArrayList;
import java.util.Random;

public abstract class Vehicle
{
	private static Random random = new Random();
	
	//CHANGE TO PRIVATE LATER AND USE GETTER/SETTERS INSTEAD
	private int unitNumber;
	private String idNumber;
	private int capacity;
	
	protected Vehicle(String unitNumber, String idNumber, String capacity)
	{
		this.unitNumber = Integer.parseInt(unitNumber);
		this.idNumber = idNumber;
		this.capacity = Integer.parseInt(capacity);
	}
	
	protected Vehicle()
	{
		unitNumber = -1;
		idNumber = "~NONE~";
		capacity = -1;
	}
	
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
	
	@Override
	public String toString()
	{
		String toReturn = this.getClass() + ": " + unitNumber + "," + idNumber + "," + capacity;
		
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
