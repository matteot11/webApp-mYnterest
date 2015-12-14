package rss.collector;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DB {
	
	
	public static Connection createDB () throws SQLException, ClassNotFoundException	{
		
		File f = new File("mynterest.db");
	
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:mynterest.db");
		
		Statement stat = con.createStatement();
		stat.executeUpdate("create table Users (email varchar PRIMARY KEY,"
												+ "password varchar,"
												+ "topic varchar,"
												+ "source varchar)");
		
		stat.executeUpdate("create table News (title varchar, description varchar, link varchar PRIMARY KEY, topic varchar, source varchar, date timestamp)");
	
		return con;
	
	}

}
