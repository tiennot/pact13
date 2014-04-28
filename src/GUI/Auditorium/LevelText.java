package GUI.Auditorium;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.General.Window;
import GUI.Outils.Text;

public class LevelText extends Text implements MouseListener{
	private int levelId;
	private LearnText learnText;
	private Window window;
	
	public LevelText(String text, int levelId, Window window, LearnText learnText){
		super(text);
		this.levelId = levelId;
		this.window = window;
		this.learnText = learnText;
		// Listens to itself
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Auditorium.levelId = this.levelId;
		
		String learnText = "<html><p style='text-align:center'>Voici le texte à lire :</p><p style='text-align:justify; margin-top:25px;'>";
		if(this.levelId==1){
			learnText += "Dans ce temple des Nations Unies, nous sommes les gardiens d'un idéal, nous sommes les gardiens d'une conscience. La lourde responsabilité et l'immense honneur qui sont les nôtres doivent nous conduire à donner la priorité au désarmement dans la paix.";
		}else if(this.levelId==2){
			learnText += "Moi, Général de Gaulle, actuellement à Londres, j'invite les officiers et les soldats français qui se trouvent en territoire britannique ou qui viendraient à s'y trouver, avec leurs armes ou sans leurs armes, j'invite les ingénieurs et les ouvriers spécialistes des industries d'armement, à se mettre en rapport avec moi.";
		}else if(this.levelId==3){
			learnText += "Demain, grâce à vous, la justice française ne sera plus une justice qui tue. Demain, grâce à vous, il n'y aura plus pour notre honte commune, des exécutions furtives, à l'aube, sous le dais noir, dans les prisons françaises. Demain, les pages sanglantes de notre justice seront tournées.";
		}else{
			learnText += "Quand vingt siècles de christianisme ont passé sur les peuples, quand depuis cent ans ont triomphé les principes des Droits de l’homme, est-il possible que des millions d’hommes puissent, sans savoir pourquoi, sans que les dirigeants le sachent, s’entre-déchirer sans se haïr?";
		}
		learnText += "</p><p style='margin-top:25px; text-align:center'>Cliquez pour passer à l'enregistrement</p></html>";
		this.learnText.setText(learnText);
		this.window.setFenetre("learnText");
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
