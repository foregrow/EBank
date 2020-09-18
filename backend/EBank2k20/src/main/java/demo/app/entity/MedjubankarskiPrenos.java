package demo.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import demo.app.enums.TipTransfera;

@Entity
public class MedjubankarskiPrenos {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = false)
	private double iznos;
	@Column(unique = false, nullable = false)
	private String valuta;
	@Column(unique = false, nullable = false)
	private TipTransfera tipTransfera;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Banka bankaDuznika;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Banka bankaPrimaoca;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Racun racunDuznika;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Racun racunPrimaoca;
	
	public MedjubankarskiPrenos() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	public Banka getBankaDuznika() {
		return bankaDuznika;
	}

	public void setBankaDuznika(Banka bankaDuznika) {
		this.bankaDuznika = bankaDuznika;
	}

	public Banka getBankaPrimaoca() {
		return bankaPrimaoca;
	}

	public void setBankaPrimaoca(Banka bankaPrimaoca) {
		this.bankaPrimaoca = bankaPrimaoca;
	}

	public Racun getRacunDuznika() {
		return racunDuznika;
	}

	public void setRacunDuznika(Racun racunDuznika) {
		this.racunDuznika = racunDuznika;
	}

	public Racun getRacunPrimaoca() {
		return racunPrimaoca;
	}

	public void setRacunPrimaoca(Racun racunPrimaoca) {
		this.racunPrimaoca = racunPrimaoca;
	}

	public TipTransfera getTipTransfera() {
		return tipTransfera;
	}

	public void setTipTransfera(TipTransfera tipTransfera) {
		this.tipTransfera = tipTransfera;
	}
	
	
}
