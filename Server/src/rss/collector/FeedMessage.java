package rss.collector;

/*
 * Represents one RSS message
 */
public class FeedMessage {

  String title;
  String description;
  String link;
  String author;
  String guid;
  String pubDate;
  String topic;
  String source;
  
  

  public String getSource() {
	return source;
}

public void setSource(String source) {
	this.source = source;
}

public String getTopic() {
	return topic;
}

public void setTopic(String topic) {
	this.topic = topic;
}

public String getPubDate() {
	return pubDate;
  }

  public void setPubDate(String pubDate) {
	this.pubDate = pubDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }
/*
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }*/



  @Override
  public String toString() {
    return "FeedMessage [title=" + title + ", description=" + description
        + ", link=" + link + ", pubDate=" + pubDate
        + "]";
  }

} 