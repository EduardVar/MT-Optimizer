import java.util.ArrayList;

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
	public ArrayList<Vehicle> assembleFleet(ArrayList<Vehicle> availableVehicles, float toFill)
	{
		ArrayList<Subway> allSubways = new ArrayList<>();
		
		for (Vehicle vehicle : availableVehicles)
			allSubways.add((Subway)vehicle);
		
		return super.assembleFleet(getAvailable(allSubways), toFill);
	}
	
	public static ArrayList<Vehicle> getAvailable(ArrayList<Subway> allSubways)
	{
		ArrayList<Vehicle> available = new ArrayList<>();
		
		//Makes only available
		for (Subway subway : allSubways)
			if (subway.getOperationalStatus() == 'A')
				available.add(subway);
		
		return available;
	}

	@Override
	public String toString()
	{
		String toReturn = this.getClass() + ": " + this.getUnitNumber() + "," + this.getIdNumber() + "," + 
				numOfCars + "," + passPerCar + "," + operationalStatus + "," + operationalDate;
		
		return toReturn.substring(6);
	}

	public char getOperationalStatus()
	{
		return operationalStatus;
	}	
}
