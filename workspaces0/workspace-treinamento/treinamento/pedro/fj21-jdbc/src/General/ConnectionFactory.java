package General;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public	Connection	getConnection() {
		try	{
						return	DriverManager.getConnection(
														"jdbc:mysql://localhost/fj21",	"root",	"Baccelli@2018");
		}	catch	(SQLException	e)	{
						throw new	RuntimeException(e);
		}
	}
}
