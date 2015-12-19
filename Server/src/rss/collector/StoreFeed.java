package rss.collector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class StoreFeed {
	
	final static int DELETEBEFORE = -2;
	
	FeedMessage message;
	Connection con;
	ResultSet rs;
	
	public StoreFeed (Connection con)	{
		this.con=con;
	}
	
	public boolean storeNews (FeedMessage message) throws SQLException, ParseException	{

		String templateCheck = "Select link from News where link=?";
		PreparedStatement statCheck = con.prepareStatement(templateCheck);
		statCheck.setString(1, message.getLink());

		rs = statCheck.executeQuery();
		Date d = new Date();

		if(!rs.next())	{ //se la notizia non è già presente nel db

			if(Conversion.dateConvert(message.getPubDate()).getTime()>d.getTime()-1000*60*60*24*2 && !message.getDescription().isEmpty())	{  //e se la notizia ha pubdate maggiore di 2 giorni fa(d.getTime() mi restituisce la data odierna in millisecondi), inoltre la notizia deve contenere descrizione
				String templateInsert = "insert into News VALUES (?,?,?,?,?,?)";   //la inseriamo nel db
				PreparedStatement statInsert=con.prepareStatement(templateInsert);
				statInsert.setString(1,message.getTitle());
				statInsert.setString(2,message.getDescription());
				statInsert.setString(3,message.getLink());
				statInsert.setString(4,message.getTopic());
				statInsert.setString(5,message.getSource());
	
				statInsert.setTimestamp(6, Conversion.dateConvert(message.getPubDate()));
				
				statInsert.executeUpdate();
				
				return true;  //se è stata aggiunta una notizia in un certo topic, torniamo true
			}
			
		}
		
			return false;
			
		

	}


	
	public void deleteOldNews () throws SQLException	{
		
		
		//java.util.Date date= new java.util.Date();
		
		 
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, DELETEBEFORE);
		long x = cal.getTimeInMillis();
		
		//System.out.println(cal.DAY_OF_YEAR);
		
		String templateCheck = "Delete from News where date < ?";
		PreparedStatement statCheck = con.prepareStatement(templateCheck);
		statCheck.setLong(1, x);
		
		statCheck.executeUpdate();
		/*
		while(rs.next())	{
			System.out.println(rs.getDate(1));
		}
		*/
	}

}
