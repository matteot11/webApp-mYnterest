package rss.collector;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;



public class ReadMain {
	
	final static int TIMER = 1*60*60*1000;
	
	static NewsCollector nc;

	static Connection con;

	static ArrayList<News> list = new ArrayList<News>();

	//SELEZIONE FONTI E TOPIC

	/*---------TOPIC DELLA REPUBBLICA---------*/

	final static String rfeeds = "http://www.repubblica.it/rss/sport/rss2.0.xml?ref=HRF-1"; //repubblica - sport
	
	final static String rfeedc = "http://www.repubblica.it/rss/cronaca/rss2.0.xml?ref=HRF-1"; //repubblica - cronaca
	
	final static String rfeedp = "http://www.repubblica.it/rss/politica/rss2.0.xml?ref=HRF-1"; //repubblica - politica
	
	final static String rfeedt = "http://www.repubblica.it/rss/tecnologia/rss2.0.xml?ref=HRF-1"; //repubblica - tecnologia
	
	final static String rfeeda = "http://www.repubblica.it/rss/ambiente/rss2.0.xml?ref=HRF-1"; //repubblica - ambiente
	
	final static String rfeede = "http://www.repubblica.it/rss/esteri/rss2.0.xml?ref=HRF-1"; //repubblica - esteri
	
	//final static String rfeedca = "http://www.repubblica.it/rss/sport/calcio/rss2.0.xml?ref=HRF-1"; //repubblica - calcio
	
	final static String rfeedm = "http://www.repubblica.it/rss/motori/rss2.0.xml?ref=HRF-1"; //repubblica - motori
	
	final static String rfeedsc = "http://www.repubblica.it/rss/scienze/rss2.0.xml?ref=HRF-1"; //repubblica - scienze
	
	//final static String rfeedg = "http://www.repubblica.it/rss/gallerie/rss2.0.xml?ref=HRF-1"; //repubblica - gallerie
	
	final static String rfeedsp = "http://www.repubblica.it/rss/spettacoli/rss2.0.xml?ref=HRF-1"; //repubblica - spettacoli

	//final static String rfeed = "http://www.repubblica.it/rss/scuola_e_universita/rss2.0.xml?ref=HRF-1"; //repubblica - scuola e giovani
	
	final static String rfeedec = "http://www.repubblica.it/rss/economia/rss2.0.xml?ref=HRF-1"; //repubblica - economia
	
	
	/*-------TOPIC DEL CORRIERE DELLA SERA---------*/
	
	final static String cfeedc = "http://xml.corriereobjects.it/rss/cronache.xml";  //corriere - cronaca
	
	final static String cfeedp = "http://xml.corriereobjects.it/rss/politica.xml";  //corriere - politica

	final static String cfeede = "http://xml.corriereobjects.it/rss/esteri.xml";  //corriere - esteri
	
	final static String cfeedec = "http://xml.corriereobjects.it/rss/economia.xml";  //corriere - economia
		
	final static String cfeedcu = "http://xml.corriereobjects.it/rss/cultura.xml";  //corriere - cultura
	
	final static String cfeedsp = "http://xml.corriereobjects.it/rss/spettacoli.xml";  //corriere - spettacoli
	
	//final static String cfeedp1 = "http://xml.corriereobjects.it/rss/cinema.xml";  //corriere - cinema e tv
	
	final static String cfeedsc = "http://xml.corriereobjects.it/rss/scienze.xml";  //corriere - scienze
	
	final static String cfeeds = "http://xml.corriereobjects.it/rss/sport.xml";  //corriere - sport
	
	final static String cfeedm = "http://xml.corriereobjects.it/rss/motori.xml";  //corriere - motori

	final static String cfeeda = "http://xml.corriereobjects.it/rss/ambiente.xml";  //corriere - ambiente
	
	final static String cfeedan = "http://xml.corriereobjects.it/rss/animali.xml";  //corriere - animali
	

	
	/*----------TOPIC DELLA STAMPA----------*/
	
	final static String sfeedp = "http://www.lastampa.it/italia/politica/rss.xml"; //lastampa-politica
	
	final static String sfeede = "http://www.lastampa.it/esteri/rss.xml"; //lastampa-esteri
	
	final static String sfeedc = "http://www.lastampa.it/italia/cronache/rss.xml"; //lastampa-cronaca
	
	final static String sfeedt = "http://www.lastampa.it/tecnologia/rss.xml"; //lastampa-tecnologia
	
	final static String sfeeda = "http://www.lastampa.it/rss/tuttogreen"; //lastampa-ambiente
	
	final static String sfeedan = "http://www.lastampa.it/rss/lazampa"; //lastampa-animali
	
	final static String sfeedec = "http://www.lastampa.it/economia/rss.xml"; //lastampa-economia
	
	final static String sfeedcu = "http://www.lastampa.it/cultura/rss.xml"; //lastampa-cultura
	
	final static String sfeedsp = "http://www.lastampa.it/spettacoli/rss.xml"; //lastampa-spettacoli
	
	//final static String sfeede = "http://www.lastampa.it/societa/rss.xml"; //lastampa-costumi
	
	final static String sfeedm = "http://www.lastampa.it/motori/rss.xml"; //lastampa-motori
	
	final static String sfeeds = "http://www.lastampa.it/sport/rss.xml"; //lastampa-sport
	

	
	
	
	final static String rep = "Repubblica.it";
	final static String cor = "Corriere.it";
	final static String sta = "LaStampa.it";
	
