/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	
 */

public final class Subway extends Vehicle
{
	private int numOfCars;
	private int passPerCar;
	
	private char operationalStatus;
	private String operationalDate;
	
	public Subway(String unitNumber, String idNumber,
			String numOfCars, String passPerCar, String operationalStatus,
			String operationalDate)
	{
		super(unitNumber, idNumber, calcCapacity(numOfCars, passPerCar));
		this.numOfCars = Integer.parseInt(numOfCars);
		this.passPerCar = Integer.parseInt(passPerCar);
		this.operationalStatus = operationalStatus.charAt(0);
		this.operationalDate = operationalDate;
	}
	
	private static String calcCapacity(String numCars, String perCar)
	{
		return (Integer.parseInt(numCars) * Integer.parseInt(perCar)) + "";
	}

	@Override
	public String toString()
	{
		return "Subway [numOfCars=" + numOfCars + ", passPerCar=" + passPerCar + ", operationalStatus="
				+ operationalStatus + ", operationalDate=" + operationalDate + ", unitNumber=" + unitNumber
				+ ", idNumber=" + idNumber + ", capacity=" + capacity + "]";
	}
	
	
}
