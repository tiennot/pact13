package Classification;


public class souche {
	private double seuil; 
	private double classInf;
	private double classSup;
	private int position;
	private double error;
	private double alpha;
	
	
	public souche(double columnCoefficients, double classInf, double classSup, int position,
			double error) {
		super();
		this.seuil = columnCoefficients;
		this.classInf = classInf;
		this.classSup = classSup;
		this.position = position;
		this.error = error;
	}
	public double classify(double[] base)
	{
		
		double classOfExample = 0 ;
		
		if(base[position] > seuil)
		{
			classOfExample = classSup ;
		}
		if( base[position] <= seuil)
		{
			classOfExample = classInf;
		}
		return classOfExample;
		
	}
	
	public final double getSeuil() {
		return seuil;
	}
	public final void setSeuil(double seuil) {
		this.seuil = seuil;
	}

	public double getAlpha() {
		return alpha;
	}


	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}


	public final double getClassInf() {
		return classInf;
	}
	public final void setClassInf(int classInf) {
		this.classInf = classInf;
	}
	public final double getClassSup() {
		return classSup;
	}
	public final void setClassSup(int classSup) {
		this.classSup = classSup;
	}
	public final int getPosition() {
		return position;
	}
	public final void setPosition(int position) {
		this.position = position;
	}
	public final double getError() {
		return error;
	}
	public final void setError(double error) {
		this.error = error;
	}
	
	

}