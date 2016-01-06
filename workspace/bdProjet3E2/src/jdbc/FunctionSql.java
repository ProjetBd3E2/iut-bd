package jdbc;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import oracle.jdbc.OracleTypes;

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
	 * @return nombre de personne avec un stage � l'heure actuelle
	 * @throws SQLException
	 */
	public String Function1() throws SQLException{
		String result="";//Contient le message qui sera affich�
		CallableStatement cstat=co.getConnection().prepareCall("{? = call STAGIAIREANNEECOURANTE}");
		cstat.registerOutParameter(1, java.sql.Types.INTEGER);
		boolean succes=cstat.execute();
		result="Il y a ";
		result+=Integer.toString(cstat.getInt(1));
		if(cstat.getInt(1)>1)
			result+=" �tudiants avec un stage cette ann�e";
		else
			result+=" �tudiants avec un stage cette ann�e";
		cstat.close();
		return result;
	}
	/**
	 * @return nombre de personne sans stage cette ann�e � l'heure actuelle
	 * @throws SQLException
	 */
	public String Function2() throws SQLException{
		String result="";
		CallableStatement cstat=co.getConnection().prepareCall("{?=call NONSTAGIAIREANNEECOURANTE}");
		cstat.registerOutParameter(1, java.sql.Types.INTEGER);
		boolean succes=cstat.execute();
		result="Il y a ";
		result+=Integer.toString(cstat.getInt(1));
		if(cstat.getInt(1)>1)
			result+=" �tudiants sans stage cette ann�e";
		else
			result+=" �tudiant sans stage cette ann�e";
		cstat.close();
		return result;
	}
	/**
	 * @param entry date
	 * @return nombre de personne sans stage lors d'une ann�e pr�c�dente � une date renseign� 
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
			if(cstat.getInt(1)<0){
				throw new IllegalArgumentException();
			}
			result="Il y a ";
			result+=Integer.toString(cstat.getInt(1));
			if(cstat.getInt(1)>1)
				result=result+" �tudiants sans stage � la date suivante: "+entry;
			else
				result=result+" �tudiant sans stage � la date suivante: "+entry;
			cstat.close();
		}catch(ParseException e){
			throw new IllegalArgumentException();
		}
		return result;
	}
	/**
	 * @param entry ann�e
	 * @return nombre d'�tudiant par entreprise
	 * @throws SQLException
	 */
	public String Function4(String entry) throws SQLException,IllegalArgumentException{
		String result="";
		String regex = "^\\d+$";
		if(entry.matches(regex)){
			int annee=Integer.parseInt(entry);
			CallableStatement cstat=co.getConnection().prepareCall("{ ?=call NBETUSTAGIAIREDATEN(?) }");
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			cstat.setInt(2,annee);
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(1);
			result="<html><table><tr><th>Nom entreprise</th><th>nombre �tudiant</th></tr>";
			while (rs.next()) {
				String tmp=Integer.toString(rs.getInt(3));
			    result=result+"<tr><td>"+rs.getString(2)+"</td><td>"+tmp+"</td></tr>";
			}
			result+="</table></html>";
			rs.close();
			cstat.close();
		}
		else
			throw new IllegalArgumentException();
		return result;
	}
	/**
	 * @param entry ann�e
	 * @return nombre moyen de stagiaire par entreprise depuis n ann�e
	 * @throws SQLException
	 */
	public String Function5(String entry) throws SQLException,IllegalArgumentException{
		String result="";
		String regex = "^\\d+$";
		if(entry.matches(regex)){
			int annee=Integer.parseInt(entry);
			CallableStatement cstat=co.getConnection().prepareCall("{ ?=call NBMOYENSTAGIAIRESENTREP(?) }");
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			cstat.setFloat(2,annee);
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(1);
			result="<html><table><tr><th>Nom entreprise</th><th>nombre �tudiant</th></tr>";
			while (rs.next()) {
				String tmp=Float.toString(rs.getFloat(3));
			    result=result+"<tr><td>"+rs.getString(2)+"</td><td>"+tmp+"</td></tr>";
			}
			result+="</table></html>";
			rs.close();
			cstat.close();
		}
		else
			throw new IllegalArgumentException();
		return result;
	}
	/**
	 * @param entry1  ville
	 * @param entry2 code postal
	 * @return nombre de stage cette ann�e dans une zone renseign�
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
			result="Il y a ";
			result+=Integer.toString(cstat.getInt(1));
			if(cstat.getInt(1)>1)
				result=result+" �tudiants qui ont un stage dans la zone suivante: "+entry1+" "+entry2;
			else
				result=result+" �tudiant qui a un stage dans la zone suivante: "+entry1+" "+entry2;
			cstat.close();
		}
		else{
			throw new IllegalArgumentException();
		}
		return result;
	}
	/**
	 * @return nombre d'�tudiant pris par zone
	 * @throws SQLException
	 */
	public String Function7() throws SQLException{
		String result="";
		CallableStatement cstat=co.getConnection().prepareCall("{ ?=call NBSTAGETOUTEZONE}");
		cstat.registerOutParameter(1, OracleTypes.CURSOR);
		cstat.executeQuery();
		ResultSet rs = (ResultSet)cstat.getObject(1);
		result="<html><table><tr><th>Code postal</th><th>nombre d'�tudiant</th></tr>";
		while (rs.next()) {
			String tmp=Integer.toString(rs.getInt(2));
		    result=result+"<tr><td>"+rs.getString(1)+"</td><td>"+tmp+"</td></tr>";
		}
		result+="</table></html>";
		rs.close();
		cstat.close();
		return result;
	}
	/**
	 * 
	 * @param entry ann�e
	 * @return 
	 * @throws SQLException
	 */
	public String Function8(String entry) throws SQLException,IllegalArgumentException{
		String result="";
		String regex = "^\\d+$";
		if(entry.matches(regex)){
			int annee=Integer.parseInt(entry);
			CallableStatement cstat=co.getConnection().prepareCall("{ ?=call CONTACTENTREPRISE(?) }");
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			cstat.setInt(2,annee);
			cstat.executeQuery();
			ResultSet rs = (ResultSet)cstat.getObject(1);
			result="<html><table><tr><th>Nom entreprise</th><th>nom �tudiant</th><th>pr�nom �tudiant</th></tr>";
			while (rs.next()) {
			    result=result+"<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td>"+rs.getString(4)+"</tr>";
			}
			result+="</table></html>";
			rs.close();
			cstat.close();
		}
		else
			throw new IllegalArgumentException();
		return result;
	}
	
	/**
	 * @param date
	 * @return la date renseign� sous format java.sql.Date
	 * @throws ParseException
	 */
	public java.sql.Date toSqlDate(String date) throws ParseException{
        java.util.Date ud = fmt.parse(date);
        Date sd = new Date(ud.getTime());
        String formattedSqlDate = fmt.format(sd);
        return sd;
    }
	
	public ConnectionJdbc getConnection(){
		return co;
	}
}
