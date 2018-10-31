package Kmeans;


public class KmenasDriver {
	private static double FitnessIter[];
	public static void main(String args[]) {
		KmeansProcess v = new KmeansProcess();
		v.execute();
		FitnessIter=v.getFitnessIterarion();
	}
	public void execute()
	{
		KmeansProcess v = new KmeansProcess();
		v.execute();
		FitnessIter=v.getFitnessIterarion();
	}
	public  double [] getFitnesIter()
	{
		
		return FitnessIter;
		
	}
}
