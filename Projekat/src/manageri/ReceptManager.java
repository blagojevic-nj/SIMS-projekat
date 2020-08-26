package manageri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.Kategorija;
import model.Proizvod;
import model.Recept;
import model.RegistrovaniKorisnik;
import model.Sastojak;
import model.Tezina;
import model.TipOcene;
import model.UredjajUReceptu;

public class ReceptManager {
	private static class TabelaRecepata {
		public HashMap<String, Integer> recepti = new HashMap<String, Integer>();	// {naziv recepta sa malim slovima : sifra recepta}
		public ArrayList<Integer> najpopularniji = new ArrayList<Integer>();	// sadrzi sifre max 10 najpopularnijih recepata
		
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
		
		public void azurirajPopularnost(int sifra, int mesto) {
			najpopularniji.remove(new Integer(sifra));
			najpopularniji.add(mesto, sifra);
		}
	}
	private TabelaRecepata tabela;
	private HashMap<Integer, Recept> ucitaniRecepti;
	private ArrayList<Recept> promenjeniRecepti;	// ovi recepti ce biti upisani nazad u fajl
	private static ReceptManager instance = null;
	static final String FOLDER_SA_RECEPTIMA = "data/recepti";
	static final String FOLDER_SA_SLIKAMA = FOLDER_SA_RECEPTIMA+"/slike";
	static final String FAJL_SA_TABELOM = FOLDER_SA_RECEPTIMA+"/tabela.json";
	
	private ReceptManager() {
		ucitaniRecepti = new HashMap<Integer, Recept>();
		promenjeniRecepti = new ArrayList<Recept>();
		ucitajTabelu();
	}
	
	public static ReceptManager getInstance() {
		if (instance == null) {
			instance = new ReceptManager();
		}
		return instance;
	}
	
