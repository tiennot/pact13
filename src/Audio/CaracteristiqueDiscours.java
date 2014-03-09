package Audio;

public class CaracteristiqueDiscours {
	
	private double moyennedepitch;
	private double ecarttypedepitch;
	private double longueurdiscours;
	private double tempsdesilence;
	
	public CaracteristiqueDiscours ( TableAudio audioFile){
		
		
		Analyse analyse = new Analyse(audioFile);
		Pitch S = new Pitch(audioFile);
		ecarttypedepitch=S.ecarttype();
		moyennedepitch=S.moyenne();
		DetectionDePause tempsPause = new DetectionDePause(audioFile);
		double[][] tabPause=tempsPause.getlistedepause();
		double u=0;
		for(int i=0; tabPause[i][1] !=0; i++){
			u=u+tabPause[i][1];
		}
		
		tempsdesilence=u;
		longueurdiscours= analyse.getFin() - analyse.getDebut();
		
	}
	
	@Override
	public String toString(){
		return ("Moyenne du pitch =" + moyennedepitch + "\n Ecart type du pitch=" + ecarttypedepitch +
				"\n Longueur du discours =" + longueurdiscours + "\n Temps des Silence =" + tempsdesilence +
						"\n");
	}
	
	
	//Getter
	
	public double getmoyennedepitch(){
		return moyennedepitch;
	}
	public double getecarttypedepitch(){
		return ecarttypedepitch;
	}
	public double getlongueurdiscours(){
		return longueurdiscours;
	}
	public double gettempsdesilence(){
		return tempsdesilence;
	}
	
	
	
	public double distance(CaracteristiqueDiscours discours){
		double S=0;
		
		double ecartType = this.getecarttypedepitch(), 
				ecartTypeDiscours = discours.getecarttypedepitch(),
				longueur = this.getlongueurdiscours(),
				longueurDiscours =discours.getlongueurdiscours(),
				moyennePitch = this.getmoyennedepitch(),
				moyennePitchDiscours = discours.getmoyennedepitch(),
				tempsSilence = this.gettempsdesilence(),
				tempsSilenceDiscours = discours.gettempsdesilence();
		
		double a=(ecartType-ecartTypeDiscours)*(ecartType-ecartTypeDiscours);
		double b=(longueur-longueurDiscours)*(longueur-longueurDiscours);
		double c=(moyennePitch - moyennePitchDiscours)*(moyennePitch - moyennePitchDiscours);
		double d=(tempsSilence-tempsSilenceDiscours)*(tempsSilence-tempsSilenceDiscours);
		
		a=2*a/((ecartType+ecartTypeDiscours)*(ecartType+ecartTypeDiscours));
		b=2*b/((longueur+longueurDiscours)*(longueur+longueurDiscours));
		c=2*c/((moyennePitch + moyennePitchDiscours)*(moyennePitch + moyennePitchDiscours));
		d=2*d/((tempsSilence+tempsSilenceDiscours)*(tempsSilence+tempsSilenceDiscours));
		
		S=a+b+c+d;
		return S;
	}

}
