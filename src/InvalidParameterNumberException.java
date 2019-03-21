/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 20, 2019
 * Desc:	
 */

public class InvalidParameterNumberException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2834806843862625461L;

	public InvalidParameterNumberException(String line) 
	{ 
		super(line.split(",").length + " parameters given. Looking for 5"
				+ " parameters."); 
	}
}
