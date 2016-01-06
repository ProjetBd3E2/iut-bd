package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJdbc {
	private Connection co;
	private boolean closed;
	
	/**
	 * 
	 */
	public ConnectionJdbc(){
		co=null;
		closed=false;
		try{
			String url="jdbc:oracle:thin:steixe2/mdporacle@oracle.iut-orsay.fr:1521:etudom";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			co= DriverManager.getConnection(url);
		}
		catch (ClassNotFoundException e){
			closed=true;
			System.err.println("err");
		}
		catch (SQLException e)
		{
			closed=true;
			System.err.println("autre err");
		}
	}
	
	public void closeConnection() throws SQLException{
		try{co.close();}
		finally{closed=true;}
	}
	
	public Connection getConnection(){
		if(closed)
			return null;
		return co;
	}
	
	public boolean isClosed(){
		return closed;
	}
}
