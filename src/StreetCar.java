/**
 * Author:	Eduard Varshavsky
 * Date:	March 27, 2019
 * Desc:	Class inherited from Vehicle to store Street Car related properties
 */

public final class StreetCar extends Vehicle
{
	// *** CLASS VARIABLES ***
	
	//Character used to store the type of the Street Car
	private char type;
	
	//Constant Strings meant to store possible types the Street Car can be
	private static final String TYPE_SINGLE = "S";
	private static final String TYPE_DOUBLE = "D";
	
	//Constant Integer meant to store how much capacity a section has
	private static final int SECTION_SIZE = 40;

	// *** CONSTRUCTOR METHODS ***
	
	/**
	 * Constructor to create a new instance of StreetCar
	 * @param unitNumber as a String object for the Street Car's unit number
	 * @param idNumber as a String object for the Street Car's ID number
	 * @param type as a String for the type of Street Car the object is
	 */
	public StreetCar(String unitNumber, String idNumber, String type)
	{
		//Uses parent class's constructor, calculates capacity within the call
		super(unitNumber, idNumber, getTypeCapacity(type));
		
		//Sets type to the given type string as a character
		this.type = type.charAt(0);
	}
	
	// *** OBJECT-BEHAVIOUR METHODS ***
	
	/**
	 * Intermediary function used to determine the capacity of a Street Car
	 * @param type is a String of data as long as a char for Street Car type
	 * @return a String for the capacity of the Street Car
	 */
	private static String getTypeCapacity(String type)
	{
		//Checks if type matches a constant already made
		if (type.equals(TYPE_SINGLE))
			//Returns the amount of only a single section
			return SECTION_SIZE + "";
		else if (type.equals(TYPE_DOUBLE))
			//Returns the amount of two singles since Street Car is a double
			return (SECTION_SIZE * 2) + "";
		else
			//Returns a -1 (error capacity) if it doesn't find a type match
			return "-1";
	}
	
	// *** MUTATOR METHODS ***
	
	// *** ACCESSOR METHODS ***

	/**
	 * Overridden method used to get string representation of Street Car object
	 * @return a String in the same form as the input lines given in input file
	 */
	public String toString()
	{
		//toReturn String containing all the information of the Subway objects
		String toReturn = this.getClass() + ": " + this.getUnitNumber() + ","
				+ this.getIdNumber() + "," + type;
		
		//Returns toString from the 6th char onwards to not contain "class" at
		//the beginning of the String
		return toReturn.substring(6);
	}
}
