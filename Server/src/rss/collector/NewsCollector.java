package rss.collector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

public class NewsCollector {
	
	private Connection con;
	private News news;
	
	

	
	public NewsCollector(Connection con, News news) {
		super();
		this.con = con;
		this.news = news;
	}




	public void newsCollect() throws SQLException, ParseException{
		
			RSSFeedParser parser = new RSSFeedParser(news.getUrl(), news.getTopic(), news.getSource());
		    Feed feed = parser.readFeed();
		    // System.out.println(feed);
		    //System.out.println(feed.getDescription());
		    

		    StoreFeed sf = new StoreFeed(con);
		    
		    
		    for (FeedMessage message : feed.getMessages()) {
		    	sf.storeNews(message);
		    }
		
	}

	
	
	
	
	

}
