package Classification;

import java.util.Hashtable;


public class Adaboost {
	
		private double[][] base = new double[40][20] ;
		private double[] annotations = new double[40] ;
		public static final int numIterationsclarinet =100;
		public static final int numIterationspiano =100;
		public static final int numIterationstrumpet =100;
		public static final int numIterationsviolin =100;
		public static final int numIterations = 100;
		// Initialise la base avec la liste des exemples, mais aussi avec le vecteur des annotations
		public Adaboost( double[][] base, double[] annotations2)
		{
			this.base = base ;
			this.annotations = annotations2 ;
		}
		
		
		public souche getBestWeakClassifier(double[] listWeight )
		{
			souche s = null;
			double errorMin = Double.MAX_VALUE;
			double leftsum = 0;
			double rightsum = 0;
			int classSup = 0;
			int classInf = 0;
			double error = 0;
			
			// Regarde sur les colonnes ou se trouve le meilleur classifieur
			for (int k= 0; k<base[0].length; k++)
			{
				double[] columnCoefficients = new double[base.length] ;
				// Prend la colonne
				for(int i=0; i<base.length; i++)
				{
					columnCoefficients[i] = base[i][k] ;
				}
				
				// Regarde � quel endroit de la colonne on a une meilleure s�paration
				for(int i=0; i< columnCoefficients.length; i++)
				{
					error = 0;
					for(int j=0; j< columnCoefficients.length; j++)
					{
						if(columnCoefficients[j] <= columnCoefficients[i])
						          leftsum += annotations[j]*listWeight[j];
					
						if(columnCoefficients[j] > columnCoefficients[i])
							rightsum += annotations[j]*listWeight[j];
					}
					for(int j=0; j< columnCoefficients.length; j++)
					{
						if(columnCoefficients[j] <= columnCoefficients[i] && (annotations[j]*leftsum)  <=0)
						          error += listWeight[j];
					
						if(columnCoefficients[j] > columnCoefficients[i] && (annotations[j]*rightsum) <= 0)
					              error += listWeight[j];
					}
					if( error < errorMin)
					{
						
						if(rightsum<0)
						{
							classSup = -1;
							classInf= 1;
						}
						else
						{
							classInf = -1;
							classSup= 1;	
						}		
						
						errorMin = error;
						s = new souche(columnCoefficients[i], classInf, classSup, k, error);		
					}	
				}		
			}
			return s;
		}

		public souche[] getStrongClassifier()
		{
			double totalWeight = 0 ;
			double errorMin;
			double[] weightOfExample = new double[base.length];
			souche[] strongClassifier = new souche[numIterations];
			
			double alpha = 0 ;
			double beta = 0 ;
			
			double w = ( (double) 1)/base.length;
			
			for (int i=0; i< base.length;i++)
			{
				weightOfExample[i] = w ;
			}
		
			for( int k=0; k < numIterations; k++)
			{
					
				// Regarde le quel des clasiffieur donne moins d'erreur et le s�lectionne
				strongClassifier[k] = getBestWeakClassifier(weightOfExample);
				
				errorMin = strongClassifier[k].getError();
				beta = (1-errorMin)/errorMin;
				alpha = (double) (0.5*Math.log(beta)) ;
				strongClassifier[k].setAlpha(alpha);
				for(int i = 0 ; i< base.length; i++)
				{
					if (/*annotations[i] == 1 && */strongClassifier[k].classify(base[i]) != annotations[i])
					{
						weightOfExample[i] =  weightOfExample[i]*beta ;
					}
				}
				
				totalWeight = 0;
				
				// Donne la somme des poids
				for (int i = 0; i<base.length; i++)
				{
					totalWeight = totalWeight + weightOfExample[i] ;
				}
				//Normalise le poids des exemples
				for (int i = 0; i<base.length; i++)
				{
					weightOfExample[i] =  weightOfExample[i]/totalWeight ;
				}
					
			}
			return strongClassifier;
	     }
	}



