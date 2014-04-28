package Classification;

import java.io.IOException;


public class Classifieurs {

	public Classifieurs () {}
	
	public souche[] avoirClassifieurBrasCroises(){
		
		double[][] brascroisesmatrice = new double[100][5];
		LireEcrireTexte brascroises = new LireEcrireTexte();
		try {
			brascroisesmatrice = brascroises.recupererDonnees("data/classif/brascroises.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		souche[] brascroisesada = new souche[100];
		for (int i = 0; i<100; i++){
			brascroisesada[i] = new souche (brascroisesmatrice[i][0], brascroisesmatrice[i][1], brascroisesmatrice[i][2], (int) brascroisesmatrice[i][3], brascroisesmatrice[i][4]);
		}
		return brascroisesada;
		
	}
	
	public souche[] avoirClassifieurJambesCroisees(){

		double[][] jambescroiseesmatrice = new double[100][5];
		LireEcrireTexte jambescroisees = new LireEcrireTexte();
		try {
			jambescroiseesmatrice = jambescroisees.recupererDonnees("data/classif/jambescroisees.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		souche[] jambescroiseesada = new souche[100];
		for (int i = 0; i<100; i++){
			jambescroiseesada[i] = new souche (jambescroiseesmatrice[i][0], jambescroiseesmatrice[i][1], jambescroiseesmatrice[i][2], (int) jambescroiseesmatrice[i][3], jambescroiseesmatrice[i][4]);
		}
		return jambescroiseesada;


	}
	
	public souche[] avoirClassifieurMainTete(){
		
		double[][] maintetematrice = new double[100][5];
		LireEcrireTexte maintete = new LireEcrireTexte();
		try {
			maintetematrice = maintete.recupererDonnees("data/classif/maintete.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		souche[] mainteteada = new souche[100];
		for (int i = 0; i<100; i++){
			mainteteada[i] = new souche (maintetematrice[i][0], maintetematrice[i][1], maintetematrice[i][2], (int) maintetematrice[i][3], maintetematrice[i][4]);
		}
		return mainteteada;
	}
	
	public souche[] avoirClassifieurMainPoche(){
		
		double[][] mainpochematrice = new double[100][5];
		LireEcrireTexte mainpoche = new LireEcrireTexte();
		try {
			mainpochematrice = mainpoche.recupererDonnees("data/classif/mainpoche.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		souche[] mainpocheada = new souche[100];
		for (int i = 0; i<100; i++){
			mainpocheada[i] = new souche (mainpochematrice[i][0], mainpochematrice[i][1], mainpochematrice[i][2], (int) mainpochematrice[i][3], mainpochematrice[i][4]);
		}
		return mainpocheada;
		
	}
	
}
