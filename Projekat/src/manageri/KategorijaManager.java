package manageri;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.Kategorija;

public class KategorijaManager {
	private static class KolekcijaKategorija {
		public ArrayList<Kategorija> kategorije = new ArrayList<Kategorija>();
	}
	private HashMap<Integer, Kategorija> sveKategorije;
	private ArrayList<String> naziviKategorija; // trebace za autocomplete prilikom izbora kategorija
	private boolean promenjen;
	private static KategorijaManager instance = null;
	static final String FAJL_SA_KATEGORIJAMA = "data/podaci/kategorije.json";
	
	private KategorijaManager() {
		sveKategorije = new HashMap<Integer, Kategorija>();
		naziviKategorija = new ArrayList<String>();
		ucitajKategorije();
		promenjen = false;
	}
	
	public static KategorijaManager getInstance() {
		if (instance == null) {
			instance = new KategorijaManager();
		}
		return instance;
	}
	
	private void ucitajKategorije() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			KolekcijaKategorija kolekcija = mapper.readValue(new File(FAJL_SA_KATEGORIJAMA), KolekcijaKategorija.class);
			for (int i = 0; i < kolekcija.kategorije.size(); i++) {
				naziviKategorija.add(null);
			}
			for (Kategorija k : kolekcija.kategorije) {
				sveKategorije.put(k.getSifra(), k);
				naziviKategorija.set(k.getSifra(), k.getNaziv());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sacuvajKategorije() {
		KolekcijaKategorija kolekcija = new KolekcijaKategorija();
		for (Kategorija k : sveKategorije.values()) {
			kolekcija.kategorije.add(k);
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(FAJL_SA_KATEGORIJAMA), kolekcija);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Kategorija novaKategorija(String naziv) {
		Kategorija kategorija = new Kategorija(sveKategorije.size(), naziv);
		sveKategorije.put(kategorija.getSifra(), kategorija);
		naziviKategorija.add(naziv);
		promenjen = true;
		return kategorija;
	}
	
	public Kategorija getKategorija(int sifra) {
		return sveKategorije.get(sifra);
	}
	
	public Kategorija getKategorija(String naziv) {
		return sveKategorije.get(naziviKategorija.indexOf(naziv));
	}

	public ArrayList<String> getNaziviKategorija() {
		return naziviKategorija;
	}

	public boolean isPromenjen() {
		return promenjen;
	}
}
