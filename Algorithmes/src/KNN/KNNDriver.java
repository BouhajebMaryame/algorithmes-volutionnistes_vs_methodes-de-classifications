package KNN;

public class KNNDriver {
	private static double FitnessIter[];
	public static void main(String args[]) {
	
		KNNProcess v = new KNNProcess();
		v.execute();
		FitnessIter=v.getFitnessIterarion();
	}
	public void execute()
	{
		KNNProcess v = new KNNProcess();
		v.execute();
		FitnessIter=v.getFitnessIterarion();
	}
	public  double [] getFitnesIter()
	{
		return FitnessIter;	
	}

}