	private void ucitajTabelu() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			tabela = mapper.readValue(new File(FAJL_SA_TABELOM), TabelaRecepata.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean ucitajRecept(String fajl) {
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
	
	public void sacuvajTabelu() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(FAJL_SA_TABELOM), tabela);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sacuvajRecepte() {
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
	
	// metoda za dodavanje novog recepta
	public Recept noviRecept(String naziv, String opis, String koraci, Tezina tezina, int vremePripreme, 
			ArrayList<UredjajUReceptu> uredjaji, ArrayList<Sastojak> sastojci, ArrayList<Integer> kategorije, 
			RegistrovaniKorisnik autor, String youtubeLink) {
		int i = 1;
		if (tabela.recepti.get(naziv.toLowerCase()) != null) {
			while (tabela.recepti.get(naziv.toLowerCase() + " " + i) != null)
				i++;
			naziv += " " + i;
		}
		Recept recept = new Recept(tabela.recepti.size(), vremePripreme, naziv, opis, koraci,
				youtubeLink, 0, tezina, false, LocalDate.now(), autor, 0);
		recept.setUredjaji(uredjaji);
		recept.setSastojci(sastojci);
		recept.setKategorije(kategorije);
		tabela.recepti.put(naziv.toLowerCase(), recept.getId());
		ucitaniRecepti.put(recept.getId(), recept);
		promenjeniRecepti.add(recept);
		return recept;
	}
	
	// vraca recept sa odredjenom sifrom
	public Recept getRecept(int sifra) {
		Recept recept = ucitaniRecepti.get(sifra);
		if (recept == null) {	// page fault
			ucitajRecept(FOLDER_SA_RECEPTIMA+"/"+sifra+".json");
			recept = ucitaniRecepti.get(sifra);
		}
		return recept;
	}
	
	// vraca kolekciju recepata na osnovu kolekcije sifara
	public ArrayList<Recept> getRecepti(ArrayList<Integer> sifre) {
		ArrayList<Recept> rezultat = new ArrayList<Recept>();
		for (int sifra : sifre) {
			rezultat.add(getRecept(sifra));
		}
		return rezultat;
	}
	
	// vraca recepte ciji nazivi sadrze zadati tekst
	public ArrayList<Recept> pretraziPoNazivu(String tekst) {
		return getRecepti(tabela.pretraziPoNazivu(tekst));
	}
	
	// detaljna pretraga
	public ArrayList<Recept> pretraziPoKriterijumima(ArrayList<Proizvod> proizvodi, boolean sviUReceptu,
			ArrayList<Proizvod> nepozeljni, ArrayList<Kategorija> kategorije, int maxVreme) {
		HashSet<Integer> medjuRezultat = new HashSet<Integer>();
		if (proizvodi == null) {
			medjuRezultat.addAll(tabela.recepti.values());
		} else if (proizvodi.size() == 0) {
			medjuRezultat.addAll(tabela.recepti.values());
		} else {
			if (sviUReceptu) {
				medjuRezultat.addAll(tabela.recepti.values());
				for (Proizvod p : proizvodi) {
					medjuRezultat.retainAll(p.getRecepti());
				}
			} else {
				for (Proizvod p : proizvodi) {
					medjuRezultat.addAll(p.getRecepti());
				}
			}
		}
		if (nepozeljni != null) {
			for (Proizvod p : nepozeljni) {
				medjuRezultat.removeAll(p.getRecepti());
			} 
		}
		if (kategorije != null) {
			if (kategorije.size() != 0) {
				HashSet<Integer> katRezultat = new HashSet<Integer>();
				for (Kategorija k : kategorije) {
					katRezultat.addAll(k.getRecepti());
				}
				medjuRezultat.retainAll(katRezultat);
			}
		}
		ArrayList<Recept> rezultat = new ArrayList<Recept>();
		for (int sifra : medjuRezultat) {
			Recept r = getRecept(sifra);
			if (r.getVremePripreme() <= maxVreme) {
				rezultat.add(r);
			}
		}
		return rezultat;
	}
	
	// vraca najnovije recepte za pocetnu stranicu
	public ArrayList<Recept> getNajnovijih10() {
		ArrayList<Recept> rezultat = new ArrayList<Recept>();
		int sifra = tabela.recepti.size()-1;	// sifra najnovijeg recepta
		int i = 0;	// brojac
		while (sifra >= 0 && i < 10) {
			Recept r = getRecept(sifra);
//	dok nema moderatora		
//			if (r.isUredjen()) {
//				rezultat.add(r);
//				i++;
//			}
			rezultat.add(r);
			i++;
			sifra--;
		}
		return rezultat;
	}
	
	// vraca najpopularnije recepte za pocetnu stranicu
	public ArrayList<Recept> getNajpopularnijih10() {
		return getRecepti(tabela.najpopularniji);
	}
	
	// ovu metodu treba pozivati nakon sto neko pregleda recept
	// inkrementuje se broj pregleda i azurira se popularnost
	public void pregledaoRecept(Recept recept, RegistrovaniKorisnik korisnik) {
		if (korisnik == null) {	// ako je neregistrovani
			recept.inkrementPregled();
			promenjeniRecepti.add(recept);
		} else if (korisnik.getKorisnickoIme() != recept.getAutor()) {	// ako recept nije svoj
			recept.inkrementPregled();
			promenjeniRecepti.add(recept);
		} else {
			return;
		}
		int mesto = tabela.najpopularniji.size();
		// uporedjuje se popularnost aktuelnog recepta sa popularnoscu ostalih najpopularnijih recepata
		while (mesto > 0) {
			Recept r = getRecept(tabela.najpopularniji.get(mesto-1));
			if (recept.getPregleda() >= r.getPregleda()) {
				mesto--;
			} else {
				break;
			}
		}
		if (mesto < 10)
			tabela.azurirajPopularnost(recept.getId(), mesto);
	}
	
	// ova metoda kopira izabranu sliku u folder sa slikama pod nazivom {sifraRecepta}.png
	public static void dodajSliku(int sifraRecepta, String putanjaDoSlike) {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(new File(putanjaDoSlike));
			fileOutputStream = new FileOutputStream(new File(FOLDER_SA_SLIKAMA+"/"+sifraRecepta+".png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int bufferSize;
		byte[] buffer = new byte[1024];
		try {
			while ((bufferSize = fileInputStream.read(buffer)) > 0) {
			    fileOutputStream.write(buffer, 0, bufferSize);
			}
			fileInputStream.close();
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getPutanjaDoSlike(int sifraRecepta) {
		String putanja = FOLDER_SA_SLIKAMA+"/"+sifraRecepta+".png";
		File f = new File(putanja);
		if (f.exists())
			return putanja;
		return FOLDER_SA_SLIKAMA+"/default.png";
	}
	
	public void promenjen(Recept r) {
		promenjeniRecepti.add(r);
	}
	
	
	public static String dateTimeToString(LocalDateTime dt) {
		String retVal = "";
		retVal += dt.getDayOfMonth() + "-" + dt.getMonthValue() + "-" + dt.getYear() + " u " + dt.getHour() + ":" + dt.getMinute();
		return retVal;
	}
	
	public void prosekOcenaRecepta(Recept recept) {
		float prosek = 0;
		float count = 0;
		for (TipOcene ocena : recept.getOcene().values()) {
			switch(ocena) {
				case JEDAN:
					prosek++;
					break;
				case DVA:
					prosek += 2;
					break;
				case TRI:
					prosek += 3;
					break;
				case CETIRI:
					prosek += 4;
					break;
				case PET:
					prosek += 5;
					break;
			}
			count++;
		}
		recept.setOcena(prosek / count);
	}
}

