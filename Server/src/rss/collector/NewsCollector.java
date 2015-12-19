package rss.collector;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import javax.xml.stream.XMLStreamException;

public class NewsCollector {
	
	private Connection con;
	private News news;
	


	
	public NewsCollector(Connection con, News news) {
		super();
		this.con = con;
		this.news = news;
	}




	public String newsCollect() throws SQLException, ParseException, XMLStreamException, InterruptedException{
		Feed feed;
		
		try	{
			RSSFeedParser parser = new RSSFeedParser(news.getUrl(), news.getTopic(), news.getSource());
		    feed = parser.readFeed();
		    // System.out.println(feed);
		    // System.out.println(feed.getDescription());
		}
		catch (XMLStreamException e)	{
			Thread.sleep(1000*2);
			RSSFeedParser parser = new RSSFeedParser(news.getUrl(), news.getTopic(), news.getSource());
		    feed = parser.readFeed();
		}
		    String curTopic = null;

		    StoreFeed sf = new StoreFeed(con);
		    
		    
	   
		for (FeedMessage message : feed.getMessages()) { //scorriamo tutte le notizie all'interno di un feed
	    	if (sf.storeNews(message))	{
	    		curTopic = news.getTopic(); //se e stata aggiunta almeno una nuova notizia in un certo topic, ritorniamo il topic
	    		
	    	}
	    }
	    System.out.println(curTopic);  //se per il topic corrente è stata trovata una notizia, stampiamo il topic corrente (altrimenti null)
	    return curTopic;
	
	}

	
	
	
	
	

}
