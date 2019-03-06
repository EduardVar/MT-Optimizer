
public class IDFormatException extends Exception
{
	public IDFormatException() { super(); }
	
	public IDFormatException(String reason) 
	{ 
		super("For input string: \"" + reason + "\""); 
	}
}