	final static String s = "sport";
	final static String c = "cronaca";
	final static String p = "politica";
	final static String t = "tecnologia";
	final static String a = "ambiente";
	final static String e = "esteri";
	final static String m = "motori";
	final static String sc = "scienze";
	final static String sp = "spettacoli";
	final static String ec = "economia";
	final static String cu = "cultura";
	final static String an = "animali";
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, InterruptedException {
		
		
		
	  /*
		ArrayList<ArrayList> topics = new ArrayList();
		
		//topic politica
		ArrayList<String> politica = new ArrayList<String>(); 
		
		politica.add(rfeedp);
		politica.add(sfeedp);
		politica.add(cfeedp);
				
		ArrayList<String> sport = new ArrayList<String>();
		
		sport.add(rfeeds);
		sport.add(sfeeds);
		sport.add(cfeeds);
		
		ArrayList<String> cronaca = new ArrayList<String>();
		
		cronaca.add(rfeedc);
		cronaca.add(sfeedc);
		cronaca.add(cfeedc);
		
		ArrayList<String> ambiente = new ArrayList<String>();
		
		ambiente.add(rfeeda);
		ambiente.add(sfeeda);
		ambiente.add(cfeeda);
		
		ArrayList<String> esteri = new ArrayList<String>();
		
		esteri.add(rfeede);
		esteri.add(sfeede);
		esteri.add(cfeede);
		
		ArrayList<String> motori = new ArrayList<String>();
		
		motori.add(rfeedm);
		motori.add(sfeedm);
		motori.add(cfeedm);
		
		ArrayList<String> spettacoli = new ArrayList<String>();

		spettacoli.add(rfeedsp);
		spettacoli.add(sfeedsp);
		spettacoli.add(cfeedsp);
		
		ArrayList<String> economia = new ArrayList<String>();
		
		economia.add(rfeedec);
		economia.add(sfeedec);
		economia.add(cfeedec);
		
		ArrayList<String> cultura = new ArrayList<String>();
		
		cultura.add(sfeedcu);
		cultura.add(cfeedcu);
		
		ArrayList<String> animali = new ArrayList<String>();
		
		animali.add(sfeedan);
		animali.add(cfeedan);
		
		ArrayList<String> scienze = new ArrayList<String>();
		
		scienze.add(rfeedsc);
		scienze.add(cfeedsc);
		
		ArrayList<String> tecnologia = new ArrayList<String>();
		
		tecnologia.add(rfeedt);
		tecnologia.add(sfeedt);
		
		topics.add(politica);
		topics.add(sport);
		topics.add(cronaca);
		topics.add(tecnologia);
		topics.add(ambiente);
		topics.add(esteri);
		topics.add(motori);
		topics.add(scienze);
		topics.add(spettacoli);
		topics.add(cultura);
		topics.add(economia);
		topics.add(animali);
		
		*/
		
		//creazione arraylist

		list.add(new News(rfeeds, s, rep));
		list.add(new News(rfeedc, c, rep));
		list.add(new News(rfeedp, p, rep));
		list.add(new News(rfeedt, t, rep));
		list.add(new News(rfeeda, a, rep));
		list.add(new News(rfeede, e, rep));
		list.add(new News(rfeedm, m, rep));
		list.add(new News(rfeedsc, sc, rep));
		list.add(new News(rfeedsp, sp,rep));
		list.add(new News(rfeedec, ec, rep));
		
		list.add(new News(cfeeds, s, cor));
		list.add(new News(cfeedc, c, cor));
		list.add(new News(cfeedp, p, cor));
		list.add(new News(cfeeda, a, cor));
		list.add(new News(cfeede, e, cor));
		list.add(new News(cfeedm, m, cor));
		list.add(new News(cfeedsc, sc, cor));
		list.add(new News(cfeedsp, sp, cor));
		list.add(new News(cfeedec, ec, cor));
		list.add(new News(cfeedcu, cu, cor));
		list.add(new News(cfeedan, an, cor));
		
		list.add(new News(sfeeds, s, sta));
		list.add(new News(sfeedc, c, sta));
		list.add(new News(sfeedp, p, sta));
		list.add(new News(sfeedt, t, sta));
		list.add(new News(sfeeda, a, sta));
		list.add(new News(sfeede, e, sta));
		//list.add(new News(sfeedm, m, sta));
		list.add(new News(sfeedsp, sp, sta));
		list.add(new News(sfeedec, ec, sta));
		list.add(new News(sfeedcu, cu, sta));
		list.add(new News(sfeedan, an, sta));


		
		File f = new File ("mynterest.db");

		while(true)	{  //ciclo 


			//connessione db
			if(!f.exists()){
				con = DB.createDB();
			}	  
			else {
				Class.forName("org.sqlite.JDBC");
				con = DriverManager.getConnection("jdbc:sqlite:mynterest.db");
			}

			/*String templateDel= "Delete from News";

			PreparedStatement statDel=con.prepareStatement(templateDel);

			statDel.executeUpdate();*/


			for( News news : list){

				nc = new NewsCollector(con,news);

				nc.newsCollect();
			}  


			con.close();

			Thread.sleep(2000);
		}

		/*
    String templateInsert= "select date from News where topic='politica'";
	PreparedStatement statInsert=con.prepareStatement(templateInsert);
	ResultSet rs = statInsert.executeQuery();*/
		/*
	while(rs.next())	{

		System.out.println(rs.getTimestamp("date"));
	}*/

		/*
	 String templateInsert1= "SELECT title, date FROM news ORDER BY date DESC";
		PreparedStatement statInsert1=con.prepareStatement(templateInsert1);
		ResultSet rs1 = statInsert1.executeQuery();


		while(rs1.next())	{

			System.out.println(rs1.getString("title"));
			System.out.println(rs1.getTimestamp("date"));	
		}

		 */

	}
} 
