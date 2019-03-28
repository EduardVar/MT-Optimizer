/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 27, 2019
 * Desc:	Custom exception class used for Invalid ID exceptions
 */

public class InvalidIdException extends Exception
{
	//Random identifier for the created exception generated
	private static final long serialVersionUID = -1956459928262905715L;

	/**
	 * Constructor for creating a new instance of this exception
	 * @param reason is a String for the item that caused the error
	 */
	public InvalidIdException(String reason) 
	{ 
		//Calls the parent class constructor with a custom message
		super("\"" + reason + "\" does not match ID format \"*\", a unique 7"
				+ " digit code, or \"Tyyyymmddnnnnnnn\"."); 
	}
}
