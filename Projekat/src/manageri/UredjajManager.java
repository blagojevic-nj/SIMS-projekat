package manageri;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.Uredjaj;

public class UredjajManager {
	private static class KolekcijaUredjaja {
		public ArrayList<Uredjaj> uredjaji;
	}
	private HashMap<Integer, Uredjaj> sviUredjaji;
	private ArrayList<String> naziviUredjaja; // trebace za autocomplete prilikom izbora uredjaja
	private boolean promenjen;
	private static UredjajManager instance = null;
	
	private UredjajManager() {
		sviUredjaji = new HashMap<Integer, Uredjaj>();
		naziviUredjaja = new ArrayList<String>();
		ucitajUredjaje("podaci/uredjaji.json");
		promenjen = false;
	}
	
	public static UredjajManager getInstance() {
		if (instance == null) {
			instance = new UredjajManager();
		}
		return instance;
	}
	
	public void ucitajUredjaje(String fajl) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			KolekcijaUredjaja kolekcija = mapper.readValue(new File(fajl), KolekcijaUredjaja.class);
			naziviUredjaja.ensureCapacity(kolekcija.uredjaji.size());
			for (Uredjaj u : kolekcija.uredjaji) {
				sviUredjaji.put(u.getSifra(), u);
				naziviUredjaja.set(u.getSifra(), u.getNaziv());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sacuvajUredjaje(String fajl) {
		KolekcijaUredjaja kolekcija = new KolekcijaUredjaja();
		kolekcija.uredjaji = (ArrayList<Uredjaj>) sviUredjaji.values();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(fajl), kolekcija);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Uredjaj novUredjaj(String naziv) {
		Uredjaj uredjaj = new Uredjaj(sviUredjaji.size(), naziv);
		sviUredjaji.put(uredjaj.getSifra(), uredjaj);
		naziviUredjaja.add(naziv);
		promenjen = true;
		return uredjaj;
	}
	
	public Uredjaj getUredjaj(int sifra) {
		return sviUredjaji.get(sifra);
	}
	
	public Uredjaj getUredjaj(String naziv) {
		return sviUredjaji.get(naziviUredjaja.indexOf(naziv));
	}

	public ArrayList<String> getNaziviUredjaja() {
		return naziviUredjaja;
	}

	public boolean isPromenjen() {
		return promenjen;
	}
}
