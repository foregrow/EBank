package demo.app.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Racun {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = false)
	private String brojRacuna;
	@Column(unique = false, nullable = false)
	private double stanje;
	@Column(unique = false, nullable = false)
	private Date datumKreiranja;
	@Column(unique = false, nullable = false)
	private boolean izbrisan;
	
	@OneToOne(mappedBy="racunZaUkidanje")
	private UkidanjeRacuna ukidanjeRacuna;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Banka banka;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Klijent klijent;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Valuta valuta;
	
	@OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "racun")
	private Set<DnevnoStanje> dnevnoStanje = new HashSet<DnevnoStanje>();
	
	public Racun() {
		
	}

	public Racun(long id, String brojRacuna, double stanje, Date datumKreiranja, boolean izbrisan,
			UkidanjeRacuna ukidanjeRacuna, Banka banka, Klijent klijent, Valuta valuta,
			Set<DnevnoStanje> dnevnoStanje) {
		super();
		this.id = id;
		this.brojRacuna = brojRacuna;
		this.stanje = stanje;
		this.datumKreiranja = datumKreiranja;
		this.izbrisan = izbrisan;
		this.ukidanjeRacuna = ukidanjeRacuna;
		this.banka = banka;
		this.klijent = klijent;
		this.valuta = valuta;
		this.dnevnoStanje = dnevnoStanje;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public double getStanje() {
		return stanje;
	}

	public void setStanje(double stanje) {
		this.stanje = stanje;
	}

	public Date getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

	public UkidanjeRacuna getUkidanjeRacuna() {
		return ukidanjeRacuna;
	}

	public void setUkidanjeRacuna(UkidanjeRacuna ukidanjeRacuna) {
		this.ukidanjeRacuna = ukidanjeRacuna;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

	public Valuta getValuta() {
		return valuta;
	}

	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}

	public Set<DnevnoStanje> getDnevnoStanje() {
		return dnevnoStanje;
	}

	public void setDnevnoStanje(Set<DnevnoStanje> dnevnoStanje) {
		this.dnevnoStanje = dnevnoStanje;
	}
	
	
	

}
