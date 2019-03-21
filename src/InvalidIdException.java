/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 15, 2019
 * Desc:	
 */

public class InvalidIdException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1956459928262905715L;

	public InvalidIdException() { super(); }
	
	public InvalidIdException(String reason) 
	{ 
		super("\"" + reason + "\" does not match ID format \"*\", a unique 7 digit code, or \"Tyyyymmddnnnnnnn\"."); 
	}
}
