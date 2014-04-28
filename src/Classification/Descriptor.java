package Classification;

import java.util.ArrayList ;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.Math ;
import java.util.Scanner ;
import java.util.regex.MatchResult;

public class Descriptor
{
	private ArrayList<ArrayList<ArrayList<Double>>> rawData ;	// donn�e brutes extraites du fichier .txt, sa dimension est size x 20 x 4
	private int size ;											// nombre d'intervalles de temps
	private int height ;
	private int intraSample ;
	private ArrayList<ArrayList<Double>> anglesList ;			// liste des angles pour chaque instant
	
	private ArrayList<ArrayList<Double>> anglesMean ;
	private ArrayList<ArrayList<Double>> velocitiesMean ;
	private ArrayList<ArrayList<Double>> anglesVar ;
	private ArrayList<ArrayList<Double>> velocitiesVar ;
	
	private ArrayList<ArrayList<Double>> descriptorMatrix ;		// la matrice finale des descripteurs, d�pend de la dur�e d'int�gration
	
	public Descriptor()
	{
		
	}
	
	/* initialise le tableau rawData avec les donn�e du fichier .txt */
	public void loadKinectFile(String fileName)
	{
		rawData = new ArrayList<ArrayList<ArrayList<Double>>>() ;
		ArrayList<Double> raw1 =  new ArrayList<Double>() ;
		
		Scanner sc = null ;
		
		String s = null ;
		
		try {
			sc = new Scanner(new BufferedReader(new FileReader(fileName))) ;
			
			sc.useDelimiter(" |\\:|\\||\\n|\n|\n\r|\r\n");

			// assign locale as US to recognize double numbers in a string
		    sc.useLocale(Locale.US);
		     
		    // r�cup�re la liste des valeurs
		    while (sc.hasNext())
		    {
		    	// check if the scanner's next token is a double
		    	if(sc.hasNextDouble())
		    	{
		    		raw1.add(sc.nextDouble()) ;
		    	}
		    	sc.next() ;	
		    }
		    
		    // organise ces valeurs en tableau 3D : squelette->coordonn�es
		    
		    size = raw1.size() / 80 ;
		    
		    for(int i = 0 ; i < size ; i++)
		    {
		    	ArrayList<ArrayList<Double>> skeleton = new ArrayList<ArrayList<Double>>() ;
		    	for(int j = 0 ; j < 20 ; j++)
		    	{
		    		ArrayList<Double> quad = new ArrayList<Double>() ;
		    		quad.add(raw1.get(80 * i + 4 * j)) ;
		    		quad.add(raw1.get(80 * i + 4 * j + 1)) ;
		    		quad.add(raw1.get(80 * i + 4 * j + 2)) ;
		    		quad.add(raw1.get(80 * i + 4 * j + 3)) ;
		    		skeleton.add(quad) ;
		    	}
		    	rawData.add(skeleton) ;
		    }
			
		} catch (Exception e) {
			e.printStackTrace() ;
		} finally {
			try {
				sc.close() ;
			} catch (Exception e) {}
		}
		
		//size = rawData.size() ;
		
		/* CHECK VISUEL
		System.out.println("***AFFICHAGE DES DONNEES BRUTES (3D)***") ;
		printArray3(rawData) ;
		System.out.println("***FIN AFFICHAGE DES DONNEES BRUTES (3D)***") ;
		*/
	}
	
	public void printArray1(ArrayList<Double> l)
	{
		for(int i = 0 ; i < l.size() ; i++)
		{
			System.out.print(l.get(i) + " ") ;
		}
	}
	
	public void printArray2(ArrayList<ArrayList<Double>> l)
	{
		for(int i = 0 ; i < l.size() ; i++)
		{
			printArray1(l.get(i)) ;
			System.out.println() ;
		}
	}
	
	public void printArray3(ArrayList<ArrayList<ArrayList<Double>>> l)
	{
		for(int i = 0 ; i < l.size() ; i++)
		{
			System.out.println(i + ".") ;
			printArray2(l.get(i)) ;
		}
	}
	
