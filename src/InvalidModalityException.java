/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 15, 2019
 * Desc:	
 */

public class InvalidModalityException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1169277229842149735L;

	public InvalidModalityException() { super(); }
	
	public InvalidModalityException(String reason) 
	{ 
		super("\"" + reason + "\" does not match accepted characters \"S\", \"G\", \"X\", \"C\", or \"D\""); 
	}
}
