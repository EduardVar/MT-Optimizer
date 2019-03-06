/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	
 */

public final class StreetCar extends Vehicle
{
	private char type;
	
	private static String TYPE_SINGLE = "S";
	private static String TYPE_DOUBLE = "D";
	private static int SECTION_SIZE = 20;

	public StreetCar(String unitNumber, String idNumber, String type)
	{
		super(unitNumber, idNumber, getTypeCapacity(type));
		this.type = type.charAt(0);
	}
	
	private static String getTypeCapacity(String type)
	{
		if (type.equals(TYPE_SINGLE))
			return SECTION_SIZE + "";
		else if (type.equals(TYPE_DOUBLE))
			return (SECTION_SIZE * 2) + "";
		else
			return "-1";
	}

	@Override
	public String toString()
	{
		return "StreetCar [type=" + type + ", unitNumber=" + unitNumber +
				", idNumber=" + idNumber + ", capacity=" + capacity + "]";
	}
}
