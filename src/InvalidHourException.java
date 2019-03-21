/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 15, 2019
 * Desc:	
 */

public class InvalidHourException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6104274566850275482L;
	
	public InvalidHourException(String reason)
	{ 
		super(specifyReason(reason)); 
	}
	
	public static String specifyReason(String reason)
	{
		try 
		{
			Integer.parseInt(reason);
			return ("\"" + reason + "\" is not between hour 1 and 24.");
		}
		catch (Exception e) 
		{
			return ("\"" + reason + "\" is not an integer.");
		}
	}
}
