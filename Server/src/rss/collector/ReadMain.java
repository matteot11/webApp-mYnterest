package rss.collector;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;



public class ReadMain {
	
	final static int TIMER = 10*1000;  //tempo che il server aspetta prima di aggiornare le notizie
	
	static NewsCollector nc;

	static Connection con;

	static ArrayList<News> myNews = new ArrayList<News>();

	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, InterruptedException, XMLStreamException {
	
		myNews = UrlSetter.setUrl();
		
		File f = new File ("mynterest.db");
		
		TopicArray newTopics = new TopicArray();

		String curTopic;
		
		while(true)	{  //ciclo 

			newTopics.clear();
			//System.out.println(newTopics.toString());
			curTopic = null;
			
			//connessione db
			if(!f.exists()){
				con = DB.createDB();
			}	  
			else {
				Class.forName("org.sqlite.JDBC");
				con = DriverManager.getConnection("jdbc:sqlite:mynterest.db");
			}

	
			
			
			
			for( News news : myNews){  //scorriamo tutti gli url dei feed

				curTopic = null;
				//System.out.println("siamo qui" + curTopic);
				nc = new NewsCollector(con,news);

				curTopic = nc.newsCollect();
				
				if(curTopic != null && !newTopics.contains(curTopic))	{
					//System.out.println("siamo qui" + curTopic);
					newTopics.add(curTopic);
				}
				
				
			}
			
			//in newTopics abbiamo tutti i topic per i quali, nell'iterazione corrente, è stata aggiunta almeno una notiza
			String templateCheck = "Select email, topic from Users";
			PreparedStatement statCheck = con.prepareStatement(templateCheck);
			
			ResultSet rs = statCheck.executeQuery();
			
			if (!newTopics.isEmpty()){
				while(rs.next())	{
					//System.out.println(newTopics.toString());
					SendEmail.send(rs.getString(1), rs.getString(2), newTopics);
				}
			}
			
			StoreFeed sf = new StoreFeed(con);  //nonostante noi prendiamo solo notizie recenti di 2 giorni, quelle vecchie rimangono nel db, e vanno rimosse
			
			sf.deleteOldNews();
			
			
			con.close();
			
			
			
			
			

			Thread.sleep(TIMER);
		}

	/*
    String templateInsert= "select date from News where topic='politica'";
	PreparedStatement statInsert=con.prepareStatement(templateInsert);
	ResultSet rs = statInsert.executeQuery();
		
	while(rs.next())	{

		System.out.println(rs.getTimestamp("date"));
	}

		
	 String templateInsert1= "SELECT title, date FROM news ORDER BY date DESC";
		PreparedStatement statInsert1=con.prepareStatement(templateInsert1);
		ResultSet rs1 = statInsert1.executeQuery();


		while(rs1.next())	{

			//System.out.println(rs1.getString("title"));
			System.out.println(rs1.getTimestamp("date"));	
		}
		*/
	}
		 
		
	
	
} 
