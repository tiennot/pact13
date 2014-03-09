package Audio;

public class DetectionDePause {
	
	private double seuil=0.001;
	private double tPauseMini = 0.20; //duree de silence pour une pause au minimum
	private double [][] listedepause;
	private static int freqEch;
	
	public DetectionDePause(TableAudio audioFile){
		
		Analyse analyse = new Analyse(audioFile);
		double[] datadonne = analyse.getEnergy(); 
		int longueur=datadonne.length;
		freqEch=audioFile.getSampleRate();
		listedepause= new double[longueur][2];
		int max= analyse.getMaximum();
		int j=0;
		for (int i=(int)analyse.getDebut()*freqEch; i<(int) analyse.getFin()*freqEch; i++){
			
			
			
			if (Math.abs(datadonne[i])/datadonne[max] <seuil){ //condition pour que le point soit considere comme un silence
				
				
				
				int compteur=1;
				int debutpause=i; // mise en memoire de l'indice de depart
				   // incrementation d'un compteur
				while(datadonne[i+1]/datadonne[max]<seuil){
					compteur=compteur+1;
					i++;
					if (i==longueur-1){
						break;
					}
				}
				
				if(compteur>tPauseMini *freqEch){  // la pause est-elle significative ?
					listedepause[j][0]=(double)debutpause/freqEch;
					listedepause[j][1]=(double)compteur/freqEch;
					j=j+1;
				}
			}
			
		}
	}

	public double[][] getlistedepause(){
		return this.listedepause;
	}

}







