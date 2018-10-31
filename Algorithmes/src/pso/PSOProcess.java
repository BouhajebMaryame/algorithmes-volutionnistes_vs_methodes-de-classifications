package pso;

import java.util.Random;
import java.util.Vector;
public class PSOProcess implements PSOConstants {
	
	private Vector<Particle> swarm = new Vector<Particle>();
	private double[] pBest = new double[SWARM_SIZE];
	private Vector<Location> pBestLocation = new Vector<Location>();
	private double gBest;
	private Location gBestLocation;
	private double[] fitnessValueList = new double[SWARM_SIZE];
	private double[] fitnessIterarion = new double[MAX_ITERATION];
	private static int[][] result ;
	public double[] getFitnessIterarion() {
		return fitnessIterarion;
	}
	Random generator = new Random();
	
	public void execute() {
		initializeSwarm();
		updateFitnessList();
		
		for(int i=0; i<SWARM_SIZE; i++) {
			pBest[i] = fitnessValueList[i];
			pBestLocation.add(swarm.get(i).getLocation());
		}
		
		int t = 0;
		double w;
		double err = 9999;
		
		while(t < MAX_ITERATION && err > ProblemSet.ERR_TOLERANCE) {
			// step 1 - update pBest
			for(int i=0; i<SWARM_SIZE; i++) {
				if(fitnessValueList[i] < pBest[i]) {
					pBest[i] = fitnessValueList[i];
					pBestLocation.set(i, swarm.get(i).getLocation());
				}
			}
			
			// step 2 - update gBest
			int bestParticleIndex = PSOUtility.getMinPos(fitnessValueList);
			if(t == 0 || fitnessValueList[bestParticleIndex] < gBest) {
				gBest = fitnessValueList[bestParticleIndex];
				gBestLocation = swarm.get(bestParticleIndex).getLocation();
			}
			fitnessIterarion[t]=swarm.get(bestParticleIndex).getFitnessValue();
			w = W_UPPERBOUND - (((double) t) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);
			
			for(int i=0; i<SWARM_SIZE; i++) {
				double r1 = generator.nextDouble();
				double r2 = generator.nextDouble();
				
				Particle p = swarm.get(i);
				
				// step 3 - update velocity
				double[][]newVel = new double[PROBLEM_DIMENSION][NB_VAR];
				double[][]newLoc = new double[PROBLEM_DIMENSION][NB_VAR];
				for(int k=0;k<PROBLEM_DIMENSION ;k++)
				{
					for(int j=0;j<NB_VAR ;j++)
					{
					   newVel[k][j]=(w * p.getVelocity().getPos()[k][j]) + 
								(r1 * C1) * (pBestLocation.get(i).getLoc()[k][j] - p.getLocation().getLoc()[k][j]) +
								(r2 * C2) * (gBestLocation.getLoc()[k][j] - p.getLocation().getLoc()[k][j]);
					   newLoc[k][j] = p.getLocation().getLoc()[k][j] + newVel[k][j];
						
					}
					
				}
				Velocity vel = new Velocity(newVel);
				p.setVelocity(vel);
				
				// step 4 - update location
				Location loc = new Location(newLoc);
				p.setLocation(loc);
			}
			
			err = ProblemSet.evaluate(gBestLocation) - 0; // minimizing the functions means it's getting closer to 0
			
			
			System.out.println("ITERAT ION " + t + ": ");
			System.out.println("     Best A: " + gBestLocation.getLoc()[0][0] +" "+ gBestLocation.getLoc()[0][1]+" "+gBestLocation.getLoc()[0][2]+" "+ gBestLocation.getLoc()[0][3]);
			System.out.println("     Best B: " + gBestLocation.getLoc()[1][0] +" "+ gBestLocation.getLoc()[1][1]+" "+gBestLocation.getLoc()[1][2]+" "+ gBestLocation.getLoc()[1][3]);
			System.out.println("     Best C: " + gBestLocation.getLoc()[2][0] +" "+ gBestLocation.getLoc()[2][1]+" "+gBestLocation.getLoc()[2][2]+" "+ gBestLocation.getLoc()[2][3]);
			System.out.println("     Value: " + ProblemSet.evaluate(gBestLocation));
			
			t++;
			updateFitnessList();
		}
		
		System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
		System.out.println("     Best A: " + gBestLocation.getLoc()[0][0] +" "+ gBestLocation.getLoc()[0][1]+" "+gBestLocation.getLoc()[0][2]+" "+ gBestLocation.getLoc()[0][3]);
		System.out.println("     Best B: " + gBestLocation.getLoc()[1][0] +" "+ gBestLocation.getLoc()[1][1]+" "+gBestLocation.getLoc()[1][2]+" "+ gBestLocation.getLoc()[1][3]);
		System.out.println("     Best C: " + gBestLocation.getLoc()[2][0] +" "+ gBestLocation.getLoc()[2][1]+" "+gBestLocation.getLoc()[2][2]+" "+ gBestLocation.getLoc()[2][3]);
		setResult(ProblemSet.Afficher(gBestLocation));;
		 
		 
		
	}

	
	public void initializeSwarm() {
		Particle p;
		for(int i=0; i<SWARM_SIZE; i++) {
			p = new Particle();
			
			// randomize location inside a space defined in Problem Set
			double[][] loc = new double[PROBLEM_DIMENSION][NB_VAR];
			double[][] vel = new double[PROBLEM_DIMENSION][NB_VAR];
			for(int k=0;k<PROBLEM_DIMENSION ;k++)
			{
				for(int j=0;j<NB_VAR ;j++)
				{
					loc[k][j]=ProblemSet.don.mix[k][j][0]+ generator.nextDouble() * (ProblemSet.don.mix[k][j][1] - ProblemSet.don.mix[k][j][0]);
					vel[k][j] = ProblemSet.VEL_LOW + generator.nextDouble() * (ProblemSet.VEL_HIGH - ProblemSet.VEL_LOW);
					
				}
				
			}
			Location location = new Location(loc);
			Velocity velocity = new Velocity(vel);
			
			p.setLocation(location);
			p.setVelocity(velocity);
			swarm.add(p);
		}
	}
	
	   public void updateFitnessList() {
	    	for(int i=0; i<SWARM_SIZE; i++) {
			fitnessValueList[i] = swarm.get(i).getFitnessValue();
	    	}
	}


	public static int [][] getResult() {
		return result;
	}


	public static void setResult(int [][] result) {
		PSOProcess.result = result;
	}
	
	
}
