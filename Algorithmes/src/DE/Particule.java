package DE;

public class Particule {
	private double fitnessValue;
	private Individu ind;
	
	public Particule() {
		super();
	}

	public Particule(Individu ind,double fitnessValue)
	{
		this.ind=ind;
		this.fitnessValue = fitnessValue;
	}
	
	public Individu getInd() {
		return ind;
	}


	public void setInd(Individu ind) {
		this.ind = ind;
	}


	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}


	public double getFitnessValue() {
		fitnessValue = ProblemSet.evaluate(ind);
		return fitnessValue;
	}
}
