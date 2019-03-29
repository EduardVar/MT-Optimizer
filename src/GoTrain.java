/**
 * Author:	Eduard Varshavsky
 * Date:	March 20, 2019
 * Desc:	Class inherited from Vehicle to store GO-Train related properties
 */

public final class GoTrain extends Vehicle
{
	/**
	 * Constructor used to create a new instance of a GoTrain object
	 * @param unitNumber is a String for the go-train's unit number
	 * @param idNumber is a String for the go-train's identification number
	 * @param capacity is a String for number of space go-train has to serve
	 */
	public GoTrain(String unitNumber, String idNumber, String capacity)
	{
		//Uses constructor from Vehicle.java to set GoTrain attributes
		super(unitNumber, idNumber, capacity);
	}
}
