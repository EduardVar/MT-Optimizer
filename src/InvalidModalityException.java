/**
 * Author:	Eduard Varshavsky
 * Date:	March 27, 2019
 * Desc:	Custom exception class used for Invalid Age exceptions
 */

public class InvalidModalityException extends Exception
{
	//Random identifier for the created exception generated
	private static final long serialVersionUID = -1169277229842149735L;

	/**
	 * Constructor for creating a new instance of this exception
	 * @param reason is a String for the item that caused the error
	 */
	public InvalidModalityException(String reason) 
	{ 
		//Calls the parent class constructor with a custom message
		super("\"" + reason + "\" does not match accepted characters \"S\","
				+ " \"G\", \"X\", \"C\", or \"D\""); 
	}
}
