package DE;

import java.util.Random;
import java.util.Vector;


public class DEProcess implements DEConstants{
	private Vector<Particule> populationDE = new Vector<Particule>();
    private static Individu gBestIndividu;
	private double[] fitnessValueList = new double[POPULATION_SIZE];
	private double[] fitnessIterarion = new double[MAX_ITERATION];
	private static int[][] result ;
	
	public static Individu getgBestIndividu() {
		return gBestIndividu;
	}


	public double[] getFitnessIterarion() {
		return fitnessIterarion;
	}
	
	public void execute() {
		initializePopulation();
		updateFitnessList();
	
		int t = 0;
		double err = 9999;
		
		while(t < MAX_ITERATION && err > ProblemSet.ERR_TOLERANCE) {
		
			int bestParticleIndex = DEUtility.getMinPos(fitnessValueList);
			gBestIndividu = populationDE.get(bestParticleIndex).getInd();
			fitnessIterarion[t]=populationDE.get(bestParticleIndex).getFitnessValue();
			if(bestParticleIndex!=0)
			{	
				Particule par = new Particule() ;
				par.setInd(gBestIndividu);
				par.getFitnessValue();
				populationDE.add(bestParticleIndex, populationDE.get(0));
				populationDE.add(0, par);
			}
			
			
			for(int i=1; i<= POPULATION_SIZE-3; i=i+3) {
				
				 Individu in1 = populationDE.get(i).getInd();
				 Individu in2 = populationDE.get(i+1).getInd();
				 Individu in3 = populationDE.get(i+2).getInd();
				 
				 double  [] chromosomeA = {in3.getInv()[1][0],in3.getInv()[1][1],in1.getInv()[1][2],in1.getInv()[1][3]};
				 double  [] chromosomeB = {in1.getInv()[1][0],in1.getInv()[1][1],in3.getInv()[1][2],in3.getInv()[1][3]};
				
				 double  [] chromosomeC = {in2.getInv()[0][0],in3.getInv()[0][1],in2.getInv()[0][2],in3.getInv()[0][3]};
				 double  [] chromosomeD = {in3.getInv()[0][0],in2.getInv()[0][1],in3.getInv()[0][2],in2.getInv()[0][3]};
				 
				 double  chromosome1[][] = {in1.getInv()[0],chromosomeA,in2.getInv()[2]};
				 double  chromosome2[][] = {in1.getInv()[2], in2.getInv()[1],chromosomeC};
				 double  chromosome3[][] = {chromosomeD,in3.getInv()[2] ,chromosomeB};
				 
				 populationDE.get(i).setInd(new Individu(chromosome1));
				 populationDE.get(i+1).setInd(new Individu(chromosome2));
				 populationDE.get(i+2).setInd(new Individu(chromosome3));
				 
				
			}
			
			err = ProblemSet.evaluate(gBestIndividu) - 0; // minimizing the functions means it's getting closer to 0
			
			
			System.out.println("ITERAT ION " + t + ": ");
			System.out.println("     Best A: " + gBestIndividu.getInv()[0][0] +" "+ gBestIndividu.getInv()[0][1]+" "+ gBestIndividu.getInv()[0][2]+" "+  gBestIndividu.getInv()[0][3]);
			System.out.println("     Best B: " + gBestIndividu.getInv()[1][0] +" "+ gBestIndividu.getInv()[1][1]+" "+ gBestIndividu.getInv()[1][2]+" "+  gBestIndividu.getInv()[1][3]);
			System.out.println("     Best C: " + gBestIndividu.getInv()[2][0] +" "+ gBestIndividu.getInv()[2][1]+" "+ gBestIndividu.getInv()[2][2]+" "+  gBestIndividu.getInv()[2][3]);
			System.out.println("     Value: " + ProblemSet.evaluate(gBestIndividu));
			
			t++;
			updateFitnessList();
		}
		
		System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
		System.out.println("     Best A: " + gBestIndividu.getInv()[0][0] +" "+ gBestIndividu.getInv()[0][1]+" "+ gBestIndividu.getInv()[0][2]+" "+  gBestIndividu.getInv()[0][3]);
		System.out.println("     Best B: " + gBestIndividu.getInv()[1][0] +" "+ gBestIndividu.getInv()[1][1]+" "+ gBestIndividu.getInv()[1][2]+" "+  gBestIndividu.getInv()[1][3]);
		System.out.println("     Best C: " + gBestIndividu.getInv()[2][0] +" "+ gBestIndividu.getInv()[2][1]+" "+ gBestIndividu.getInv()[2][2]+" "+  gBestIndividu.getInv()[2][3]);
		setResult(ProblemSet.Afficher(gBestIndividu));

	}
	public void initializePopulation() {
		Random generator = new Random();
		Particule p;
		for(int i=0; i<POPULATION_SIZE; i++) {
			p = new Particule();
			
			// randomize location inside a space defined in Problem Set
			double[][] ind = new double[PROBLEM_DIMENSION][NB_VAR];
			for(int k=0;k<PROBLEM_DIMENSION ;k++)
			{
				for(int j=0;j<NB_VAR ;j++)
				{
					ind[k][j]=ProblemSet.don.mix[k][j][0]+ generator.nextDouble() * (ProblemSet.don.mix[k][j][1] - ProblemSet.don.mix[k][j][0]);
					
				}
			}
			Individu individu = new Individu(ind);
			p.setInd(individu);
			populationDE.add(p);
		}
	}
	 public void updateFitnessList() {
	    	for(int i=0; i<POPULATION_SIZE; i++) {
			fitnessValueList[i] = populationDE.get(i).getFitnessValue();
	    	}
	}

	public static int[][] getResult() {
		return result;
	}

	public static void setResult(int[][] result) {
		DEProcess.result = result;
	}
	
}
