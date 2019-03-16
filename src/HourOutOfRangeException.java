/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 15, 2019
 * Desc:	
 */

public class HourOutOfRangeException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6104274566850275482L;

	public HourOutOfRangeException() { super(); }
	
	public HourOutOfRangeException(String reason) { super(reason); }
}
