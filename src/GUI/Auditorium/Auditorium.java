package GUI.Auditorium;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import GUI.General.Fenetre;
import GUI.General.Window;
import GUI.Outils.Const;
import GUI.Outils.Info;
import GUI.Outils.Text;

public class Auditorium extends Fenetre implements ActionListener{
	private Text stateText;
	private Text timeText = new Text("00:00");
	private EndButton endButton;
	private Timer timer;
	private int time = -3;
	
	public Auditorium(Window window){
		super(window);
		this.setLayout(new BorderLayout());
		this.stateText = new Text("");
		this.add(this.stateText, BorderLayout.CENTER);
		this.add(this.timeText, BorderLayout.NORTH);
		this.timeText.setFont(Const.font(20));
		this.endButton = new EndButton(this);
		this.add(this.endButton, BorderLayout.SOUTH);
	}
	
	public void startReccording(){
		this.time = -3; 
		this.timeText.setText("00:00");
		this.timer = new Timer(1000, this);
		this.timer.start();
	}
	
	public void stopReccording(){
		  this.timer.stop();
		  this.stateText.setText("Traitement des résultats...");
		  this.stateText.setFont(Const.font(28));
		  Info.echo("ICI => Appel de la fonction stop()");
		  
	}
	
	public void actionPerformed(ActionEvent evt){
		if(this.time>0){
			int minutes = (int) Math.floor(this.time/60);
			int secondes = this.time - minutes*60;
			if(secondes<10) this.timeText.setText("0"+minutes+":0"+secondes);
			else this.timeText.setText("0"+minutes+":"+secondes);
			
		}else if(this.time==0){
				this.stateText.setText("Enregistrement en cours...");
				this.stateText.setFont(Const.font(28));
				Info.echo("ICI => Appel de la fonction start()");
		}else{
			if(this.time==-3){
				this.stateText.setText("3");
				this.stateText.setFont(Const.font(150));
			}
			if(this.time==-2){
				this.stateText.setText("2");
				this.stateText.setFont(Const.font(200));
			}
			if(this.time==-1){
				this.stateText.setText("1");
				this.stateText.setFont(Const.font(250));
			}
		}
		this.time++;
	}
}
