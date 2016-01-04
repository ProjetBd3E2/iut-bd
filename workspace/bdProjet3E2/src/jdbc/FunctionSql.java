package jdbc;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class FunctionSql {
	private ConnectionJdbc co;
	
	public FunctionSql() throws SQLException{
		co=new ConnectionJdbc();
		if(co.isClosed())
			throw new SQLException();
	}
	
	public String Function1() throws SQLException{
		String result="";//Contient le message qui sera affiché
		CallableStatement cstat=co.getConnection().prepareCall("{?=call uneComedie(?,?)}");
		return result;
	}
	public String Function2() throws SQLException{
		String result="";
		return result;
	}
	public String Function3(String entry) throws SQLException{
		String result="";
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
	public String Function6(String entry1,String entry2) throws SQLException{
		String result="";
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
	
	public ConnectionJdbc getConnection(){
		return co;
	}
}
