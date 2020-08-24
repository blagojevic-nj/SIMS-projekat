package manageri;

import java.util.Comparator;

import model.Recept;

public class ReceptSorter implements Comparator<Recept> {
	public enum Kriterijum {
		NAZIV, TEZINA, OCENA, DATUM, POPULARNOST;
	}
	private Kriterijum kriterijum;
	private int smer;
	
	public ReceptSorter(Kriterijum kriterijum, int smer) {
		super();
		this.kriterijum = kriterijum;
		this.smer = smer;
	}

	@Override
	public int compare(Recept r1, Recept r2) {
		int retVal = 0;
		switch (kriterijum) {
		case NAZIV:
			retVal = r1.getNaziv().compareTo(r2.getNaziv());
			break;
		case TEZINA:
			retVal = r1.getTezina().ordinal() - r2.getTezina().ordinal();
			break;
		case OCENA:
			retVal = (r1.getOcena() >= r2.getOcena())? 1 : -1;
			break;
		case DATUM:
			retVal = r1.getDatum().compareTo(r2.getDatum());
			break;
		case POPULARNOST:
			retVal = r1.getPregleda() - r2.getPregleda();
			break;
		default:
			break;
		}
		return retVal*smer;
	}
}
