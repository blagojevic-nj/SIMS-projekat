package manageri;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.Proizvod;

public class ProizvodManager {
	private static class KolekcijaProizvoda {
		public ArrayList<Proizvod> proizvodi;
	}
	private HashMap<Integer, Proizvod> sviProizvodi;
	private ArrayList<String> naziviProizvoda; // trebace za autocomplete prilikom izbora sastojaka
	private boolean promenjen;
	private static ProizvodManager instance = null;
	
	private ProizvodManager() {
		sviProizvodi = new HashMap<Integer, Proizvod>();
		naziviProizvoda = new ArrayList<String>();
		ucitajProizvode("podaci/proizvodi.json");
		promenjen = false;
	}
	
	public static ProizvodManager getInstance() {
		if (instance == null) {
			instance = new ProizvodManager();
		}
		return instance;
	}
	
	private void ucitajProizvode(String fajl) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			KolekcijaProizvoda kolekcija = mapper.readValue(new File(fajl), KolekcijaProizvoda.class);
			naziviProizvoda.ensureCapacity(kolekcija.proizvodi.size());
			for (Proizvod p : kolekcija.proizvodi) {
				sviProizvodi.put(p.getSifra(), p);
				naziviProizvoda.set(p.getSifra(), p.getNaziv());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sacuvajProizvode(String fajl) {
		KolekcijaProizvoda kolekcija = new KolekcijaProizvoda();
		kolekcija.proizvodi = (ArrayList<Proizvod>) sviProizvodi.values();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(fajl), kolekcija);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Proizvod novProizvod(String naziv) {
		Proizvod proizvod = new Proizvod(sviProizvodi.size(), naziv);
		sviProizvodi.put(proizvod.getSifra(), proizvod);
		naziviProizvoda.add(naziv);
		promenjen = true;
		return proizvod;
	}
	
	public Proizvod getProizvod(int sifra) {
		return sviProizvodi.get(sifra);
	}
	
	public Proizvod getProizvod(String naziv) {
		return sviProizvodi.get(naziviProizvoda.indexOf(naziv));
	}

	public ArrayList<String> getNaziviProizvoda() {
		return naziviProizvoda;
	}

	public boolean isPromenjen() {
		return promenjen;
	}
}
