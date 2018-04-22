/**
 * 
 */
package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: Administrator
 * @date: Jan 3, 2018
 */
public class DBUtil {
	static
	{
//		1. load the driver
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getMeConnectionObj()
	{
		Connection con = null;
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521/orcl";
		String jdbcUser = "system";
		String jdbcPsw = "oracle";
		
//		2. Establish the Connection
		try
		{
			con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPsw);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
