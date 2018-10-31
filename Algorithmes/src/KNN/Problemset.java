package KNN;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Problemset {
	public static final Algodonn don = new Algodonn();
	public static final double ERR_TOLERANCE = 1E-20; 
	private static int res[][];
	public static double evaluate(int k , Map<Individu, Integer> Population) {
		  res = new int [3][3];
	     double result = 0;
		 for(int i=0;i<don.getIris1().length;i++)
		 { 
			 double is[]= {don.getIris1()[i][0],don.getIris1()[i][1],don.getIris1()[i][2],don.getIris1()[i][3]};	
			Map<Double,Integer> Test = min(is,  Population);
			 int a=0;
			 int b=0;
			 int c=0;
			 
			int j=0;
			Iterator<Double> itr =  Test.keySet().iterator();
			while(itr.hasNext() && j<k)
			{
				double im=itr.next();
				//System.out.println(im+"  "+Test.get(im));
				int valeur = Test.get(im);
				if(valeur == 1)
					a++;
				else if(valeur==2)
					b++;
				else 
					c++;	
				j++;
			}
			if(c==a || b==a ||c==b)
			{
				int valeur = Test.get(itr.next());
				if(valeur == 1)
					a++;
				else if(valeur==2)
					b++;
				else 
					c++;	
			}
			int max= Math.max(a,b); 
			max=Math.max(max, c);
			if(max == a) max =1;  else if(max == b) max=2; else max =3;
			if(max == don.getRes()[i])
				 res[max-1][max-1] +=1;
				 else 
					 res[don.getRes()[i]-1][max-1] +=1;
			 
		 }
		result = 120 - (res[0][0] +res[1][1]+res[2][2]);
		
		return result;
	}
	public static int [][] Afficher()
	{
		return res;
	}
	 public static Map<Double, Integer> min(double donn[], Map<Individu, Integer> Population)
	 	{
		 Set<Individu> set = Population.keySet() ;
		 Iterator<Individu> iter = set.iterator();
		 
		 Map<Double, Integer> Test = new HashMap<Double,Integer>();
		 while(iter.hasNext())
			 {
			 	 Individu ind = iter.next(); 
				 Test.put(dis(donn,ind), Population.get(ind));
			 }
		 
		 Comparator<Double> keycomparator= new Comparator<Double>() 
		 {
			public int compare(Double d1, Double d2) {
				 return d1.compareTo(d2);
			}
		  };
		 Map<Double, Integer> result = new TreeMap<Double, Integer>(keycomparator);
		 result.putAll(Test);
		 return result;
	 	}
	 public static double dis(double donn1[],Individu ind)
		 {
			 double res=0;
			 for(int i=0;i<donn1.length;i++)
			 {
				 res+=Math.pow((donn1[i]-ind.getInv()[i]),2);
			 }
			 return Math.sqrt(res);
		 }
}
