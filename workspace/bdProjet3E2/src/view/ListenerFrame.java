package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;

import jdbc.FunctionSql;

public class ListenerFrame implements ActionListener,FocusListener,WindowListener{
	private BdFrame frame;
	private FunctionSql sql;
	public ListenerFrame(BdFrame b){
		frame=b;
		try{
		sql=new FunctionSql();
		}catch(Exception e){
			problem("Impossible de se connecter à la base de donnée, merci de bien vouloir contacter un administrateur\nL'application va maintenant se fermer",true);
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() instanceof JButton){
			if(frame.getRadioFct1().isSelected()){
				try{
					String result=sql.Function1();frame.notificationResult(result);
				}catch(SQLException e){
					frame.notificationError("Erreur lors de la communication avec la base de donnée, veuillez contacter un administrateur");
				}
				}
			else if(frame.getRadioFct2().isSelected()){
				try{
					String result=sql.Function2();frame.notificationResult(result);
				}catch(SQLException e){
					frame.notificationError("Erreur lors de la communication avec la base de donnée, veuillez contacter un administrateur");
				}
			}
			else if(frame.getRadioFct3().isSelected()){
				try {
					String result=sql.Function3(frame.getTextFct3().getText());
					frame.notificationResult(result);
				} catch (IllegalArgumentException e) {
					frame.notificationError("Entré invalide");
				} catch (SQLException e) {
					frame.notificationError("Erreur lors de la communication avec la base de donnée, veuillez contacter un administrateur");
				}
			}
			else if(frame.getRadioFct4().isSelected()){}
			else if(frame.getRadioFct5().isSelected()){}
			
			else if(frame.getRadioFct6().isSelected()){
				try {
					String result=sql.Function6(frame.getTextFct61().getText(),frame.getTextFct62().getText());
					frame.notificationResult(result);
				} catch (IllegalArgumentException e) {
					frame.notificationError("Entré invalide");
				} catch (SQLException e) {
					frame.notificationError("Erreur lors de la communication avec la base de donnée, veuillez contacter un administrateur");
				}
			}
			else if(frame.getRadioFct7().isSelected()){}
			else if(frame.getRadioFct8().isSelected()){}
		}
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		if(arg0.getSource() instanceof JTextField){
			JTextField temp=(JTextField)arg0.getSource();
			temp.setText("");
			temp.validate();
		}
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		try {
			sql.getConnection().closeConnection();
		} catch (Exception e1) {
			System.exit(1);
		}
		System.exit(0);
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void problem(String message, boolean fatalError){
		try{//On essai de fermer la connexion
			if(fatalError)
				sql.getConnection().closeConnection();
		} 
		catch (Exception e) {}
		finally{
			if(fatalError)
				frame.notificationFatalError(message);
			else
				frame.notificationError(message);
		}
	}
}
