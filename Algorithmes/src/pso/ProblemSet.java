package pso;
// la fonction claculer est la sommme (somme (dist));

public class ProblemSet {

	public static final double VEL_LOW = -1;
	public static final double VEL_HIGH = 1;
	public static final Algodonn don = new Algodonn();
	public static final double ERR_TOLERANCE = 1E-20; 
	public static double evaluate(Location location) {
		 int res[][]= new int [3][3];
		double result = 0;
		double A [] = location.getLoc()[0]; 
		double B [] = location.getLoc()[1]; 
		double C [] = location.getLoc()[2];

			
		 for(int i=0;i<don.getIris().length;i++)
		 { 
			 double is[]= {don.getIris()[i][0],don.getIris()[i][1],don.getIris()[i][2],don.getIris()[i][3]};
			
			     int min = min(is,A,B,C);
				if(min== don.getRes()[i])
					 res[min-1][min-1] +=1;
				else 
				res[don.getRes()[i]-1][min-1] +=1;
//		result+=dis(is,A)+dis(is,B)+dis(is,C);
			 
		 }
		 result = 	150 - (res[0][0] +res[1][1]+res[2][2]);
		
		return result;
	}
	public static int[][] Afficher(Location location)
	{
		double A [] = location.getLoc()[0]; 
		double B [] = location.getLoc()[1]; 
		double C [] = location.getLoc()[2];
		 int res[][]= new int [3][3];
		 for(int i=0;i<don.getIris().length;i++)
		 { 
			 double is[]= {don.getIris()[i][0],don.getIris()[i][1],don.getIris()[i][2],don.getIris()[i][3]};
			
			     int min = min(is,A,B,C);
				 if(min== don.getRes()[i])
				 res[min-1][min-1] +=1;
				 else 
					 res[don.getRes()[i]-1][min-1] +=1;
			 
		 }
		return res;
	}
	 public static int min(double donn[],double donn1[],double donn2[],double donn3[])
	 {
		 double  min = Math.min(dis(donn,donn1), dis(donn,donn2)); 
		 		min=Math.min(min, dis(donn,donn3));
		 		
		 		if(min == dis(donn,donn1))
		 			return 1;
		 		else if(min == dis(donn,donn2))
		 			return 2;
		 		else return 3;
	 }
	 public static double dis(double donn1[],double donn2[])
	 {
		 double res=0;
		 for(int i=0;i<donn1.length;i++)
		 {
			 res+=Math.pow((donn1[i]-donn2[i]),2);
		 }
		 return Math.sqrt(res);
	 }
}
