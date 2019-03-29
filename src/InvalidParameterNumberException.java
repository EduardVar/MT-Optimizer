/**
 * Author:	Eduard Varshavsky
 * Date:	March 27, 2019
 * Desc:	Custom exception class used for Invalid Age exceptions
 */

public class InvalidParameterNumberException extends Exception
{
	//Random identifier for the created exception generated
	private static final long serialVersionUID = -2834806843862625461L;

	/**
	 * Constructor for creating a new instance of this exception
	 * @param reason is a String for the item that caused the error
	 */
	public InvalidParameterNumberException(String line) 
	{ 
		//Calls the parent class constructor with a custom message
		super(line.split(",").length + " parameters given. Looking for 5"
				+ " parameters."); 
	}
}
