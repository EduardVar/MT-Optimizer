/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 15, 2019
 * Desc:	
 */

public class AgeFormatException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7382269421507926378L;

	public AgeFormatException() { super(); }
	
	public AgeFormatException(String reason) 
	{ 
		super("For input string: \"" + reason + "\""); 
	}
}