	/* calcule l'angle A^B^C (en radians) */
	private Double getAngleFrom(ArrayList<Double> A, ArrayList<Double> B, ArrayList<Double> C)
	{
		double theta ;
		
		Double scalProd = ((A.get(0) - B.get(0))*(C.get(0) - B.get(0)) +
						   (A.get(1) - B.get(1))*(C.get(1) - B.get(1)) +
						   (A.get(2) - B.get(2))*(C.get(2) - B.get(2))) ;
		
		Double normBA = Math.sqrt(	(A.get(0) - B.get(0))*(A.get(0) - B.get(0)) +
									(A.get(1) - B.get(1))*(A.get(1) - B.get(1)) +
									(A.get(2) - B.get(2))*(A.get(2) - B.get(2)) ) ;
		
		Double normBC = Math.sqrt(	(C.get(0) - B.get(0))*(C.get(0) - B.get(0)) +
									(C.get(1) - B.get(1))*(C.get(1) - B.get(1)) +
									(C.get(2) - B.get(2))*(C.get(2) - B.get(2)) ) ;
		
		theta = Math.acos(scalProd / (normBA * normBC + 0.00000001)) ; // epsilon ajout� pour �viter les Nan en cas d'angle ind�finis.
		
		return theta ;
	}
	
	/* convertit le squelette � l'instant t en tableau de 16 angles */
	private ArrayList<Double> convertToAngles(ArrayList<ArrayList<Double>> skeleton)
	{
		ArrayList<Double> angles = new ArrayList<Double>() ;
		
		// instant correspondant (en nanoseconde)
		angles.add((skeleton.get(0)).get(3)) ;
		// coude � poignet � main gauche
		angles.add(getAngleFrom(skeleton.get(4), skeleton.get(10), skeleton.get(6))) ;
		// coude � poignet � main droit
		angles.add(getAngleFrom(skeleton.get(12), skeleton.get(18), skeleton.get(14))) ;
		// �paule � coude � poignet gauche
		angles.add(getAngleFrom(skeleton.get(9), skeleton.get(4), skeleton.get(10))) ;
		// �paule � coude � poignet droit
		angles.add(getAngleFrom(skeleton.get(17), skeleton.get(12), skeleton.get(18))) ;
		// cou � �paule � coude gauche
		angles.add(getAngleFrom(skeleton.get(1), skeleton.get(9), skeleton.get(4))) ;
		// cou � �paule � coude droit
		angles.add(getAngleFrom(skeleton.get(1), skeleton.get(17), skeleton.get(12))) ;
		// �paule � cou � t�te gauche
		angles.add(getAngleFrom(skeleton.get(9), skeleton.get(1), skeleton.get(2))) ;
		// �paule � cou � t�te droit
		angles.add(getAngleFrom(skeleton.get(17), skeleton.get(1), skeleton.get(2))) ;
		// colonne vert�brale � cou � t�te
		angles.add(getAngleFrom(skeleton.get(19), skeleton.get(1), skeleton.get(2))) ;
		// bassin � colonne vert�brale � cou
		angles.add(getAngleFrom(skeleton.get(0), skeleton.get(19), skeleton.get(1))) ;
		// bassin � hanche � genou gauche
		angles.add(getAngleFrom(skeleton.get(0), skeleton.get(7), skeleton.get(8))) ;
		// bassin � hanche � genou droit
		angles.add(getAngleFrom(skeleton.get(0), skeleton.get(15), skeleton.get(16))) ;
		// hanche � genou � cheville gauche
		angles.add(getAngleFrom(skeleton.get(7), skeleton.get(8), skeleton.get(3))) ;
		// hanche � genou � cheville droit
		angles.add(getAngleFrom(skeleton.get(15), skeleton.get(16), skeleton.get(11))) ;
		// genou � cheville � pied gauche
		angles.add(getAngleFrom(skeleton.get(8), skeleton.get(3), skeleton.get(5))) ;
		// genou � cheville � pied droit
		angles.add(getAngleFrom(skeleton.get(16), skeleton.get(11), skeleton.get(13))) ;

		
		return angles ;
	}
	
	/* somme vectorielle de deux listes, suppos�es de m�me longueurs */
	private ArrayList<Double> arraySum(ArrayList<Double> l1, ArrayList<Double> l2)
	{
		ArrayList<Double> s = new ArrayList<Double>() ;
		
		for(int i = 0 ; i < l1.size() ; i++)
		{
			s.add(l1.get(i) + l2.get(i)) ;
		}
		return s ;
	}
	
	/* multiplie vectoriellement une liste par un scalaire */
	private ArrayList<Double> arrayMult(ArrayList<Double> l, Double x)
	{
		ArrayList<Double> q = new ArrayList<Double>() ;
		
		for(int i = 0 ; i < l.size() ; i++)
		{
			q.add(l.get(i) * x) ;
		}
		return q ;
	}
	
	/* retourne la liste en valeur absolue */
	private ArrayList<Double> arrayAbs(ArrayList<Double> l)
	{
		ArrayList<Double> a = new ArrayList<Double>() ;
		
		for(int i = 0 ; i < l.size() ; i++)
		{
			a.add(Math.abs(l.get(i))) ;
		}
		return a ;
	}
	
