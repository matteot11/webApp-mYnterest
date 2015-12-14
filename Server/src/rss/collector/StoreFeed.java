package rss.collector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StoreFeed {
	
	FeedMessage message;
	Connection con;
	ResultSet rs;
	
	public StoreFeed (Connection con)	{
		this.con=con;
	}
	
	public void storeNews (FeedMessage message) throws SQLException, ParseException	{

		String templateCheck = "Select link from News where link=?";
		PreparedStatement statCheck = con.prepareStatement(templateCheck);
		statCheck.setString(1, message.getLink());

		rs = statCheck.executeQuery();

		if(!rs.next())	{

			String templateInsert = "insert into News VALUES (?,?,?,?,?,?)";
			PreparedStatement statInsert=con.prepareStatement(templateInsert);
			statInsert.setString(1,message.getTitle());
			statInsert.setString(2,message.getDescription());
			statInsert.setString(3,message.getLink());
			statInsert.setString(4,message.getTopic());
			statInsert.setString(5,message.getSource());

			statInsert.setTimestamp(6,Conversion.dateConvert(message.getPubDate()));
			
			statInsert.executeUpdate();
		}


	}


	

}
