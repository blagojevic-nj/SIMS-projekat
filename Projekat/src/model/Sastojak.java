package model;

public class Sastojak {
	private int kolicina;
	private String mernaJedinica;
	private TipSastojka tip;
	private String nazivProizvoda;

	public Sastojak() {
	}

	public Sastojak(int kolicina, String mernaJedinica, TipSastojka tip, String nazivProizvoda) {
		super();
		this.kolicina = kolicina;
		this.mernaJedinica = mernaJedinica;
		this.tip = tip;
		this.nazivProizvoda = nazivProizvoda;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public String getMernaJedinica() {
		return mernaJedinica;
	}

	public void setMernaJedinica(String mernaJedinica) {
		this.mernaJedinica = mernaJedinica;
	}

	public TipSastojka getTip() {
		return tip;
	}

	public void setTip(TipSastojka tip) {
		this.tip = tip;
	}

	public String getNazivProizvoda() {
		return nazivProizvoda;
	}

	public void setNazivProizvoda(String nazivProizvoda) {
		this.nazivProizvoda = nazivProizvoda;
	}

}
