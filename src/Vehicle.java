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
		return this.getClass() + " Unit# = " + unitNumber + ", ID = " + idNumber + ", Capacity = " + capacity;
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
	
	public ArrayList<Vehicle> assembleFleet(ArrayList<Vehicle> availableVehicles, float toFill)
	{
		//NEED TO IMPLEMENT TRUE OPTIMIZATION ALGORITHM
		//He keeps changing the specifications ;_;
		
		float leftToFill = toFill;
		
		ArrayList<Vehicle> tempAvailable = new ArrayList<>();
		
		for (Vehicle vehicle : availableVehicles)
			tempAvailable.add(vehicle);
		
		ArrayList<Vehicle> fleet = new ArrayList<>();
		
		System.out.println("NEW FLEET");
		
		while (leftToFill > 0)
		{
			System.out.println("To fill: " + toFill);
			
			Vehicle best = tempAvailable.get(0);
			
			for (Vehicle curr : tempAvailable)
			{
				float bestSize = best.getCapacity();
				float currSize = curr.getCapacity();
				
				if (currSize >= toFill && currSize < bestSize)
					best = curr;
				else if (bestSize < toFill && currSize > bestSize)
					best = curr;
			}
			
			fleet.add(best);
			leftToFill -= best.getCapacity();
			tempAvailable.remove(best);
		}
		
		return fleet;
	}
}
