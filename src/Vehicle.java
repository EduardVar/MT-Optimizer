/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	
 */

public abstract class Vehicle
{
	//MIGHT NEED TO CHANGE 
	protected int unitNumber;
	protected String idNumber;
	protected int capacity;
	
	protected Vehicle(int unitNumber, String idNumber, int capacity)
	{
		this.unitNumber = unitNumber;
		this.idNumber = idNumber;
		this.capacity = capacity;
	}
	
	protected Vehicle(int unitNumber, String idNumber)
	{
		this(unitNumber, idNumber, -1);
	}
	
	protected Vehicle()
	{
		unitNumber = -1;
		idNumber = "~NONE~";
		capacity = -1;
	}
	
	
}