	/* retourne le vecteur �cart quadratique */
	private ArrayList<Double> arrayQuad(ArrayList<Double> l1, ArrayList<Double> l2)
	{
		ArrayList<Double> q = new ArrayList<Double>() ;
		
		for(int i = 0 ; i < l1.size() ; i++)
		{
			q.add((l1.get(i) - l2.get(i)) * (l1.get(i) - l2.get(i))) ;
		}
		return q ;
	}
	
	// CALCULE LA MOYENNE DES ANGLES (1/4)
	private void calculateAnglesMean()
	{
		/* cr�ation de la matrice de moyenne des angles, hauteur height, largeur 16 + 1 */
		anglesMean = new ArrayList<ArrayList<Double>>() ;
		for(int i = 0 ; i < height ; i++)
		{
			/* cr�e un vecteur nul de dimension 16 + 1 (premi�re coordonn�e correspond au temps) */
			ArrayList<Double> zeroLine = new ArrayList<Double>() ;
			for(int j = 0 ; j < 17 ; j++)
			{
				zeroLine.add((double) 0) ;
			}
			anglesMean.add(zeroLine) ;
		}
		
		for(int i = 0 ; i < height ; i++)
		{
			for(int k = intraSample * i ; k < intraSample * (i + 1) ; k++)
			{
				anglesMean.set(i, arraySum(anglesMean.get(i), anglesList.get(k))) ;
			}
			anglesMean.set(i, arrayMult(anglesMean.get(i), 1/(double)intraSample)) ;
		}
	}
	
	// CALCULE LA MOYENNE DES VITESSES (2/4)
	private void calculateVelocitiesMean()
	{
		velocitiesMean = new ArrayList<ArrayList<Double>>() ;
		for(int i = 0 ; i < height ; i++)
		{
			ArrayList<Double> zeroLine = new ArrayList<Double>() ;
			for(int j = 0 ; j < 17 ; j++)
			{
				zeroLine.add((double) 0) ;
			}
			velocitiesMean.add(zeroLine) ;
		}
		
		for(int i = 0 ; i < height ; i++)
		{
			for(int k = intraSample * i ; k < intraSample * (i + 1) ; k++)
			{
				if(k + 1 < size) // pas d'effet de bord
				{
					velocitiesMean.set(i, arraySum(velocitiesMean.get(i), arrayAbs(arrayMult(arraySum(arrayMult(anglesList.get(k), -1.0), anglesList.get(k + 1)), 1.0/(anglesList.get(k + 1).get(0) - anglesList.get(k).get(0)))))) ;
				}
				// (si effet de bord il y a, on n'ajoute rien)
			}
			velocitiesMean.set(i, arrayMult(velocitiesMean.get(i), 1/(double)intraSample)) ;
		}
	}
	
	// CALCULE LA VARIANCE DES ANGLES (3/4)
	private void calculateAnglesVar()
	{
		anglesVar = new ArrayList<ArrayList<Double>>() ;
		for(int i = 0 ; i < height ; i++)
		{
			ArrayList<Double> zeroLine = new ArrayList<Double>() ;
			for(int j = 0 ; j < 17 ; j++)
			{
				zeroLine.add((double) 0) ;
			}
			anglesVar.add(zeroLine) ;
		}
		
		for(int i = 0 ; i < height ; i++)
		{
			for(int k = intraSample * i ; k < intraSample * (i + 1) ; k++)
			{
				anglesVar.set(i, arraySum(anglesVar.get(i), arrayQuad(anglesList.get(k), anglesMean.get(i)))) ;
			}
			anglesVar.set(i, arrayMult(anglesVar.get(i), 1/(double)intraSample)) ;
		}
	}
	
	// CALCULE LA VARIANCE DES VITESSES (4/4)
	private void calculateVelocitiesVar()
	{
		velocitiesVar = new ArrayList<ArrayList<Double>>() ;
		for(int i = 0 ; i < height ; i++)
		{
			ArrayList<Double> zeroLine = new ArrayList<Double>() ;
			for(int j = 0 ; j < 17 ; j++)
			{
				zeroLine.add((double) 0) ;
			}
			velocitiesVar.add(zeroLine) ;
		}
		
		for(int i = 0 ; i < height ; i++)
		{
			for(int k = intraSample * i ; k < intraSample * (i + 1) ; k++)
			{
				if(k + 1 < size)
				{
					velocitiesVar.set(i, arraySum(velocitiesVar.get(i), arrayQuad(arrayAbs(arrayMult(arraySum(arrayMult(anglesList.get(k + 1), -1.0), anglesList.get(k)), 1/(anglesList.get(k + 1).get(0) - anglesList.get(k).get(0)))), velocitiesMean.get(i)))) ;
				}
			}
			velocitiesVar.set(i, arrayMult(anglesVar.get(i), 1/(double)intraSample)) ;
		}
	}
	
