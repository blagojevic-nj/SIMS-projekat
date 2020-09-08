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
		public ArrayList<Proizvod> proizvodi = new ArrayList<Proizvod>();
	}
	private HashMap<Integer, Proizvod> sviProizvodi;
	private ArrayList<String> naziviProizvoda; // trebace za autocomplete prilikom izbora sastojaka
	private boolean promenjen;
	static final String FAJL_SA_PROIZVODIMA = "data/podaci/proizvodi.json";
	
	public ProizvodManager() {
		sviProizvodi = new HashMap<Integer, Proizvod>();
		naziviProizvoda = new ArrayList<String>();
		ucitajProizvode();
		promenjen = false;
	}
	
	private void ucitajProizvode() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			KolekcijaProizvoda kolekcija = mapper.readValue(new File(FAJL_SA_PROIZVODIMA), KolekcijaProizvoda.class);
			for (int i = 0; i < kolekcija.proizvodi.size(); i++) {
				naziviProizvoda.add(null);
			}
			for (Proizvod p : kolekcija.proizvodi) {
				sviProizvodi.put(p.getSifra(), p);
				naziviProizvoda.set(p.getSifra(), p.getNaziv());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sacuvajProizvode() {
		KolekcijaProizvoda kolekcija = new KolekcijaProizvoda();
		for (Proizvod p : sviProizvodi.values()) {
			kolekcija.proizvodi.add(p);
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(FAJL_SA_PROIZVODIMA), kolekcija);
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
