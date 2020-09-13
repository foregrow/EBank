package demo.app.web.dto;

import java.util.Date;

public class IzvestajDnevnoStanjeRacunDTO {

	private String brojIzvoda;
	private Date datumPrometa;
	private double novoStanje;
	private double prethodnoStanje; //dnevnoStanje dovde
	private double iznos;
	private String duznik;
	private String primaoc;
	private String svrhaPlacanja;
	private String vrstaPlacanja;
	private String valuta;
	
	public IzvestajDnevnoStanjeRacunDTO() {
		
	}
	
	public IzvestajDnevnoStanjeRacunDTO(String brojIzvoda, Date datumPrometa, double novoStanje, double prethodnoStanje,
			double iznos, String duznik, String promaoc, String svrhaPlacanja, String vrstaPlacanja, String valuta) {
		super();
		this.brojIzvoda = brojIzvoda;
		this.datumPrometa = datumPrometa;
		this.novoStanje = novoStanje;
		this.prethodnoStanje = prethodnoStanje;
		this.iznos = iznos;
		this.duznik = duznik;
		this.primaoc = promaoc;
		this.svrhaPlacanja = svrhaPlacanja;
		this.vrstaPlacanja = vrstaPlacanja;
		this.valuta = valuta;
	}

	public String getBrojIzvoda() {
		return brojIzvoda;
	}

	public void setBrojIzvoda(String brojIzvoda) {
		this.brojIzvoda = brojIzvoda;
	}

	public Date getDatumPrometa() {
		return datumPrometa;
	}

	public void setDatumPrometa(Date datumPrometa) {
		this.datumPrometa = datumPrometa;
	}

	public double getNovoStanje() {
		return novoStanje;
	}

	public void setNovoStanje(double novoStanje) {
		this.novoStanje = novoStanje;
	}

	public double getPrethodnoStanje() {
		return prethodnoStanje;
	}

	public void setPrethodnoStanje(double prethodnoStanje) {
		this.prethodnoStanje = prethodnoStanje;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public String getDuznik() {
		return duznik;
	}

	public void setDuznik(String duznik) {
		this.duznik = duznik;
	}

	public String getPrimaoc() {
		return primaoc;
	}

	public void setPrimaoc(String promaoc) {
		this.primaoc = promaoc;
	}

	public String getSvrhaPlacanja() {
		return svrhaPlacanja;
	}

	public void setSvrhaPlacanja(String svrhaPlacanja) {
		this.svrhaPlacanja = svrhaPlacanja;
	}

	public String getVrstaPlacanja() {
		return vrstaPlacanja;
	}

	public void setVrstaPlacanja(String vrstaPlacanja) {
		this.vrstaPlacanja = vrstaPlacanja;
	}

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}
	
	
}
