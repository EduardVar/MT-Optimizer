/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 22, 2019
 * Desc:	This class is used to store and calculate attributes of a passenger
 * 			Also used to catch any errors in input during instantiation.
 */

import java.util.Map;

public final class Passenger
{
	// *** CLASS VARIABLES ***
	
	//In industry, this would use a function to get the current date or the
	//day all the transactions were made. (Use this for project purposes)
	private static int globalDate = -1;
	
	//Sets float constants for size passenger takes up based on age
	private static final float CHILD_SIZE = 0.75f;
	private static final float ADULT_SIZE = 1.0f;
	private static final float SENIOR_SIZE = 1.25f;
	
	//String to store the passenger's identifications
	private String ID;
	
	//Characters to store the modality and age group of passenger
	private char modality;
	private char ageGroup;
	
	//Integer to store the hour the passenger rode
	private int hour;
	
	//Integer to store the date the passenger rode 
	private int date;
	
	//Stores the passenger's size as a float
	private float size = 0;

	//Stores String of any errors the Passenger object had during validation
	private String errorLog = "";

	// *** CONSTRUCTOR METHODS ***
	
	/**
	 * This is the constructor for a Passenger object. Takes care of setting
	 * attributes and checking for any exceptions in the given data
	 * @param item0 meant to store the ID of the passenger. Remains a String
	 * @param item1 meant to store modality of the passenger. Should be a char
	 * @param item2 meant to store the age of the passenger. Should be a char
	 * @param item3 meant to store hour passenger rode. Should be an integer
	 * @param item4 meant to store the date passenger rode. Remains a String
	 */
	public Passenger(String item0, String item1, String item2, String item3,
			String item4)
	
	{	
		//Checks for any exceptions in the input given with this function
		validateInput(item0, item1, item2, item3, item4);
		
		//Sets the size attribute of the passenger to the result of this method
		size = setPassengerSize();
	}

	// *** OBJECT-BEHAVIOUR METHODS ***
	
	/**
	 * This functions is used to update the total capacities in the Hash Map
	 * allInfo, uses individual Passenger object size to add onto it
	 * @param allInfo the map containing the required capacity to fill each
	 * 				  subsection for each hour
	 */
	public void addToInfo(Map<Integer, Map<Character, Float>>allInfo)
	{
		//Stores Vehicle map of the capacities to-fill for passenger's hour
		Map<Character, Float> inner = allInfo.get(hour);	
		
		//Adds passenger's size to modality to-fill of the passenger's modality
		float newSize = inner.get(modality) + size;
		
		//Replaces overall size to-fill for passenger's modality with newSize
		inner.put(modality, newSize);	
		
		//Places the updated map under the specific hour that it was modified
		allInfo.put(hour, inner);
	}
	
