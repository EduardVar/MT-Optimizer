/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 20, 2019
 * Desc:	Class inherited from Bus meant to store GO-Bus related properties
 */

public final class GoBus extends Bus
{
	/**
	 * Constructor used to create a new instance of a GoBus object
	 * @param unitNumber is a String for the go-bus's unit number
	 * @param idNumber is a String for the go-bus's identification number
	 * @param capacity is a String for the number of space go-bus has to serve
	 */
	public GoBus(String unitNumber, String idNumber, String capacity)
	{
		//Uses parent class constructor from Bus.java to set GoBus attributes
		super(unitNumber, idNumber, capacity);
	}
}
