package manageri;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.Recept;
import model.RegistrovaniKorisnik;
import model.Sastojak;
import model.Tezina;
import model.UredjajUReceptu;

public class ReceptManager {
	private static class TabelaRecepata {
		public HashMap<String, Integer> recepti;
		
		public ArrayList<Integer> pretraziPoNazivu(String tekst) {
			ArrayList<Integer> rezultat = new ArrayList<Integer>();
			tekst = tekst.trim().toLowerCase();
			if (tekst.equals(""))
				return rezultat;
			for (String naziv : recepti.keySet()) {
				if (naziv.contains(tekst)) {
					rezultat.add(recepti.get(naziv));
				}
			}
			return rezultat;
		}
	}
	private static TabelaRecepata tabela;
	private static boolean tabelaUcitana;
	private static HashMap<Integer, Recept> ucitaniRecepti;
	private static ArrayList<Recept> promenjeniRecepti;
	private static ReceptManager instance = new ReceptManager();
	static final String FOLDER_SA_RECEPTIMA = "recepti";
	
	private ReceptManager() {
		promenjeniRecepti = new ArrayList<Recept>();
		tabelaUcitana = false;
	}
	
	public static ReceptManager getInstance() {
		if (instance == null) {
			instance = new ReceptManager();
		}
		return instance;
	}
	
	public static void ucitajTabelu(String fajl) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			tabela = mapper.readValue(new File(fajl), TabelaRecepata.class);
			tabelaUcitana = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean ucitajRecept(String fajl) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Recept recept = mapper.readValue(new File(fajl), Recept.class);
			ucitaniRecepti.put(recept.getId(), recept);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void sacuvajTabelu(String fajl) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(fajl), tabela);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sacuvajRecepte() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			for (Recept r : promenjeniRecepti) {
				mapper.writeValue(new File(FOLDER_SA_RECEPTIMA+"/"+r.getId()+".json"), r);
			}
			promenjeniRecepti.clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Recept noviRecept(String naziv, String opis, String koraci, Tezina tezina, int vremePripreme, 
			ArrayList<UredjajUReceptu> uredjaji, ArrayList<Sastojak> sastojci, ArrayList<Integer> kategorije, 
			RegistrovaniKorisnik autor, String youtubeLink) {
		if (!tabelaUcitana) {
			ucitajTabelu(FOLDER_SA_RECEPTIMA+"/tabela.json");
		}
		int i = 1;
		while (tabela.recepti.get(naziv.toLowerCase()) != null) {
			naziv = naziv+" "+i;
			i++;
		}
		Recept recept = new Recept(tabela.recepti.size(), vremePripreme, naziv, opis, koraci,
				youtubeLink, 0, tezina, false, LocalDate.now(), autor);
		recept.setUredjaji(uredjaji);
		recept.setSastojci(sastojci);
		recept.setKategorije(kategorije);
		tabela.recepti.put(naziv.toLowerCase(), recept.getId());
		ucitaniRecepti.put(recept.getId(), recept);
		promenjeniRecepti.add(recept);
		return recept;
	}
	
	public Recept getRecept(int sifra) {
		Recept recept = ucitaniRecepti.get(sifra);
		if (recept == null) {	// page fault
			ucitajRecept(FOLDER_SA_RECEPTIMA+"/"+sifra+".json");
			recept = ucitaniRecepti.get(sifra);
		}
		return recept;
	}
	
	public ArrayList<Recept> getRecepti(ArrayList<Integer> sifre) {
		ArrayList<Recept> rezultat = new ArrayList<Recept>();
		for (int sifra : sifre) {
			rezultat.add(getRecept(sifra));
		}
		return rezultat;
	}
	
	public ArrayList<Recept> pretraziPoNazivu(String tekst) {
		if (!tabelaUcitana) {
			ucitajTabelu(FOLDER_SA_RECEPTIMA+"/tabela.json");
		}
		return getRecepti(tabela.pretraziPoNazivu(tekst));
	}
	
	public ArrayList<Recept> pretraziPoKriterijumima() {
		// TODO (implementiracu kasnije)
		return null;
	}
}
