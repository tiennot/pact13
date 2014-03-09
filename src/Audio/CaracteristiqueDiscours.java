package Audio;

import java.io.Serializable;

public class CaracteristiqueDiscours implements Serializable {
	
	private int numeroDiscours;
	private static final long serialVersionUID = -5473442237557119527L;
	private double moyennedepitch;
	private double ecarttypedepitch;
	private double longueurdiscours;
	private double tempsdesilence;

	
	public CaracteristiqueDiscours ( TableAudio audioFile, int numeroDiscours){
		
		this.numeroDiscours = numeroDiscours;
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
				"\n Longueur du discours =" + longueurdiscours + "\n Temps des silences =" + tempsdesilence +
						"\n");
	}
	
	
	
	
	
	//Getter
	public int getNumeroDiscours(){
		return this.numeroDiscours;
	}
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
	public void setNumerodiscours(int i){
		this.numeroDiscours= i;
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
	
	public void appréciation(){
		
		if (this.getNumeroDiscours()==1){
			if (this.longueurdiscours<11){
				System.out.println("Votre débit est élevé");
			}
			else if (this.longueurdiscours<15 && this.longueurdiscours>11){
				System.out.println("Vote débit est correct");
			}
			else {
				System.out.println("Votre débit est faible");
			}
			
			if (this.ecarttypedepitch<40){
				System.out.println("Votre discours est monotone");
			}
			else if (this.ecarttypedepitch>40 && this.ecarttypedepitch<70){
				System.out.println("Présence d'intonations, continuez");
			}
			else {
				System.out.println("Vous avez réussi à mettre beaucoup d'intonations dans votre discours, attention à ne pas trop en faire non plus");
			}
			if (this.tempsdesilence < 1.8){
				System.out.println("Vous marquez peu de pauses, pensez à marquer vos respirations");
			}
			else if (this.tempsdesilence >2 && this.tempsdesilence<3){
				System.out.println("Votre discours est ponctué par des pauses, continuez");
			}
			else{
				System.out.println("Votre discours est posé, mais attention à ne pas manquer de dynamisme");
			}
		}
		if (this.getNumeroDiscours()==2){
			if (this.longueurdiscours<15){
				System.out.println("Votre débit est élevé");
			}
			else if (this.longueurdiscours<18 && this.longueurdiscours>15){
				System.out.println("Vote débit est correct");
			}
			else {
				System.out.println("Votre débit est faible");
			}
			
			if (this.ecarttypedepitch<45){
				System.out.println("Votre discours est monotone");
			}
			else if (this.ecarttypedepitch>45 && this.ecarttypedepitch<75){
				System.out.println("Présence d'intonations, continuez");
			}
			else {
				System.out.println("Vous avez réussi à mettre beaucoup d'intonations dans votre discours, attention à ne pas trop en faire non plus");
			}
			if (this.tempsdesilence < 3.5){
				System.out.println("Vous marquez peu de pauses, pensez à marquer vos respirations");
			}
			else if (this.tempsdesilence >3.5 && this.tempsdesilence<5.5){
				System.out.println("Votre discours est ponctué par des pauses, continuez");
			}
			else{
				System.out.println("Votre discours est posé, mais attention à ne pas manquer de dynamisme");
			}
		}
		if (this.getNumeroDiscours()==3){
			if (this.longueurdiscours<15){
				System.out.println("Votre débit est élevé");
			}
			else if (this.longueurdiscours<15 && this.longueurdiscours>19){
				System.out.println("Vote débit est correct");
			}
			else {
				System.out.println("Votre débit est faible");
			}
			
			if (this.ecarttypedepitch<46){
				System.out.println("Votre discours est monotone");
			}
			else if (this.ecarttypedepitch>46 && this.ecarttypedepitch<60){
				System.out.println("Présence d'intonations, continuez");
			}
			else {
				System.out.println("Vous avez réussi à mettre beaucoup d'intonations dans votre discours, attention à ne pas trop en faire non plus");
			}
			if (this.tempsdesilence < 5.5){
				System.out.println("Vous marquez peu de pauses, pensez à marquer vos respirations");
			}
			else if (this.tempsdesilence >5.5 && this.tempsdesilence<9){
				System.out.println("Votre discours est ponctué par des pauses, continuez");
			}
			else{
				System.out.println("Votre discours est posé, mais attention à ne pas manquer de dynamisme");
			}
		}
		if (this.getNumeroDiscours()==3){
			if (this.longueurdiscours<13.5){
				System.out.println("Votre débit est élevé");
			}
			else if (this.longueurdiscours<13.5 && this.longueurdiscours>16){
				System.out.println("Vote débit est correct");
			}
			else {
				System.out.println("Votre débit est faible");
			}
			
			if (this.ecarttypedepitch<54){
				System.out.println("Votre discours est monotone");
			}
			else if (this.ecarttypedepitch>54 && this.ecarttypedepitch<75){
				System.out.println("Présence d'intonations, continuez");
			}
			else {
				System.out.println("Vous avez réussi à mettre beaucoup d'intonations dans votre discours, attention à ne pas trop en faire non plus");
			}
			if (this.tempsdesilence < 4){
				System.out.println("Vous marquez peu de pauses, pensez à marquer vos respirations");
			}
			else if (this.tempsdesilence >4 && this.tempsdesilence<6){
				System.out.println("Votre discours est ponctué par des pauses, continuez");
			}
			else{
				System.out.println("Votre discours est posé, mais attention à ne pas manquer de dynamisme");
			}
		}
	}
	
	

}
