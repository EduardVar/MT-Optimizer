/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	March 15, 2019
 * Desc:	
 */

public class ModalityFormatException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1169277229842149735L;

	public ModalityFormatException() { super(); }
	
	public ModalityFormatException(String reason) 
	{ 
		super("For input string: \"" + reason + "\""); 
	}
}
