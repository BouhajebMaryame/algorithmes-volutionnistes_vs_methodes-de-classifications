package Kmeans;
// la fonction claculer est la sommme (somme (dist));

import DE.Individu;
import pso.Algodonn;

public class ProblemSet {
	public static final Algodonn don = new Algodonn();
	public static final double alpha  = 0.4;
	public static final double ERR_TOLERANCE = 1E-20; 

	public static double evaluate(Individu individu) {
		 int res[][]= new int [3][3];
		 double result = 0;
		 
		 for(int i=0;i<don.getIris().length;i++)
		 { 
			 double is[]= {don.getIris()[i][0],don.getIris()[i][1],don.getIris()[i][2],don.getIris()[i][3]};
			 double itr [][]= new double[3][4];
			     int min = min(is,individu.getInv()[0],individu.getInv()[1],individu.getInv()[2]);
			     if(min== don.getRes()[i])
					 res[min-1][min-1] +=1;
				 else 
				 res[don.getRes()[i]-1][min-1] +=1;
			     if(min == 1)
			     {
			    	    itr [0]= deplacement(is,individu.getInv()[0]);
				    	itr [1]= individu.getInv()[1];
				    	itr [2]= individu.getInv()[2];
			     }
			     else if(min ==2)
			     {
				    	itr [0]= individu.getInv()[0];
				    	itr [1]= deplacement(is,individu.getInv()[1]);
				    	itr [2]= individu.getInv()[2];
				     }
			     else
			        {
				    	itr [0]= individu.getInv()[0];
				    	itr [1]= individu.getInv()[1];
				    	itr [2]= deplacement(is,individu.getInv()[2]);
				     }
			     
			     individu.setInv(itr);
				
			 
		 }
		 result = 	150 - (res[0][0] +res[1][1]+res[2][2]);
		
		return result;
	}
	
	public static double [] deplacement(double donn[],double donn1[])
	{
		double [] result = new double[4];
		
		for(int i=0 ;i<4 ;i++)
		{
			result[i]=  donn1[i] + alpha*(donn[i] -donn1[i]);
		}
		return result;
	}
	public static int[][] Afficher(Individu individu)
	{
		double A [] = individu.getInv()[0]; 
		double B [] = individu.getInv()[1]; 
		double C [] = individu.getInv()[2];
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
