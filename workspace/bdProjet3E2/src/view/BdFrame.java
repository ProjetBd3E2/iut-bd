package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BdFrame extends JFrame {
	private JPanel fct1,fct2,fct3,fct4,fct5,fct6,fct7,fct8,contentPane,resultPane,framePane;
	private JTextField textFct3,textFct4,textFct5,textFct61,textFct62,textFct8;
	private JRadioButton radioFct1,radioFct2,radioFct3,radioFct4,radioFct5,radioFct6,radioFct7,radioFct8;
	private ButtonGroup group;
	private JButton execute;
	private JLabel results;
	/**
	 * crée la fenêtre d'affichage principal. Contient des boutons pour choisir les statistiques demandé et affiche le résultat
	 * une fois que l'utilisateur a choisi une fonction à appeler. La classe ListenerFrame réagis quand l'utilisateur demande 
	 * des statistiques
	 * @see listenerFrame
	 */
	public BdFrame(){
		super("Gestionnaire Statistique Base de Données");
		contentPane=new JPanel();resultPane=new JPanel();framePane=new JPanel();
		results=new JLabel();
		fct1=new JPanel();fct2=new JPanel();fct3=new JPanel();fct4=new JPanel();fct5=new JPanel();fct6=new JPanel();fct7=new JPanel();fct8=new JPanel();
		radioFct1=new JRadioButton("Nombre d'étudiant avec un stage cette année");
		radioFct2=new JRadioButton("Nombre d'étudiant sans stage cette année");
		radioFct3=new JRadioButton("Nombre d'étudiant sans stage à la date suivante:");textFct3=new JTextField("");
		radioFct4=new JRadioButton("Nombre de stagiaire pris par chaque entreprise depuis ... années:");textFct4=new JTextField("");
		radioFct5=new JRadioButton("Nombre moyen de stagiaires encadrés par les entreprise depuis ... années:");textFct5=new JTextField("");
		radioFct6=new JRadioButton("Nombre de stage dans une zone géographique:");textFct61=new JTextField("");textFct62=new JTextField("");
		radioFct7=new JRadioButton("Nombre de stage pour toutes les zones géographiques");
		radioFct8=new JRadioButton("Toute les entreprises et leur contact ayant eu au moins un stage depuis ... années:");textFct8=new JTextField("");
		group=new ButtonGroup();
		execute=new JButton("Executer");
		ListenerFrame l=new ListenerFrame(this);
		initialize();
		
		resultPane.setBorder(BorderFactory.createLineBorder(Color.black));
		
		group.add(radioFct1);
		group.add(radioFct2);
		group.add(radioFct3);
		group.add(radioFct4);
		group.add(radioFct5);
		group.add(radioFct6);
		group.add(radioFct7);
		group.add(radioFct8);
		
		execute.addActionListener(l);
		textFct3.addFocusListener(l);
		textFct4.addFocusListener(l);
		textFct5.addFocusListener(l);
		textFct61.addFocusListener(l);
		textFct62.addFocusListener(l);
		textFct8.addFocusListener(l);
		addWindowListener(l);
		
		BorderLayout b=new BorderLayout();BorderLayout b1=new BorderLayout();BorderLayout b2=new BorderLayout();BorderLayout b3=new BorderLayout();
		BorderLayout b4=new BorderLayout();BorderLayout b5=new BorderLayout();BorderLayout b6=new BorderLayout();BorderLayout b7=new BorderLayout();
		BorderLayout b8=new BorderLayout();
		b.setHgap(10);b1.setHgap(10);b2.setHgap(10);b3.setHgap(10);b4.setHgap(10);b5.setHgap(10);b6.setHgap(10);b7.setHgap(10);b8.setHgap(10);
		
		fct1.setLayout(b);fct2.setLayout(b2);fct3.setLayout(b3);
		fct4.setLayout(b4);fct5.setLayout(b5);fct6.setLayout(b6);
		fct7.setLayout(b7);fct8.setLayout(b8);
		
		fct1.add(radioFct1,BorderLayout.WEST);
		fct2.add(radioFct2,BorderLayout.WEST);
		fct3.add(radioFct3,BorderLayout.WEST);fct3.add(textFct3,BorderLayout.EAST);
		fct4.add(radioFct4,BorderLayout.WEST);fct4.add(textFct4,BorderLayout.EAST);
		fct5.add(radioFct5,BorderLayout.WEST);fct5.add(textFct5,BorderLayout.EAST);
		fct6.add(radioFct6,BorderLayout.WEST);fct6.add(textFct61,BorderLayout.CENTER);fct6.add(textFct62,BorderLayout.EAST);
		fct7.add(radioFct7,BorderLayout.WEST);
		fct8.add(radioFct8,BorderLayout.WEST);fct8.add(textFct8,BorderLayout.EAST);
		
		framePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		resultPane.add(results);
		resultPane.setLayout(new GridLayout(1,1));
		
		contentPane.add(fct1);
		contentPane.add(fct2);
		contentPane.add(fct3);
		contentPane.add(fct4);
		contentPane.add(fct5);
		contentPane.add(fct6);
		contentPane.add(fct7);
		contentPane.add(fct8);
		contentPane.add(execute);
		contentPane.setLayout(new GridLayout(9,1));
		BorderLayout bFrame=new BorderLayout();
		bFrame.setVgap(10);
		bFrame.setHgap(10);
		framePane.setLayout(bFrame);
		framePane.add(contentPane,BorderLayout.CENTER);
		framePane.add(resultPane,BorderLayout.SOUTH);
		setLayout(new BorderLayout());
		add(framePane,BorderLayout.CENTER);

		pack();
		validate();
		setVisible(true);
	}
	
	public static void main(String args[]){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Impossible d'obtenir une meilleure interface graphique");
		}
		BdFrame b=new BdFrame();
	}
	/**
	 * Affiche un message d'erreur, provoque la fermeture de l'application
	 * @param message
	 */
	public void notificationFatalError(String message){
		/*provoque fermeture du programme*/
		radioFct1.setEnabled(false);
		radioFct2.setEnabled(false);
		radioFct3.setEnabled(false);
		radioFct4.setEnabled(false);
		radioFct5.setEnabled(false);
		radioFct6.setEnabled(false);
		radioFct7.setEnabled(false);
		radioFct8.setEnabled(false);
		execute.setEnabled(false);
		int input = JOptionPane.showOptionDialog(this,message,"Erreur grave",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,null,null);
		if(input==JOptionPane.OK_OPTION){
			System.exit(1);
		}
	}
	/**
	 * Affiche un message d'erreur, ne provoque pas la fermeture de l'application
	 * @param message
	 */
	public void notificationError(String message){
		/*affiche seulement un message d'erreur, le programme peut continuer*/
		JOptionPane.showMessageDialog(this, message,"Quelque chose est advenu",JOptionPane.WARNING_MESSAGE);
	}
	/**
	 * Met à jour le JLabel result. Affiche le résultat des statistiques demandé par l'utilisateur
	 * @param message
	 */
	public void notificationResult(String message){
		initialize();
		results.setText(message);
		pack();
	}
	/**
	 * Remet à leur état initiale toute les JTextField et le JLabel
	 */
	public void initialize(){
		textFct3.setText("jj/mm/aaaa");
		textFct4.setText("année");
		textFct5.setText("année");
		textFct61.setText("ville\t");
		textFct62.setText("code postal");
		textFct8.setText("année");
		results.setText("");
	}

	public JPanel getFct1() {
		return fct1;
	}

	public JPanel getFct2() {
		return fct2;
	}

	public JPanel getFct3() {
		return fct3;
	}

	public JPanel getFct4() {
		return fct4;
	}

	public JPanel getFct5() {
		return fct5;
	}

	public JPanel getFct6() {
		return fct6;
	}

	public JPanel getFct7() {
		return fct7;
	}

	public JPanel getFct8() {
		return fct8;
	}

	public JTextField getTextFct3() {
		return textFct3;
	}

	public JTextField getTextFct4() {
		return textFct4;
	}

	public JTextField getTextFct5() {
		return textFct5;
	}

	public JTextField getTextFct61() {
		return textFct61;
	}

	public JTextField getTextFct62() {
		return textFct62;
	}

	public JTextField getTextFct8() {
		return textFct8;
	}

	public JRadioButton getRadioFct1() {
		return radioFct1;
	}

	public JRadioButton getRadioFct2() {
		return radioFct2;
	}

	public JRadioButton getRadioFct3() {
		return radioFct3;
	}

	public JRadioButton getRadioFct4() {
		return radioFct4;
	}

	public JRadioButton getRadioFct5() {
		return radioFct5;
	}

	public JRadioButton getRadioFct6() {
		return radioFct6;
	}

	public JRadioButton getRadioFct7() {
		return radioFct7;
	}

	public JRadioButton getRadioFct8() {
		return radioFct8;
	}

	public ButtonGroup getGroup() {
		return group;
	}

	public JButton getExecute() {
		return execute;
	}
	
}
