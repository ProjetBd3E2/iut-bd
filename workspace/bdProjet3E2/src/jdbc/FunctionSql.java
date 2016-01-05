package jdbc;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

public class FunctionSql {
	private ConnectionJdbc co;
	/**
	 * permet de cast un string en date
	 */
	static final java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Constructeur
	 * @see ConnectionJdbc
	 * @throws SQLException
	 */
	public FunctionSql() throws SQLException{
		co=new ConnectionJdbc();
		if(co.isClosed())
			throw new SQLException();
	}
	/**
	 * @return nombre de personne avec un stage à l'heure actuelle
	 * @throws SQLException
	 */
	public String Function1() throws SQLException{
		String result="";//Contient le message qui sera affiché
		CallableStatement cstat=co.getConnection().prepareCall("{?=call STAGIAIREANNEECOURANTE()}");
		cstat.registerOutParameter(1, java.sql.Types.INTEGER);
		boolean succes=cstat.execute();
		if(succes){
			result="Il y a ";
			result+=Integer.toString(cstat.getInt(1));
			result+=" étudiants avec un stage cette année";
		}
		else{throw new SQLException();}
		return result;
	}
	/**
	 * @return nombre de personne sans stage cette année à l'heure actuelle
	 * @throws SQLException
	 */
	public String Function2() throws SQLException{
		String result="";
		CallableStatement cstat=co.getConnection().prepareCall("{?=call NONSTAGIAIREANNEECOURANTE()}");
		cstat.registerOutParameter(1, java.sql.Types.INTEGER);
		boolean succes=cstat.execute();
		if(succes){
			result="Il y a ";
			result+=Integer.toString(cstat.getInt(1));
			result+=" étudiants sans stage cette année";
		}
		else{throw new SQLException();}
		return result;
	}
	/**
	 * @param entry date
	 * @return nombre de personne sans stage lors d'une année précédente à une date renseigné 
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 */
	public String Function3(String entry) throws SQLException, IllegalArgumentException{
		String result="";
		try{
			Date d=toSqlDate(entry);
			CallableStatement cstat=co.getConnection().prepareCall("{?=call NONSTAGIAIREANNEEUTILISATEUR(?)}");
			cstat.setDate(2,d);
			cstat.registerOutParameter(1, java.sql.Types.INTEGER);
			boolean succes=cstat.execute();
			if(succes){
				result="Il y a ";
				result+=Integer.toString(cstat.getInt(1));
				result=result+" étudiants sans stage à la date suivante: "+entry;
			}
			else{throw new SQLException();}
		}catch(ParseException e){
			throw new IllegalArgumentException();
		}
		return result;
	}
	
	public String Function4(String entry) throws SQLException{
		String result="";
		return result;
	}
	public String Function5(String entry) throws SQLException{
		String result="";
		return result;
	}
	/**
	 * @param entry1  ville
	 * @param entry2 code postal
	 * @return nombre de stage cette année dans une zone renseigné
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 */
	public String Function6(String entry1,String entry2) throws SQLException,IllegalArgumentException{
		String result="",regex="[0-9]*";
		if(entry2.matches(regex) && entry2.length()==5){
			CallableStatement cstat=co.getConnection().prepareCall("{?=call NBSTAGEZONEUT(?,?)}");
			cstat.registerOutParameter(1, java.sql.Types.INTEGER);
			cstat.setString(2,entry2);
			cstat.setString(3,entry1);
			boolean succes=cstat.execute();
			if(succes){
				result="Il y a ";
				result+=Integer.toString(cstat.getInt(1));
				result=result+" étudiants qui ont un stage dans la zone suivante: "+entry1+" "+entry2;
			}
		}
		else{
			throw new IllegalArgumentException();
		}
		return result;
	}
	public String Function7() throws SQLException{
		String result="";
		return result;
	}
	public String Function8(String entry) throws SQLException{
		String result="";
		return result;
	}
	
	/**
	 * 
	 * @param date
	 * @return la date renseigné sous format java.sql.Date
	 * @throws ParseException
	 */
	public java.sql.Date toSqlDate(String date) throws ParseException{
        java.util.Date ud = fmt.parse(date);
        Date sd = new Date(ud.getTime());
        String formattedSqlDate = fmt.format(sd);
        System.out.println(formattedSqlDate);
        return sd;
    }
	
	public ConnectionJdbc getConnection(){
		return co;
	}
}
