
public class AgeFormatException extends Exception
{
	public AgeFormatException() { super(); }
	
	public AgeFormatException(String reason) 
	{ 
		super("For input string: \"" + reason + "\""); 
	}
}