	/**
	 * This function is used to check for any exceptions in the data provided
	 * during construction of the Passenger object. Sets the data if valid.
	 * @param item0 meant to store the ID of the passenger. Remains a String
	 * @param item1 meant to store modality of the passenger. Should be a char
	 * @param item2 meant to store the age of the passenger. Should be a char
	 * @param item3 meant to store hour passenger rode. Should be an integer
	 * @param item4 meant to store the date passenger rode. Remains a String
	 */
	public void validateInput(String item0, String item1, String item2,
			String item3, String item4)
	{
		//Tries to validate if item0 is a suitable String for ID
		try 
		{
			//Sets the ID to the String of item0
			ID = item0;
			
			//Checks if the ID matches the dimensions and requirements required
			if (!((ID.length() == 16 && ID.charAt(0) == 'T') || 
					ID.equals("*") || (ID.length() == 7 && checkIfInt(ID))))
				//Throws specific exception using the invalid ID
				throw new InvalidIdException(item0);
		}
		catch (InvalidIdException e) 
		{
			//Adds the InvalidException error to Passenger object's errorLog
			addToErrorLog(e.toString());
		}
		
		//Tries to validate if item1 is a suitable character for modality
		try
		{
			//Checks if the given string is the size of a character. If it is
			//not, sets the character as a ? to raise an exception later
			modality = item1.length() == 1 ? item1.charAt(0) : '?';
			
			//Checks if  modality matches dimensions and requirements required
			if (!(modality == 'S' || modality == 'G' || modality == 'X' ||
					modality == 'C' || modality == 'D'))
				//Throws specific exception using the invalid modality format
				throw new InvalidModalityException(item1);
		}
		catch (InvalidModalityException e) 
		{
			//Adds the InvalidModalityException error to Passenger's errorLog
			addToErrorLog(e.toString());
		}
		
		//Tries to validate if item2 is a suitable character for ageGroup
		try
		{
			//Checks if the given string is the size of a character. If it is
			//not sets the character as a ? to raise an exception later
			ageGroup = item2.length() == 1 ? item2.charAt(0) : '?';
			
			//Checks if age matches the dimensions and requirements required
			if (!(ageGroup == 'C' || ageGroup == 'A' || ageGroup == 'S'))
				//Throws specific exception using the invalid age format
				throw new InvalidAgeException(item2);
		}
		catch (InvalidAgeException e) 
		{
			//Adds the InvalidAgeException error to Passenger's errorLog
			addToErrorLog(e.toString());
		}
		
		//Tries to validate if item3 is a suitable integer for hour
		try
		{
			//Sets hour to -1 if it can't be converted to an int, otherwise 
			//parses item3 into hour as an integer
			hour = checkIfInt(item3) ? Integer.parseInt(item3) : -1;
						
			//Checks if hour falls within the 24 hour range
			if (hour < 1 || hour > 24)
				//Throws specific exception using the invalid hour value
				throw new InvalidHourException(item3);
		}
		catch (InvalidHourException e) 
		{
			//Adds the InvalidHourException error to Passenger's errorLog
			addToErrorLog(e.toString());
		}
		
		//Tries to validate if item4 is a suitable integer for the date
		try
		{
			//Sets date to -1 if it can't be converted to an int, otherwise
			//parses item4 into date as an integer
			date = checkIfInt(item4) ? Integer.parseInt(item4) : -1;
			
			//In industry, will check via records if the date matches records
			if (globalDate == -1)
				//For this program, set first date in rider ship as global date
				globalDate = date;
			else
				//Checks if the date provided does not matches the global date
				if (!(date == globalDate))
					//Throws specific exception using the invalid date given
					throw new InvalidDateException(item4);
		}
		catch (InvalidDateException e) 
		{
			//Adds the InvalidDateException error to Passenger's errorLog
			addToErrorLog(e.toString());
		}
		
		//If there is content in errorLog, add the line and which number it is
		//in the file to the end of errorLog
		if (!(errorLog.equals("")))
			errorLog += "On line " + MTOptimizer.getRiderCount() + ": \"" + 
					MTOptimizer.getLine() + "\"";
	}
	
	/**
	 * This function is used to check if a String can be parsed as an integer
	 * @param toCheck is the String object to check if it's an integer
	 * @return true if able to be parsed, otherwise returns false
	 */
	public boolean checkIfInt(String toCheck)
	{
		//Tries to parse toCheck as an integer
		try {
			//Parses toCheck with parseInt to an integer
			Integer.parseInt(toCheck);
			
			//Returns true if code was successful
			return true;
		}
		catch (NumberFormatException e) {
			//Returns false if an error occurred while parsing the String
			return false;
		}
	}
	
	// *** MUTATOR METHODS ***
	
	/**
	 * Adds an exception's string content to error log
	 * @param e is the exception (must use .toString() on exception object)
	 */
	public void addToErrorLog(String e)
	{	
		errorLog +=  e + "\r\n";
	}
	
	/**
	 * Provides a value for how much space the passenger takes up on a Vehicle
	 * @return float for how much space the passenger takes up
	 */
	public float setPassengerSize()
	{
		//Checks was character ageGroup is and returns appropriate size for it
		switch (ageGroup)
		{
		case 'C':
			//Returns float size value for a child
			return CHILD_SIZE;
		case 'A':
			//Returns float size value for an adult
			return ADULT_SIZE;
		case 'S':
			//Returns float size value for a senior
			return SENIOR_SIZE;
		default:
			return -1;
		}
	}
	
	// *** ACCESSOR METHODS ***
	
	/**
	 * Getter used to access the hour attribute
	 * @return integer of the hour the Passenger object rode
	 */
	public int getHour()
	{
		return hour;
	}

	/**
	 * Getter used to access the modality attribute
	 * @return String of the modality the Passenger object rode
	 */
	public char getModality()
	{
		return modality;
	}

	/**
	 * Getter used to access the size attribute
	 * @return integer of the size the Passenger object is
	 */
	public float getSize()
	{
		return size;
	}

	/**
	 * Getter used to access the getErrorLog attribute
	 * @return String of the erroLog content the Passenger has
	 */
	public String getErrorLog()
	{
		return errorLog;
	}
}
