/**
 * Author:	Eduard Varshavsky
 * Date:	March 20, 2019
 * Desc:	Class inherited from Vehicle meant to store Bus related properties
 */

public class Bus extends Vehicle
{
	/**
	 * Constructor used to create a new instance of a Bus object
	 * @param unitNumber is a String for the bus's unit number
	 * @param idNumber is a String for the bus's identification number
	 * @param capacity is a String for the number of space the bus has to serve
	 */
	public Bus(String unitNumber, String idNumber, String capacity)
	{
		//Uses parent class constructor from Vehicle.java to set Bus attributes
		super(unitNumber, idNumber, capacity);
	}
}
