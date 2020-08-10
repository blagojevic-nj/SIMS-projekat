package model;

public class Sastojak {
	private int kolicina;
	private String mernaJedinica;
	private TipSastojka tip;
	private Recept Recept;
	private Proizvod proizvod;
	
	public Sastojak(int kolicina, String mernaJedinica, TipSastojka tip, model.Recept recept, Proizvod proizvod) {
		super();
		this.kolicina = kolicina;
		this.mernaJedinica = mernaJedinica;
		this.tip = tip;
		Recept = recept;
		this.proizvod = proizvod;
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
	public Recept getRecept() {
		return Recept;
	}
	public void setRecept(Recept recept) {
		Recept = recept;
	}
	public Proizvod getProizvod() {
		return proizvod;
	}
	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}
}
