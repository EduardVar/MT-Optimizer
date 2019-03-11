/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	
 */

public final class Passenger
{
	//In industry, this would use a function to get the current date or the
	//day all the transactions were made. (Use this for project purposes)
	private static String globalDate = "NONE";
	
	private static final float CHILD_SIZE = 0.75f;
	private static final float ADULT_SIZE = 1.0f;
	private static final float SENIOR_SIZE = 1.25f;
	
	private String ID;
	
	private char modality;
	private char ageGroup;
	
	private int hour;
	private String date;
	
	private float size = 0;
	
	public Passenger(String item0, String item1, String item2, String item3,
			String item4) throws IDFormatException, AgeFormatException,
			ModalityFormatException, HourOutOfRangeException, 
			InvalidDateException
	
	{	
		this.ID = item0;
		this.modality = item1.length() == 1 ? item1.charAt(0) : '?';
		this.ageGroup = item2.length() == 1 ? item2.charAt(0) : '?';
		this.hour = Integer.parseInt(item3);
		this.date = item4;
		
		checkForExceptions(item0, item1, item2, item3, item4);
	}
	
	public void checkForExceptions(String item0, String item1, String item2,
			String item3, String item4) throws IDFormatException, 
			AgeFormatException, ModalityFormatException, HourOutOfRangeException, 
			InvalidDateException
	{
		if (!(ID.length() == 7 || ID.length() == 14 || ID.equals("*")))
			throw new IDFormatException(item0);
		
		if (!(modality == 'S' || modality == 'G' || modality == 'X' ||
				modality == 'C' || modality == 'D'))
			throw new ModalityFormatException(item1);
		
		if (!(ageGroup == 'C' || ageGroup == 'A' || ageGroup == 'S'))
			throw new AgeFormatException(item2);
					
		if (hour < 1 || hour > 24)
			throw new HourOutOfRangeException(item3);
		
		if (globalDate.equals("NONE"))
			globalDate = item4;
		else
			if (!(item4.equals(globalDate)))
				throw new InvalidDateException(item4);
	}
	
	public void setPassengerSize()
	{
		switch (ageGroup)
		{
		case 'C':
			size = CHILD_SIZE;
			break;
		case 'A':
			size = ADULT_SIZE;
			break;
		case 'S':
			size = SENIOR_SIZE;
			break;
		}
	}

	@Override
	public String toString()
	{
		return "Person [ID=" + ID + ", modality=" + modality + ", ageGroup=" +
				ageGroup + ", hour=" + hour + ", date=" + date + "]";
	}
}
