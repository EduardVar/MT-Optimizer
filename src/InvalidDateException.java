
public class InvalidDateException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5357130290575686090L;

	public InvalidDateException() { super(); }
	
	public InvalidDateException(String reason) 
	{ 
		super("For input string: \"" + reason + "\""); 
	}
}
