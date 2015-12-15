package rss.collector;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Conversion {
	
	final static String OLD_FORMAT = "EE, dd MMM yyyy HH:mm:ss Z";
    final static String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss";
    final static String OLD_FORMAT2 = "EE, dd MMM yyyy HH:mm:ss";
	
	
	
	public static Timestamp dateConvert (String pubDate) throws ParseException	{

 
    String oldDateString = pubDate;
    String newDateString;
    SimpleDateFormat sdf1;
    
   
    
    if (oldDateString.endsWith("+0100")){
	    sdf1 = new SimpleDateFormat(OLD_FORMAT, Locale.ENGLISH);
    }
    else {
	    sdf1 = new SimpleDateFormat(OLD_FORMAT2, Locale.ENGLISH);
    }
    
    Date d = sdf1.parse(oldDateString);
    sdf1.applyPattern(NEW_FORMAT);
    newDateString = sdf1.format(d);
    
    
    //System.out.println(newDateString);
    
    Date d1 = new SimpleDateFormat(NEW_FORMAT).parse(newDateString);
    
    //java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
    
    java.sql.Timestamp d2 = new java.sql.Timestamp(d1.getTime());
    
    return d2;
	}

}
