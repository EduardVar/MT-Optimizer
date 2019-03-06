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
}
