package Classification;

import java.io.IOException;
import java.util.ArrayList;


public class Classif {
	public static souche[] brascroises;
	public static souche[] jambescroisees;
	public static souche[] maintete;
	public static souche[] mainpoche;

	public static void Init() {
		Classifieurs classifieurs = new Classifieurs();
		brascroises = classifieurs.avoirClassifieurBrasCroises();
		jambescroisees = classifieurs.avoirClassifieurJambesCroisees();
		maintete = classifieurs.avoirClassifieurMainTete();
		mainpoche = classifieurs.avoirClassifieurMainPoche(); //a faire faire au logiciel dès le lancement du programme	
	}
	
	public static String analyse(){
		String toReturn = "";
		//le reste se fait à chaque analyse, à la fin de l'enregistrement
		int numbrascroises = 0;
		int numjambescroisees = 0;
		int nummaintete = 0;
		int nummainpoche = 0;
		int l = 0;
		double etiquette = 300;
		double etiquettebrascroises = 0;
		double etiquettejambescroisees = 0;
		double etiquettemaintete = 0;
		double etiquettemainpoche = 0;
		int hauteur_de_la_matrice = 21;
		
		
		double[][] matrix = new double [hauteur_de_la_matrice][64];
		double [] matrice = new double [64];
		Descriptor descriptor = new Descriptor();
		descriptor.loadKinectFile("data/classif/blabla.txt");
		descriptor.extract(hauteur_de_la_matrice);
		matrix = descriptor.getMatrix();
		
		for (int j = 0; j<3; j++){
			for (int i = 0; i<64 ; i++){
				matrice[i] = matrix[j][i];
				for (int k = 0; k<100 ; k++){
					etiquettebrascroises = etiquettebrascroises + brascroises[k].classify(matrice);
					etiquettejambescroisees = etiquettejambescroisees + jambescroisees[k].classify(matrice);
					etiquettemaintete = etiquettemaintete + maintete[k].classify(matrice);
					etiquettemainpoche = etiquettemainpoche + mainpoche[k].classify(matrice);
				}
			}
			
		}
		if (etiquettemaintete >= etiquette){
			etiquette = etiquettemaintete;
			l=1;
		}
		if (etiquettebrascroises >= etiquette){
			etiquette = etiquettebrascroises;
			l=2;
		}
		if (etiquettejambescroisees >= etiquette){
			etiquette = etiquettejambescroisees;
			l=3;
		}
		if (etiquettemainpoche >= etiquette){
			etiquette = etiquettemainpoche;
			l=4;
		}
		if (l==1) nummaintete++;
		else if (l==2) numbrascroises++;
		else if (l==3) numjambescroisees++;
		else if (l==4) nummainpoche++;
		etiquette = 300;
		for (int j = 3; j<6; j++){
			for (int i = 0; i<64 ; i++){
				matrice[i] = matrix[j][i];
				for (int k = 0; k<100 ; k++){
					etiquettebrascroises = etiquettebrascroises + brascroises[k].classify(matrice);
					etiquettejambescroisees = etiquettejambescroisees + jambescroisees[k].classify(matrice);
					etiquettemaintete = etiquettemaintete + maintete[k].classify(matrice);
					etiquettemainpoche = etiquettemainpoche + mainpoche[k].classify(matrice);
				}
			}
			
		}
		if (etiquettemaintete >= etiquette){
			etiquette = etiquettemaintete;
			l=1;
		}
		if (etiquettebrascroises >= etiquette){
			etiquette = etiquettebrascroises;
			l=2;
		}
		if (etiquettejambescroisees >= etiquette){
			etiquette = etiquettejambescroisees;
			l=3;
		}
		if (etiquettemainpoche >= etiquette){
			etiquette = etiquettemainpoche;
			l=4;
		}
		if (l==1) nummaintete++;
		else if (l==2) numbrascroises++;
		else if (l==3) numjambescroisees++;
		else if (l==4) nummainpoche++;
		etiquette = 300;
		for (int j = 6; j<9; j++){
			for (int i = 0; i<64 ; i++){
				matrice[i] = matrix[j][i];
				for (int k = 0; k<100 ; k++){
					etiquettebrascroises = etiquettebrascroises + brascroises[k].classify(matrice);
					etiquettejambescroisees = etiquettejambescroisees + jambescroisees[k].classify(matrice);
					etiquettemaintete = etiquettemaintete + maintete[k].classify(matrice);
					etiquettemainpoche = etiquettemainpoche + mainpoche[k].classify(matrice);
				}
			}
			
		}
		if (etiquettemaintete >= etiquette){
			etiquette = etiquettemaintete;
			l=1;
		}
		if (etiquettebrascroises >= etiquette){
			etiquette = etiquettebrascroises;
			l=2;
		}
		if (etiquettejambescroisees >= etiquette){
			etiquette = etiquettejambescroisees;
			l=3;
		}
		if (etiquettemainpoche >= etiquette){
			etiquette = etiquettemainpoche;
			l=4;
		}
		if (l==1) nummaintete++;
		else if (l==2) numbrascroises++;
		else if (l==3) numjambescroisees++;
		else if (l==4) nummainpoche++;
		etiquette = 300;
		for (int j = 9; j<12; j++){
			for (int i = 0; i<64 ; i++){
				matrice[i] = matrix[j][i];
				for (int k = 0; k<100 ; k++){
					etiquettebrascroises = etiquettebrascroises + brascroises[k].classify(matrice);
					etiquettejambescroisees = etiquettejambescroisees + jambescroisees[k].classify(matrice);
					etiquettemaintete = etiquettemaintete + maintete[k].classify(matrice);
					etiquettemainpoche = etiquettemainpoche + mainpoche[k].classify(matrice);
				}
			}
			
		}
		if (etiquettemaintete >= etiquette){
			etiquette = etiquettemaintete;
			l=1;
		}
		if (etiquettebrascroises >= etiquette){
			etiquette = etiquettebrascroises;
			l=2;
		}
		if (etiquettejambescroisees >= etiquette){
			etiquette = etiquettejambescroisees;
			l=3;
		}
		if (etiquettemainpoche >= etiquette){
			etiquette = etiquettemainpoche;
			l=4;
		}
		if (l==1) nummaintete++;
		else if (l==2) numbrascroises++;
		else if (l==3) numjambescroisees++;
		else if (l==4) nummainpoche++;
		etiquette = 300;
		for (int j = 12; j<15; j++){
			for (int i = 0; i<64 ; i++){
				matrice[i] = matrix[j][i];
				for (int k = 0; k<100 ; k++){
					etiquettebrascroises = etiquettebrascroises + brascroises[k].classify(matrice);
					etiquettejambescroisees = etiquettejambescroisees + jambescroisees[k].classify(matrice);
					etiquettemaintete = etiquettemaintete + maintete[k].classify(matrice);
					etiquettemainpoche = etiquettemainpoche + mainpoche[k].classify(matrice);
				}
			}
			
		}
		if (etiquettemaintete >= etiquette){
			etiquette = etiquettemaintete;
			l=1;
		}
		if (etiquettebrascroises >= etiquette){
			etiquette = etiquettebrascroises;
			l=2;
		}
		if (etiquettejambescroisees >= etiquette){
			etiquette = etiquettejambescroisees;
			l=3;
		}
		if (etiquettemainpoche >= etiquette){
			etiquette = etiquettemainpoche;
			l=4;
		}
		if (l==1) nummaintete++;
		else if (l==2) numbrascroises++;
		else if (l==3) numjambescroisees++;
		else if (l==4) nummainpoche++;
		etiquette = 300;
		for (int j = 12; j<15; j++){
			for (int i = 0; i<64 ; i++){
				matrice[i] = matrix[j][i];
				for (int k = 0; k<100 ; k++){
					etiquettebrascroises = etiquettebrascroises + brascroises[k].classify(matrice);
					etiquettejambescroisees = etiquettejambescroisees + jambescroisees[k].classify(matrice);
					etiquettemaintete = etiquettemaintete + maintete[k].classify(matrice);
					etiquettemainpoche = etiquettemainpoche + mainpoche[k].classify(matrice);
				}
			}
			
		}
		if (etiquettemaintete >= etiquette){
			etiquette = etiquettemaintete;
			l=1;
		}
		if (etiquettebrascroises >= etiquette){
			etiquette = etiquettebrascroises;
			l=2;
		}
		if (etiquettejambescroisees >= etiquette){
			etiquette = etiquettejambescroisees;
			l=3;
		}
		if (etiquettemainpoche >= etiquette){
			etiquette = etiquettemainpoche;
			l=4;
		}
		if (l==1) nummaintete++;
		else if (l==2) numbrascroises++;
		else if (l==3) numjambescroisees++;
		else if (l==4) nummainpoche++;
		etiquette = 300;
		for (int j = 15; j<18; j++){
			for (int i = 0; i<64 ; i++){
				matrice[i] = matrix[j][i];
				for (int k = 0; k<100 ; k++){
					etiquettebrascroises = etiquettebrascroises + brascroises[k].classify(matrice);
					etiquettejambescroisees = etiquettejambescroisees + jambescroisees[k].classify(matrice);
					etiquettemaintete = etiquettemaintete + maintete[k].classify(matrice);
					etiquettemainpoche = etiquettemainpoche + mainpoche[k].classify(matrice);
				}
			}
			
		}
		if (etiquettemaintete >= etiquette){
			etiquette = etiquettemaintete;
			l=1;
		}
		if (etiquettebrascroises >= etiquette){
			etiquette = etiquettebrascroises;
			l=2;
		}
		if (etiquettejambescroisees >= etiquette){
			etiquette = etiquettejambescroisees;
			l=3;
		}
		if (etiquettemainpoche >= etiquette){
			etiquette = etiquettemainpoche;
			l=4;
		}
		if (l==1) nummaintete++;
		else if (l==2) numbrascroises++;
		else if (l==3) numjambescroisees++;
		else if (l==4) nummainpoche++;
		etiquette = 300;
		for (int j = 18; j<21; j++){
			for (int i = 0; i<64 ; i++){
				matrice[i] = matrix[j][i];
				for (int k = 0; k<100 ; k++){
					etiquettebrascroises = etiquettebrascroises + brascroises[k].classify(matrice);
					etiquettejambescroisees = etiquettejambescroisees + jambescroisees[k].classify(matrice);
					etiquettemaintete = etiquettemaintete + maintete[k].classify(matrice);
					etiquettemainpoche = etiquettemainpoche + mainpoche[k].classify(matrice);
				}
			}
			
		}
		if (etiquettemaintete >= etiquette){
			etiquette = etiquettemaintete;
			l=1;
		}
		if (etiquettebrascroises >= etiquette){
			etiquette = etiquettebrascroises;
			l=2;
		}
		if (etiquettejambescroisees >= etiquette){
			etiquette = etiquettejambescroisees;
			l=3;
		}
		if (etiquettemainpoche >= etiquette){
			etiquette = etiquettemainpoche;
			l=4;
		}
		
		
		if(nummaintete !=0){
			toReturn += "Vous avez porté votre main à la tête " + nummaintete + " fois";
		}
		if (numbrascroises !=0){
			toReturn +=  "Vous avez croisé les bras " + numbrascroises + " fois";
		}
		if (numjambescroisees !=0){
			toReturn += "Vous avez croisé vos jambes " + numjambescroisees + " fois";
		}
		if (nummainpoche !=0){
			toReturn += "Vous avez porté vos mains à vos poches " + nummainpoche + " fois";
		}
		if (nummaintete !=0 && numbrascroises !=0 && numjambescroisees !=0 && nummainpoche != 0){
			toReturn += "Bravo, nous n'avons relevé aucun mouvement qui soit venu perturber votre discours. Bravo !";
		}
		
	
		return toReturn;

	}

}
