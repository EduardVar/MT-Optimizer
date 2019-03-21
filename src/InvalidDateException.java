/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 15, 2019
 * Desc:	
 */

public class InvalidDateException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5357130290575686090L;

	public InvalidDateException() { super(); }
	
	public InvalidDateException(String reason) 
	{ 
		super("\"" + reason + "\" does not match numerical format \"yyyymmdd\"."); 
	}
}
