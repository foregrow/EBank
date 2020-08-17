package demo.app.web.dto;

import java.util.Date;

import demo.app.entity.Nalog;

public class NalogDTO {

	
	private long id;
	private String primaoc;
	private String svrhaPlacanja;
	private String duznik;
	private Date datumPrijema;
	private Date datumValute;
	private String modelZaduzenja;
	private String pozivNaBrojZaduzenja;
	private String modelOdobrenja;
	private String pozivNaBrojOdobrenja;
	private boolean hitno;
	private double iznos;
	private boolean status;
	private String tipGreske;
	private RacunDTO racunDuznika;
	private RacunDTO racunPrimaoca;
	private String vrstaPlacanja;
	
	private DrzavaDTO drzava;
	private DnevnoStanjeDTO dnevnoStanje;
	private ValutaDTO valuta;
	
	/*
	private Poruka poruka;*/
	
	public NalogDTO() {
		
	}
	public NalogDTO(Nalog obj) {
		id = obj.getId();
		primaoc = obj.getPrimaoc();
		svrhaPlacanja = obj.getSvrhaPlacanja();
		duznik = obj.getDuznik();
		datumPrijema = obj.getDatumPrijema();
		datumValute = obj.getDatumValute();
		modelZaduzenja = obj.getModelZaduzenja();
		pozivNaBrojZaduzenja = obj.getPozivNaBrojZaduzenja();
		modelOdobrenja = obj.getModelOdobrenja();
		hitno = obj.isHitno();
		iznos = obj.getIznos();
		status = obj.isStatus();
		tipGreske = obj.getTipGreske();
		vrstaPlacanja = obj.getVrstaPlacanja();
		
		if(obj.getRacunDuznika() != null)
			racunDuznika = new RacunDTO(obj.getRacunDuznika());
		if(obj.getRacunPrimaoca() != null)
			racunDuznika = new RacunDTO(obj.getRacunPrimaoca());
		if(obj.getDrzava() != null)
			drzava = new DrzavaDTO(obj.getDrzava());
		if(obj.getDnevnoStanje() != null)
			dnevnoStanje = new DnevnoStanjeDTO(obj.getDnevnoStanje());
		if(obj.getValuta() != null)
			valuta = new ValutaDTO(obj.getValuta());
	}
	

	public NalogDTO(long id, String primaoc, String svrhaPlacanja, String duznik, Date datumPrijema, Date datumValute,
			String modelZaduzenja, String pozivNaBrojZaduzenja, String modelOdobrenja, String pozivNaBrojOdobrenja,
			boolean hitno, double iznos, boolean status, String tipGreske, RacunDTO racunDuznika, RacunDTO racunPrimaoca,
			String vrstaPlacanja, DrzavaDTO drzava, DnevnoStanjeDTO dnevnoStanje, ValutaDTO valuta) {
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

	public RacunDTO getRacunDuznika() {
		return racunDuznika;
	}

	public void setRacunDuznika(RacunDTO racunDuznika) {
		this.racunDuznika = racunDuznika;
	}

	public RacunDTO getRacunPrimaoca() {
		return racunPrimaoca;
	}

	public void setRacunPrimaoca(RacunDTO racunPrimaoca) {
		this.racunPrimaoca = racunPrimaoca;
	}

	public String getVrstaPlacanja() {
		return vrstaPlacanja;
	}

	public void setVrstaPlacanja(String vrstaPlacanja) {
		this.vrstaPlacanja = vrstaPlacanja;
	}

	public DrzavaDTO getDrzava() {
		return drzava;
	}

	public void setDrzava(DrzavaDTO drzava) {
		this.drzava = drzava;
	}

	public DnevnoStanjeDTO getDnevnoStanje() {
		return dnevnoStanje;
	}

	public void setDnevnoStanje(DnevnoStanjeDTO dnevnoStanje) {
		this.dnevnoStanje = dnevnoStanje;
	}

	public ValutaDTO getValuta() {
		return valuta;
	}

	public void setValuta(ValutaDTO valuta) {
		this.valuta = valuta;
	}
	
	
}
