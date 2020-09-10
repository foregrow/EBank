package demo.app.entity;

import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Nalog {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = true)
	private String primaoc;
	@Column(unique = false, nullable = true)
	private String svrhaPlacanja;
	@Column(unique = false, nullable = true)
	private String duznik;
	@Column(unique = false, nullable = true)
	private Date datumPrijema;
	@Column(unique = false, nullable = true)
	private Date datumValute;
	@Column(unique = false, nullable = true)
	private String modelZaduzenja;
	@Column(unique = false, nullable = true)
	private String pozivNaBrojZaduzenja;
	@Column(unique = false, nullable = true)
	private String modelOdobrenja;
	@Column(unique = false, nullable = true)
	private String pozivNaBrojOdobrenja;
	@Column(unique = false, nullable = true)
	private boolean hitno;
	@Column(unique = false, nullable = true)
	private double iznos;
	@Column(unique = false, nullable = true)
	private boolean status;
	@Column(unique = false, nullable = true)
	private String tipGreske;
	@OneToOne
	private Racun racunDuznika;
	@OneToOne
	private Racun racunPrimaoca;
	@Column(unique = false, nullable = true)
	private String vrstaPlacanja;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Drzava drzava;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private DnevnoStanje dnevnoStanje;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Valuta valuta;
	
	/*
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Poruka poruka;*/
	
	public Nalog() {
		
	}
	

	public Nalog(long id, String primaoc, String svrhaPlacanja, String duznik, Date datumPrijema, Date datumValute,
			String modelZaduzenja, String pozivNaBrojZaduzenja, String modelOdobrenja, String pozivNaBrojOdobrenja,
			boolean hitno, double iznos, boolean status, String tipGreske, Racun racunDuznika, Racun racunPrimaoca,
			String vrstaPlacanja, Drzava drzava, DnevnoStanje dnevnoStanje, Valuta valuta) {
		super();
		this.id = id;
		this.primaoc = primaoc;
		this.svrhaPlacanja = svrhaPlacanja;
		this.duznik = duznik;
		this.datumPrijema = datumPrijema;
		this.datumValute = datumValute;
		this.modelZaduzenja = modelZaduzenja;
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
		this.modelOdobrenja = modelOdobrenja;
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
		this.hitno = hitno;
		this.iznos = iznos;
		this.status = status;
		this.tipGreske = tipGreske;
		this.racunDuznika = racunDuznika;
		this.racunPrimaoca = racunPrimaoca;
		this.vrstaPlacanja = vrstaPlacanja;
		this.drzava = drzava;
		this.dnevnoStanje = dnevnoStanje;
		this.valuta = valuta;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPrimaoc() {
		return primaoc;
	}

	public void setPrimaoc(String primaoc) {
		this.primaoc = primaoc;
	}

	public String getSvrhaPlacanja() {
		return svrhaPlacanja;
	}

	public void setSvrhaPlacanja(String svrhaPlacanja) {
		this.svrhaPlacanja = svrhaPlacanja;
	}

	public String getDuznik() {
		return duznik;
	}

	public void setDuznik(String duznik) {
		this.duznik = duznik;
	}

	public Date getDatumPrijema() {
		return datumPrijema;
	}

	public void setDatumPrijema(Date datumPrijema) {
		this.datumPrijema = datumPrijema;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public String getModelZaduzenja() {
		return modelZaduzenja;
	}

	public void setModelZaduzenja(String modelZaduzenja) {
		this.modelZaduzenja = modelZaduzenja;
	}

	public String getPozivNaBrojZaduzenja() {
		return pozivNaBrojZaduzenja;
	}

	public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
	}

	public String getModelOdobrenja() {
		return modelOdobrenja;
	}

	public void setModelOdobrenja(String modelOdobrenja) {
		this.modelOdobrenja = modelOdobrenja;
	}

	public String getPozivNaBrojOdobrenja() {
		return pozivNaBrojOdobrenja;
	}

	public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
	}

	public boolean isHitno() {
		return hitno;
	}

	public void setHitno(boolean hitno) {
		this.hitno = hitno;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTipGreske() {
		return tipGreske;
	}

	public void setTipGreske(String tipGreske) {
		this.tipGreske = tipGreske;
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

	public String getVrstaPlacanja() {
		return vrstaPlacanja;
	}

	public void setVrstaPlacanja(String vrstaPlacanja) {
		this.vrstaPlacanja = vrstaPlacanja;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

	public DnevnoStanje getDnevnoStanje() {
		return dnevnoStanje;
	}

	public void setDnevnoStanje(DnevnoStanje dnevnoStanje) {
		this.dnevnoStanje = dnevnoStanje;
	}

	public Valuta getValuta() {
		return valuta;
	}

	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}
	
	
}
