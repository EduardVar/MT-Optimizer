
public class ModalityFormatException extends Exception
{
	public ModalityFormatException() { super(); }
	
	public ModalityFormatException(String reason) 
	{ 
		super("For input string: \"" + reason + "\""); 
	}
}
