/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 15, 2019
 * Desc:	
 */

public class IDFormatException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1956459928262905715L;

	public IDFormatException() { super(); }
	
	public IDFormatException(String reason) 
	{ 
		super("For input string: \"" + reason + "\""); 
	}
}
