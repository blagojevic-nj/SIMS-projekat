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
		public ArrayList<Kategorija> kategorije;
	}
	private static HashMap<Integer, Kategorija> sveKategorije;
	private static ArrayList<String> naziviKategorija; // trebace za autocomplete prilikom izbora kategorija
	private static boolean promenjen;
	private static KategorijaManager instance = new KategorijaManager();
	
	private KategorijaManager() {
		ucitajKategorije("podaci/kategorije.json");
		promenjen = false;
	}
	
	public static KategorijaManager getInstance() {
		if (instance == null) {
			instance = new KategorijaManager();
		}
		return instance;
	}
	
	public static void ucitajKategorije(String fajl) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			KolekcijaKategorija kolekcija = mapper.readValue(new File(fajl), KolekcijaKategorija.class);
			naziviKategorija.ensureCapacity(kolekcija.kategorije.size());
			for (Kategorija k : kolekcija.kategorije) {
				sveKategorije.put(k.getSifra(), k);
				naziviKategorija.set(k.getSifra(), k.getNaziv());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sacuvajKategorije(String fajl) {
		KolekcijaKategorija kolekcija = new KolekcijaKategorija();
		kolekcija.kategorije = (ArrayList<Kategorija>) sveKategorije.values();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(fajl), kolekcija);
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
