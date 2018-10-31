package pso;

import DE.DEProcess;

public class PSODriver {
	private int result[][];
	private static double FitnessIter[];
	public static void main(String args[]) {
	
		PSOProcess v = new PSOProcess();
		v.execute();
		FitnessIter=v.getFitnessIterarion();
	}
	public void execute()
	{
		PSOProcess v = new PSOProcess();
		v.execute();
		FitnessIter=v.getFitnessIterarion();
	}
	public  double [] getFitnesIter()
	{
		
		return FitnessIter;
		
	}
	


}
