/**
 * Author:	Eduard Varshavsky
 * Date:	March 26, 2019
 * Desc:	Custom exception class used for Invalid Age exceptions
 */

public class InvalidHourException extends Exception
{
	//Random identifier for the created exception generated
	private static final long serialVersionUID = -6104274566850275482L;
	
	/**
	 * Constructor for creating a new instance of this exception
	 * @param reason is a String for the item that caused the error
	 */
	public InvalidHourException(String reason)
	{ 
		//Calls the parent class constructor but calls function first to
		//specify the reason for the exception
		super(specifyReason(reason)); 
	}
	
	/**
	 * Intermediately provides a description for the reason why the exception
	 * occurred. Used during constructor calls
	 * @param reason is a String for the item that caused the exception
	 * @return a message for the exception as a String
	 */
	public static String specifyReason(String reason)
	{
		try 
		{
			//Attempts to parse the integer
			Integer.parseInt(reason);
			
			//Returns a message where the item is a valid integer but not
			//within the 24 hour range
			return ("\"" + reason + "\" is not between hour 1 and 24.");
		}
		catch (Exception e) 
		{
			//If the Integer parsing fails, that means the item isn't an
			//integer, so the message is based around that reason
			return ("\"" + reason + "\" is not an integer.");
		}
	}
}
