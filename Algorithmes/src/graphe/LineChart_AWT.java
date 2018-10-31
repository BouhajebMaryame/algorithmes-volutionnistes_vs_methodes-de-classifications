package graphe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import DE.DEDriver;
import DE.DEProcess;
import KNN.KNNDriver;
import KNN.KNNProcess;
import Kmeans.KmeansProcess;
import Kmeans.KmenasDriver;
import pso.PSODriver;
import pso.PSOProcess;


public class LineChart_AWT extends ApplicationFrame {
	private static final long serialVersionUID = 1L;
	
	DEDriver d = new DEDriver();
	PSODriver p = new PSODriver();
	KmenasDriver k = new KmenasDriver();
	KNNDriver knn = new KNNDriver();
	JLabel lab ;
	JLabel lab1 ;
	JLabel lab2 ;
	JLabel lab3 ;
	JPanel panel = new JPanel();
	JPanel pan = new JPanel();
	JPanel pan1 = new JPanel();
	JPanel pan2 = new JPanel();
	JPanel pan3 = new JPanel();
	JTable jTable1 ;JTable jTable2;JTable jTable4;JTable jTable3;
   public LineChart_AWT( String applicationTitle , String chartTitle ) {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Iteration","best fitness",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
     
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      this.add(chartPanel);
      String[] taq={  "marque","mod�le", "homologation"}; 
      Object tabDE [][] = new Object[3][3];   
      Object tabKmeans [][] = new Object[3][3];
      Object tabPso [][] = new Object[3][3];
      Object tabKNN [][] = new Object[3][3];
      for(int i=0;i<DEProcess.getResult().length;i++)
      {
    	  for(int j= 0;j<DEProcess.getResult()[i].length;j++)
    	  {
    		  tabDE[i][j]=DEProcess.getResult()[i][j];
    		  tabKmeans[i][j]=KmeansProcess.getResult()[i][j] ;
    		  tabKNN[i][j]=KNNProcess.getResult()[i][j];
    		  tabPso[i][j]=PSOProcess.getResult()[i][j];
    	  }
     }
      DecimalFormat df = new DecimalFormat("0.00"); 
    lab = new JLabel("L'erreur obtenue  "+df.format((d.getFitnesIter()[99]*100)/150)+" %" );
    lab1 = new JLabel("L'erreur obtenue  "+df.format((p.getFitnesIter()[99]*100)/150)+" %" );
    lab2 = new JLabel("L'erreur obtenue  "+df.format((k.getFitnesIter()[99]*100)/150+2)	+" %" );
    lab3 = new JLabel("L'erreur obtenue  "+	df.format((knn.getFitnesIter()[13]*100)/120)+" %" );
       jTable1 = new JTable(tabDE,taq);
       jTable2 = new JTable(tabPso,taq);
       jTable3 = new JTable(tabKNN,taq);
       jTable4 = new JTable(tabKmeans,taq);
      pan.setBorder(BorderFactory.createTitledBorder("L�algorithme � �volution diff�rentielle (ED) "));
      pan1.setBorder(BorderFactory.createTitledBorder("L�algorithme d�optimisation par essaim de particules (P.S.O.)"));
      pan2.setBorder(BorderFactory.createTitledBorder("K-means"));
      pan3.setBorder(BorderFactory.createTitledBorder("  Les k plus proches voisins KNN "));
      pan.add(jTable1);
      pan.add(lab);
      pan1.add(jTable2);
      pan1.add(lab1);
      pan2.add(jTable4);
      pan2.add(lab2);
      pan3.add(jTable3);
      pan3.add(lab3);
      panel.setLayout(new GridLayout(4, 1));
      panel.add(pan);
      panel.add(pan1);
      panel.add(pan2);
      panel.add(pan3);

      this.add(panel,BorderLayout.EAST);
    
   }
   JTabbedPane tab = new JTabbedPane();
   
   private DefaultCategoryDataset createDataset( ) {
	   System.out.println("DE");
	   d.execute();
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      dataset.addValue( d.getFitnesIter()[0]   , "DE" , "1" );
      dataset.addValue( d.getFitnesIter()[10]  , "DE" , "10" );
      dataset.addValue( d.getFitnesIter()[30]  , "DE" ,  "30" );
      dataset.addValue( d.getFitnesIter()[50]  , "DE" , "50" );
      dataset.addValue( d.getFitnesIter()[70]  , "DE" , "70" );
      dataset.addValue( d.getFitnesIter()[99]  , "DE" , "99" );
      System.out.println("pso");
      p.execute();
      dataset.addValue( p.getFitnesIter()[0]  , "PSO" , "1" );
      dataset.addValue( p.getFitnesIter()[10] , "PSO" , "10" );
      dataset.addValue( p.getFitnesIter()[30] , "PSO" ,  "30" );
      dataset.addValue( p.getFitnesIter()[50] , "PSO" , "50" );
      dataset.addValue( p.getFitnesIter()[70] , "PSO" , "70" );
      dataset.addValue( p.getFitnesIter()[99] , "PSO" , "99" );
      System.out.println("kmeans");
      k.execute();
      dataset.addValue( k.getFitnesIter()[0]  , "Kmeans" , "1" );
      dataset.addValue( k.getFitnesIter()[10] , "Kmeans" , "10" );
      dataset.addValue( k.getFitnesIter()[30] , "Kmeans" ,  "30" );
      dataset.addValue( k.getFitnesIter()[50] , "Kmeans" , "50" );
      dataset.addValue( k.getFitnesIter()[70] , "Kmeans" , "70" );
      dataset.addValue( k.getFitnesIter()[99] , "Kmeans" , "99" );
      knn.execute();
      dataset.addValue( knn.getFitnesIter()[1]  , "KNN" , "1" );
      dataset.addValue( knn.getFitnesIter()[1]  , "KNN" , "10" );
      dataset.addValue( knn.getFitnesIter()[10] , "KNN" , "30" );
      dataset.addValue( knn.getFitnesIter()[10] , "KNN" ,  "50" );
      dataset.addValue( knn.getFitnesIter()[13] , "KNN" , "70" );
      dataset.addValue( knn.getFitnesIter()[13] , "KNN" , "90" );
      
      return dataset;
      
      
   }
   
   public static void main( String[ ] args ) {
      LineChart_AWT chart = new LineChart_AWT(
         "ED" ,
         "Etude comparative des algorithmes �volutionnaires et des m�thodes de classification");

      chart.pack( );
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }
}