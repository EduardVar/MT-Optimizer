/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	
 */

import java.util.ArrayList;
import java.util.Random;

public abstract class Vehicle implements Drivables
{
	private static Random random = new Random();
	
	//CHANGE TO PRIVATE LATER AND USE GETTER/SETTERS INSTEAD
	protected int unitNumber;
	protected String idNumber;
	protected int capacity;
	
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

	@Override
	public String toString()
	{
		return "Vehicle [unitNumber=" + unitNumber + ", idNumber=" + idNumber + ", capacity=" + capacity + "]";
	}
	
	//Non inclusive
	public static int generateNumber(int low, int high)
	{
		return random.nextInt(high - low) + low;
	}

	public int getCapacity()
	{
		return capacity;
	}
	
	public ArrayList<Vehicle> assembleFleet(ArrayList<Vehicle> availableVehicles, int toFill)
	{
		int leftToFill = toFill;
		
		ArrayList<Vehicle> fleet = new ArrayList<>();
		
		//Randomly choose
		while (leftToFill > 0)
		{
			Vehicle toAdd = availableVehicles.get(generateNumber(0, availableVehicles.size()));
			
			leftToFill -= toAdd.getCapacity();
			
			availableVehicles.remove(toAdd);
		}
		
		return fleet;
	}
}
