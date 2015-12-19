package rss.collector;

import java.util.ArrayList;

public class UrlSetter {
	
	public static ArrayList<News> setUrl ()	{
		ArrayList<String> politica = new ArrayList<String>();
		politica.add("http://www.repubblica.it/rss/politica/rss2.0.xml?ref=HRF-1");
		politica.add("http://xml.corriereobjects.it/rss/politica.xml");
		politica.add("http://www.lastampa.it/italia/politica/rss.xml");
		
		ArrayList<String> cronaca = new ArrayList<String>();
		cronaca.add("http://www.repubblica.it/rss/cronaca/rss2.0.xml?ref=HRF-1");
		cronaca.add("http://xml.corriereobjects.it/rss/cronache.xml");
		cronaca.add("http://www.lastampa.it/italia/cronache/rss.xml");
		
		ArrayList<String> economia = new ArrayList<String>();
		economia.add("http://www.repubblica.it/rss/economia/rss2.0.xml?ref=HRF-1");
		economia.add("http://xml.corriereobjects.it/rss/economia.xml");
		economia.add("http://www.lastampa.it/economia/rss.xml");
		
		ArrayList<String> sport = new ArrayList<String>();
		sport.add("http://www.repubblica.it/rss/sport/rss2.0.xml?ref=HRF-1");
		sport.add("http://xml.corriereobjects.it/rss/sport.xml");
		sport.add("http://www.lastampa.it/sport/rss.xml");
		
		ArrayList<String> esteri = new ArrayList<String>();
		esteri.add("http://www.repubblica.it/rss/esteri/rss2.0.xml?ref=HRF-1");
		esteri.add("http://xml.corriereobjects.it/rss/esteri.xml");
		esteri.add("http://www.lastampa.it/esteri/rss.xml");
		
		ArrayList<String> tecnologia = new ArrayList<String>();
		tecnologia.add("http://www.repubblica.it/rss/tecnologia/rss2.0.xml?ref=HRF-1");
		//tecnologia.add();
		tecnologia.add("http://www.lastampa.it/tecnologia/rss.xml");
		
		/*ArrayList<String> ambiente = new ArrayList<String>();
		ambiente.add("http://www.repubblica.it/rss/ambiente/rss2.0.xml?ref=HRF-1");
		ambiente.add("http://xml.corriereobjects.it/rss/ambiente.xml");
		ambiente.add("http://www.lastampa.it/rss/tuttogreen");*/
		
		/*ArrayList<String> motori = new ArrayList<String>();
		motori.add("http://www.repubblica.it/rss/motori/rss2.0.xml?ref=HRF-1");
		motori.add("http://xml.corriereobjects.it/rss/motori.xml");
		//motori.add("http://www.lastampa.it/motori/rss.xml"); ---->VUOTO!!*/
		
		ArrayList<String> scienze = new ArrayList<String>();
		scienze.add("http://www.repubblica.it/rss/scienze/rss2.0.xml?ref=HRF-1");
		scienze.add("http://xml.corriereobjects.it/rss/scienze.xml");
		//scienze.add();
		
		/*ArrayList<String> spettacoli = new ArrayList<String>();
		spettacoli.add("http://www.repubblica.it/rss/spettacoli/rss2.0.xml?ref=HRF-1");
		spettacoli.add("http://xml.corriereobjects.it/rss/spettacoli.xml");
		spettacoli.add("http://www.lastampa.it/spettacoli/rss.xml");*/
		
		/*ArrayList<String> cultura = new ArrayList<String>();
		//cultura.add();
		cultura.add("http://xml.corriereobjects.it/rss/cultura.xml");
		cultura.add("http://www.lastampa.it/cultura/rss.xml");*/
		
		/*ArrayList<String> animali = new ArrayList<String>();
		//animali.add();
		animali.add("http://xml.corriereobjects.it/rss/animali.xml");
		animali.add("http://www.lastampa.it/rss/lazampa");*/
		
		ArrayList<News> myNews = new ArrayList<News>();
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		list.add(politica);
		list.add(cronaca);
		list.add(economia);
		list.add(sport);
		//list.add(ambiente);
		//list.add(animali);
		//list.add(spettacoli);
		//list.add(cultura);
		//list.add(motori);
		list.add(scienze);
		list.add(tecnologia);
		list.add(esteri);
		
		for(ArrayList<String> i : list)	{
			for(String j : i)	{
				if (i==politica)	{
					myNews.add(new News(j, "politica"));
					continue;
				}
				if (i==cronaca)	{
					myNews.add(new News(j, "cronaca"));	
					continue;
				}
				if (i==economia)	{
					myNews.add(new News(j, "economia"));	
					continue;
				}
				if (i==sport)	{
					myNews.add(new News(j, "sport"));	
					continue;	
				}
				/*if (i==ambiente)	{
					myNews.add(new News(j, "ambiente"));	
					continue;
				}*/
				/*if (i==animali)	{
					myNews.add(new News(j, "animali"));
					continue;
				}*/
				/*if (i==spettacoli)	{
					myNews.add(new News(j, "spettacoli"));	
					continue;
				}*/
				/*if (i==cultura)	{
					myNews.add(new News(j, "cultura"));	
					continue;
				}*/
				/*if (i==motori)	{
					myNews.add(new News(j, "motori"));
					continue;
				}*/
				if (i==scienze)	{
					myNews.add(new News(j, "scienze"));	
					continue;
				}
				if (i==tecnologia)	{
					myNews.add(new News(j, "tecnologia"));
					continue;
				}
				if (i==esteri)	{
					myNews.add(new News(j, "esteri"));	
					continue;
				}
				
			}
	}
		
		
		return myNews;
	}
	
	
}
