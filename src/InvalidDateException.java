/**
 * Author:	Eduard Varshavsky
 * Date:	March 26, 2019
 * Desc:	Custom exception class used for Invalid Age exceptions
 */

public class InvalidDateException extends Exception
{
	//Random identifier for the created exception generated
	private static final long serialVersionUID = 5357130290575686090L;

	/**
	 * Constructor for creating a new instance of this exception
	 * @param reason is a String for the item that caused the error
	 */
	public InvalidDateException(String reason) 
	{ 
		//Calls the parent class constructor with a custom message
		super("\"" + reason + "\" does not match numerical "
				+ "format \"yyyymmdd\"."); 
	}
}