	// CALCULE LA MATRICE FINALE (CONCATENATION DES QUATRES RESULTATS PRECEDENTS)
	private void calculateMatrix()
	{
		descriptorMatrix = new ArrayList<ArrayList<Double>>() ; 
		
		for(int i = 0 ; i < height ; i++)
		{
			ArrayList<Double> line = new ArrayList<Double>() ;
			for(int j = 0 ; j < 16 ; j++)
			{
				line.add(anglesMean.get(i).get(j + 1)) ;
			}
			
			for(int j = 0 ; j < 16 ; j++)
			{
				line.add(velocitiesMean.get(i).get(j + 1)) ;
			}
			
			for(int j = 0 ; j < 16 ; j++)
			{
				line.add(anglesVar.get(i).get(j + 1)) ;
			}
			
			for(int j = 0 ; j < 16 ; j++)
			{
				line.add(velocitiesVar.get(i).get(j + 1)) ;
			}
			
			descriptorMatrix.add(line) ;
		}	
	}
	
	// CALCULE LA MATRICE DES DESCRIPTEURS EN FONCTION DU NOMBRE D'ECHANTILLONS FINALS = HAUTEUR DE LA MATRICE)
	public void extract(int number_of_samples)
	{
		/** 1. calcul des descripteurs instantan�s **/
		
		anglesList = new ArrayList<ArrayList<Double>>() ;
		
		for(int i = 0 ; i < size ; i++)
		{
			anglesList.add(convertToAngles(rawData.get(i))) ;
		}
		
		/*
		System.out.println("***AFFICHAGE DE LA LISTE DES ANGLES (2D)***") ;
		printArray2(anglesList) ;
		System.out.println("***FIN AFFICHAGE DE LA LISTE DES ANGLES (2D)***") ;
		*/
		
		/** 2. calcul de la matrice des descripteurs en fonction du sous-�chantillonage **/
		
		if(number_of_samples > size)
			height = size ;
		else
			height = number_of_samples ; // (= hauteur de la matrice des descripteurs)
		intraSample = size / height ; // (= nombre d'�chantillons dans une fen�tre)
		
		calculateAnglesMean() ;
		calculateVelocitiesMean() ;
		calculateAnglesVar() ;
		calculateVelocitiesVar() ;
		
		// CHECK VISUEL
		/*
		System.out.println("***AFFICHAGE DU TABLEAU DE MOYENNE DES ANGLES (2D)***");
		printArray2(anglesMean) ;
		System.out.println("***FIN AFFICHAGE DU TABLEAU DE MOYENNE DES ANGLES (2D)***");
		
		System.out.println("***AFFICHAGE DU TABLEAU DE MOYENNE DES VITESSES (2D)***");
		printArray2(velocitiesMean) ;
		System.out.println("***FIN AFFICHAGE DU TABLEAU DE MOYENNE DES VITESSES (2D)***");
		
		System.out.println("***AFFICHAGE DU TABLEAU DE VARIANCE DES ANGLES (2D)***");
		printArray2(anglesVar) ;
		System.out.println("***FIN AFFICHAGE DU TABLEAU DE VARIANCE DES ANGLES (2D)***");
		
		System.out.println("***AFFICHAGE DU TABLEAU DE VARIANCE DES VITESSES (2D)***");
		printArray2(velocitiesVar) ;
		System.out.println("***FIN AFFICHAGE DU TABLEAU DE VARIANCE DES VITESSES (2D)***");
		// FIN CHECK VISUEL
		 */
		
		calculateMatrix() ;
	}
	
	// RECUPERE LA MATRICE EN ENTIER
	public double[][] getMatrix()
	{
		double[][] matrix = new double[height][64] ;
		
		for(int i = 0 ; i < height ; i++)
		{
			for(int j = 0 ; j < 64 ; j++)
			{
				matrix[i][j] = descriptorMatrix.get(i).get(j) ;
			}
		}
		return matrix ;
	}
	
	// RECUPERE LA HAUTEUR DE LA MATRICE
	public int getHeight()
	{
		return height ;
	}
	
	// RECUPERE LE NOMBRE BRUT D'ECHANTILLONS
	public int getSize()
	{
		return size ;
	}
}
