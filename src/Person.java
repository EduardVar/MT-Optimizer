/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	
 */

public final class Person
{
	private String ID;
	
	private char modality;
	private char ageGroup;
	
	private int hour;
	private String date;
	
	public Person(String item0, String item1, String item2, String item3,
			String item4) throws IDFormatException, AgeFormatException,
			ModalityFormatException, HourOutOfRangeException
	
	{
		String ID = item0;
		char modality = item1.charAt(0);
		char ageGroup = item2.charAt(0);
		int hour = Integer.parseInt(item3);
		String date = item4;
		
		if (!(ID.length() == 7 || ID.length() == 14 || ID.equals("*")))
			throw new IDFormatException(item0);
		
		if ((item1.length() != 1) || !(modality == 'S' || modality == 'G' ||
				modality == 'X' || modality == 'C' || modality == 'D'))
			throw new ModalityFormatException(item1);
		
		if ((item2.length() != 1) || !(ageGroup == 'C' || ageGroup == 'A' ||
				ageGroup == 'S'))
			throw new AgeFormatException(item2);
					
		if (hour < 1 || hour > 24)
			throw new HourOutOfRangeException(item3);
		
		//CHECK IF DATE WORKS
		
		this.ID = ID;
		this.modality = modality;
		this.ageGroup = ageGroup;
		this.hour = hour;
		this.date = date;
	}

	@Override
	public String toString()
	{
		return "Person [ID=" + ID + ", modality=" + modality + ", ageGroup=" +
				ageGroup + ", hour=" + hour + ", date=" + date + "]";
	}
}
