package rss.collector;

public class News {
	
	private String url;
	private String topic;
	private String source;
	
	
	public News(String url/*, String topic*/, String source) {
		super();
		this.url = url;
		//this.topic = topic;
		this.source = source;
		
		if(url.contains("repubblica"))	{
			this.topic="LaRepubblica.it";
		}
		if(url.contains("corriere"))	{
			this.topic="Corriere.it";
		}
		if(url.contains("lastampa"))	{
			this.topic="LaStampa.it";
		}
	
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	};
	
	

}
