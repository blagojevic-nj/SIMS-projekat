package manageri;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.Korisnik;
import model.Nalog;
import model.RegistrovaniKorisnik;
import model.TipNaloga;

public class KorisnikManager {
	private static class KolekcijaNaloga {
		public ArrayList<Nalog> nalozi;
	}
	private static HashMap<String, Nalog> sviNalozi;
	private static HashMap<Integer, Korisnik> ucitaniKorisnici;
	private static ArrayList<Nalog> promenjeniNalozi;
	private static KorisnikManager instance = new KorisnikManager();
	static final String FOLDER_SA_KORISNICIMA = "korisnici";
	
	private KorisnikManager() {
		promenjeniNalozi = new ArrayList<Nalog>();
	}
	
	public static KorisnikManager getInstance() {
		if (instance == null) {
			instance = new KorisnikManager();
		}
		return instance;
	}
	
	public static void ucitajNaloge(String fajl) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			KolekcijaNaloga kolekcija = mapper.readValue(new File(fajl), KolekcijaNaloga.class);
			for (Nalog n : kolekcija.nalozi) {
				sviNalozi.put(n.getKorisnickoIme(), n);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sacuvajNaloge(String fajl) {
		KolekcijaNaloga kolekcija = new KolekcijaNaloga();
		kolekcija.nalozi = (ArrayList<Nalog>) sviNalozi.values();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(fajl), kolekcija);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean postoji(String korisnickoIme, String lozinka) {
		Nalog nalog = sviNalozi.get(korisnickoIme);
		if (nalog != null)
			if (nalog.getLozinka().equals(lozinka))
				return true;
		return false;
	}
	
	public static boolean ucitajKorisnika(String fajl, TipNaloga tip) {
		// TODO
		return false;
	}
	
	public static void sacuvajKorisnike() {
		// TODO
	}
	
	public RegistrovaniKorisnik registracijaKorisnika() {
		// TODO
		return null;
	}
	
	public Korisnik registracijaAdmina() {
		// TODO
		return null;
	}
	
	public Korisnik registracijaModeratora() {
		// TODO
		return null;
	}
	
	public Korisnik getKorisnik(String korisnickoIme) {
		Nalog nalog = sviNalozi.get(korisnickoIme);
		if (nalog == null) {
			return null;
		}
		Korisnik korisnik = ucitaniKorisnici.get(nalog.getIdKorisnika());
		if (korisnik == null) {	// page fault
			ucitajKorisnika(FOLDER_SA_KORISNICIMA+"/"+nalog.getIdKorisnika()+".json", nalog.getTip());
			korisnik = ucitaniKorisnici.get(nalog.getIdKorisnika());
		}
		return korisnik;
	}
	
	public boolean isPromenjen() {
		return !promenjeniNalozi.isEmpty();
	}
}
