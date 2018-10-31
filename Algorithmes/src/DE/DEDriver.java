package DE;


public class DEDriver {
	private static double FitnessIter[];
	public static void main(String args[]) {
	
		DEProcess v = new DEProcess();
	     v.execute();
		FitnessIter=v.getFitnessIterarion();
	}
	public void execute()
	{
		DEProcess v = new DEProcess();
		v.execute();
		FitnessIter=v.getFitnessIterarion();
	}
	public  double [] getFitnesIter()
	{
		
		return FitnessIter;
		
	}
}
