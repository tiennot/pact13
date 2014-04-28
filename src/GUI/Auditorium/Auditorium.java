package GUI.Auditorium;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Audio.CaracteristiqueDiscours;
import Audio.RecordAudio;
import Audio.RecordThread;
import Audio.TableAudio;
import GUI.General.Fenetre;
import GUI.General.Window;
import GUI.Outils.Const;
import GUI.Outils.Info;
import GUI.Outils.KinectLauncher;
import GUI.Outils.Text;
import GUI.Utilisateurs.UtilisateursObj;

public class Auditorium extends Fenetre implements ActionListener{
	// Parent window
	private Window window;
	// Thread
	private RecordThread recordThread;
	private Text stateText;
	private Text timeText = new Text("00:00");
	private EndButton endButton;
	private BackButton backButton;
	private JPanel centerContainer = new JPanel();
	private Timer timer;
	private int time = -3;
	// Static thing to know what speech is currently played
	public static int levelId = 1;
	// Kinect thing
	private KinectLauncher kinectLauncher = new KinectLauncher("blablba.txt");

	public Auditorium(Window window){
		super(window); this.window = window;
		this.setLayout(new BorderLayout());
		this.stateText = new Text("");
		this.add(this.stateText, BorderLayout.CENTER);
		this.add(this.timeText, BorderLayout.NORTH);
		this.timeText.setFont(Const.font(20));
		this.endButton = new EndButton(this);
		this.backButton = new BackButton(this, window);
		this.add(this.endButton, BorderLayout.SOUTH);
		this.centerContainer.setOpaque(false);
		this.centerContainer.setLayout(new BoxLayout(this.centerContainer, BoxLayout.PAGE_AXIS));
	}

	public void startReccording(){
		// Starts the kinect
		this.kinectLauncher.startRecording();
		
		System.out.println("Niveau choisi : "+this.levelId);
		this.time = -3; 
		this.timeText.setText("00:00");
		this.timer = new Timer(1000, this);
		this.timer.start();
		this.add(this.endButton, BorderLayout.SOUTH);
		this.add(this.stateText, BorderLayout.CENTER);
		this.stateText.setVisible(true);
		this.centerContainer.setVisible(false);
		this.backButton.setVisible(false);
		this.endButton.setVisible(true);
	}

	public void stopReccording(){
		  // Starts the kinect
		  this.kinectLauncher.stopRecording();
		  
		  this.timer.stop();
		  this.stateText.setText("Traitement des resultats...");
		  this.stateText.setFont(Const.font(28));
		  // Stops the thread
		  this.recordThread.stopRecord();
		  this.recordThread.interrupt();
		  //
		  this.add(this.backButton, BorderLayout.SOUTH);
		  this.backButton.setVisible(true);
		  this.endButton.setVisible(false);
		  this.stateText.setVisible(false);
		  // Results
		  this.centerContainer.setVisible(true);
		  this.centerContainer.removeAll();
		  this.add(this.centerContainer, BorderLayout.CENTER);
		  this.centerContainer.add(new Text("Appreciation du coach :"));
		  CaracteristiqueDiscours cd =new CaracteristiqueDiscours( new TableAudio("data/audio"),1);
		  Text appreciation = new Text("<html><p>"+cd.appreciation()+"</p></html>");
		  appreciation.setFont(Const.font(18));
		  this.centerContainer.add(appreciation);
		  // Updates current user data
		  Window.USER().setNbparties(Window.USER().getNbparties()+1);
		  Window.USERS.writeToFile();		  

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
				// Starts recording
				this.recordThread = new RecordThread("data/audio");
				this.recordThread.start();
		}else{
			if(this.time==-3){
				this.stateText.setText("3");
				this.stateText.setFont(Const.font(150));
			}
			else if(this.time==-2){
				this.stateText.setText("2");
				this.stateText.setFont(Const.font(200));
			}
			else if(this.time==-1){
				this.stateText.setText("1");
				this.stateText.setFont(Const.font(250));
			}
		}
		this.time++;
	}
}