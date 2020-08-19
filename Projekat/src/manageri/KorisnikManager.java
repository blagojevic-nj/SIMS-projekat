package manageri;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.Bedz;
import model.Korisnik;
import model.Nalog;
import model.RegistrovaniKorisnik;
import model.TipNaloga;

public class KorisnikManager {
	private static class KolekcijaNaloga {
		public ArrayList<Nalog> nalozi = new ArrayList<Nalog>();
	}
	private HashMap<String, Nalog> sviNalozi;
	private HashMap<Integer, Korisnik> ucitaniKorisnici;
	private ArrayList<Nalog> promenjeniNalozi;
	private static KorisnikManager instance = null;
	static final String FOLDER_SA_KORISNICIMA = "data/korisnici";
	static final String FAJL_SA_NALOZIMA = FOLDER_SA_KORISNICIMA+"/nalozi.json";
	
	private KorisnikManager() {
		sviNalozi = new HashMap<String, Nalog>();
		ucitaniKorisnici = new HashMap<Integer, Korisnik>();
		promenjeniNalozi = new ArrayList<Nalog>();
		ucitajNaloge();
	}
	
	public static KorisnikManager getInstance() {
		if (instance == null) {
			instance = new KorisnikManager();
		}
		return instance;
	}
	
	private void ucitajNaloge() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			KolekcijaNaloga kolekcija = mapper.readValue(new File(FAJL_SA_NALOZIMA), KolekcijaNaloga.class);
			for (Nalog n : kolekcija.nalozi) {
				sviNalozi.put(n.getKorisnickoIme(), n);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sacuvajNaloge() {
		KolekcijaNaloga kolekcija = new KolekcijaNaloga();
		for (Nalog n : sviNalozi.values()) {
			kolekcija.nalozi.add(n);
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(FAJL_SA_NALOZIMA), kolekcija);
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
	
	public boolean ucitajKorisnika(String fajl, TipNaloga tip) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (tip == TipNaloga.REG_KORISNIK) {
				RegistrovaniKorisnik rk = mapper.readValue(new File(fajl), RegistrovaniKorisnik.class);
				ucitaniKorisnici.put(rk.getId(), rk);
			}
			else {
				Korisnik k = mapper.readValue(new File(fajl), Korisnik.class);
				ucitaniKorisnici.put(k.getId(), k);
			}
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void sacuvajKorisnike() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			for (Nalog n : promenjeniNalozi) {
				mapper.writeValue(new File(FOLDER_SA_KORISNICIMA+"/"+n.getIdKorisnika()+".json"), 
						ucitaniKorisnici.get(n.getIdKorisnika()));
			}
			promenjeniNalozi.clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RegistrovaniKorisnik registracijaKorisnika(String ime, String prezime, String email, String korisnickoIme, String lozinka) {
		RegistrovaniKorisnik r = new RegistrovaniKorisnik(sviNalozi.size(), ime, prezime, email, korisnickoIme, false, Bedz.NEMA, 0, 0);
		Nalog nalog = new Nalog(korisnickoIme, lozinka, TipNaloga.REG_KORISNIK, r.getId());
		sviNalozi.put(korisnickoIme, nalog);
		promenjeniNalozi.add(nalog);
		ucitaniKorisnici.put(r.getId(), r);
		return r;
	}
	
	public Korisnik registracijaAdmina(String ime, String prezime, String email, String korisnickoIme, String lozinka) {
		Korisnik a = new Korisnik(sviNalozi.size(), ime, prezime, email, korisnickoIme);
		Nalog nalog = new Nalog(korisnickoIme, lozinka, TipNaloga.ADMIN, a.getId());
		sviNalozi.put(korisnickoIme, nalog);
		promenjeniNalozi.add(nalog);
		ucitaniKorisnici.put(a.getId(), a);
		return a;
	}
	
	public Korisnik registracijaModeratora(String ime, String prezime, String email, String korisnickoIme, String lozinka) {
		Korisnik m = new Korisnik(sviNalozi.size(), ime, prezime, email, korisnickoIme);
		Nalog nalog = new Nalog(korisnickoIme, lozinka, TipNaloga.MODERATOR, m.getId());
		sviNalozi.put(korisnickoIme, nalog);
		promenjeniNalozi.add(nalog);
		ucitaniKorisnici.put(m.getId(), m);
		return m;
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
	
	public boolean validnoKorisnickoIme(String korisnickoIme) {
		if (korisnickoIme.matches("[a-zA-Z0-9._]{6,30}")) {
			if (sviNalozi.get(korisnickoIme) != null) {
				return false;
			}
			return true;
		}
		return false;
	}
}
