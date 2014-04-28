package Classification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class LireEcrireTexte {
	
	public LireEcrireTexte(){}
	
	public double[][] recupererDonnees (String fileName) throws IOException {
		
		double[][]  rawData = new double[100][5];
		BufferedReader bf =null;
		try {
			FileReader fr = new FileReader(fileName);
			bf = new BufferedReader(fr);
			for (int i=0; i<100; i++){
				String temp = bf.readLine();
				for (int j=0; j<5;j++){
					int k = temp.indexOf(",");
					rawData[i][j]= Double.parseDouble(temp.substring(0,k));
					temp = temp.substring(k+1);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			bf.close();
		}
		return rawData;
	}
	
public double[] recupererExemple (String fileName, int l) throws IOException {
		
		double[][]  rawData = new double[60][20];
		double[] exemple = new double[20];
		BufferedReader bf =null;
		try {
			FileReader fr = new FileReader(fileName);
			bf = new BufferedReader(fr);
			for (int i=0; i<60; i++){
				String temp = bf.readLine();
				for (int j=0; j<20;j++){
					int k = temp.indexOf(",");
					rawData[i][j]= Double.parseDouble(temp.substring(0,k));
					temp = temp.substring(k+1);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			bf.close();
		}
		for (int i=0; i<19; i++){
			exemple[i]=rawData[l][i];
		}
		return exemple;
	}
}	