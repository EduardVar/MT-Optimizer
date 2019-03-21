/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 15, 2019
 * Desc:	
 */

public class InvalidAgeException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7382269421507926378L;

	public InvalidAgeException() { super(); }
	
	public InvalidAgeException(String reason) 
	{ 
		super("\"" + reason + "\" does not match accepted characters \"A\", \"C\", or \"S\"."); 
	}
}
