package KNN;
import java.util.HashMap;
import java.util.Map;

public class KNNProcess {
	private static int MAX_ITERATION=15;
	private Map<Individu, Integer> Population = new HashMap<Individu, Integer> ();
	private double[] fitnessIterarion = new double[MAX_ITERATION];
	private static int result[][];
	
	public double[] getFitnessIterarion() {
		return fitnessIterarion;
	}
	
	public void execute() {
		initializePopulation();
		int t = 1;
		int i=0;
		while(t < MAX_ITERATION ) {
			System.out.println("ITERAT ION " + t + ": ");
			fitnessIterarion[t]=Problemset.evaluate(t,Population);
			System.out.println("     Value: " +fitnessIterarion[t]);
			i++;
			t=i*3+1;
			
		}
		
		System.out.println("\nSolution found at iteration " + (t) + ", the solutions is:");
		setResult(Problemset.Afficher());
		 
		 
		
	}
	public void initializePopulation() {
		for(int i=30; i<40; i++) {
			double[] ind = new double[4];
			ind  =  Problemset.don.getIris()[i];
			Individu individu = new Individu(ind);
			Population.put(individu, 1);
		}
		
		for(int i=80; i<90; i++) {
			double[] ind = new double[4];
			ind  =  Problemset.don.getIris()[i];
			Individu individu = new Individu(ind);
			Population.put(individu, 2);
		}
		
		for(int i=100; i<110; i++) {
			double[] ind = new double[4];
			ind  =  Problemset.don.getIris()[i];
			Individu individu = new Individu(ind);
			Population.put(individu, 3);
		}
	}

	public static int[][] getResult() {
		return result;
	}

	public static void setResult(int result[][]) {
		KNNProcess.result = result;
	}

}
