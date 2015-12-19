package rss.collector;

import java.util.ArrayList;

public class TopicArray extends ArrayList<String>{
	
	public String toString ()	{
		String ret = "";
		for (String s : this)	{
			ret+=s + "\n";
		}
		
		return ret;
	}
	
}
