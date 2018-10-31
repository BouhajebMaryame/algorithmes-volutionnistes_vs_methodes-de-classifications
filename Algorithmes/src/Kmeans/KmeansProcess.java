package Kmeans;

import java.util.Random;
import DE.DEConstants;
import DE.Individu;

public class KmeansProcess implements DEConstants{
	private Individu individu;
	private double[] fitnessIterarion = new double[MAX_ITERATION];
	private static int[][] result ;
	
	public double[] getFitnessIterarion() {
		return fitnessIterarion;
	}
	
	public void execute() {
		initializePopulation();
	
		int t = 0;
		double err = 9999;
		
		while(t < MAX_ITERATION && err > ProblemSet.ERR_TOLERANCE) {
			
			fitnessIterarion[t]=ProblemSet.evaluate(individu);
			System.out.println("ITERAT ION " + t + ": ");
			System.out.println("     Best A: " + individu.getInv()[0][0] +" "+ individu.getInv()[0][1]+" "+ individu.getInv()[0][2]+" "+ individu.getInv()[0][3]);
			System.out.println("     Best B: " + individu.getInv()[1][0] +" "+ individu.getInv()[1][1]+" "+ individu.getInv()[1][2]+" "+  individu.getInv()[1][3]);
			System.out.println("     Best C: " + individu.getInv()[2][0] +" "+ individu.getInv()[2][1]+" "+ individu.getInv()[2][2]+" "+  individu.getInv()[2][3]);
			System.out.println("     Value: " + fitnessIterarion[t]);
			
			t++;
			
		}
		
		System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
		System.out.println("     Best A: " + individu.getInv()[0][0] +" "+ individu.getInv()[0][1]+" "+ individu.getInv()[0][2]+" "+ individu.getInv()[0][3]);
		System.out.println("     Best B: " + individu.getInv()[1][0] +" "+ individu.getInv()[1][1]+" "+ individu.getInv()[1][2]+" "+  individu.getInv()[1][3]);
		System.out.println("     Best C: " + individu.getInv()[2][0] +" "+ individu.getInv()[2][1]+" "+ individu.getInv()[2][2]+" "+  individu.getInv()[2][3]);
		setResult(ProblemSet.Afficher(individu));
		 
		 
		
	}
	public void initializePopulation() {
		Random generator = new Random();
		for(int i=0; i<POPULATION_SIZE; i++) {
			// randomize location inside a space defined in Problem Set
			double[][] ind = new double[PROBLEM_DIMENSION][NB_VAR];
			for(int k=0;k<PROBLEM_DIMENSION ;k++)
			{
				for(int j=0;j<NB_VAR ;j++)
				{
					ind[k][j]=ProblemSet.don.getMix()[k][j][0]+ generator.nextDouble() * (ProblemSet.don.getMix()[k][j][1] - ProblemSet.don.getMix()[k][j][0]);
					
				}
			}
			individu = new Individu(ind);
		}
	}

	public static int[][] getResult() {
		return result;
	}

	public static void setResult(int[][] result) {
		KmeansProcess.result = result;
	}
	
}
