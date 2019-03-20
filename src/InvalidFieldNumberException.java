/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 20, 2019
 * Desc:	
 */

public class InvalidFieldNumberException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2834806843862625461L;

	public InvalidFieldNumberException() { super(); }
	
	public InvalidFieldNumberException(String line) 
	{ 
		super(line.split(",").length + " parameters given. Looking for 5"
				+ " parameters."); 
	}
}
