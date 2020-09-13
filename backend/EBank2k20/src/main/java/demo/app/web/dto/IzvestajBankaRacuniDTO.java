package demo.app.web.dto;

import java.util.Date;

public class IzvestajBankaRacuniDTO {
	
	private String brojRacuna;
	private double stanje;
	private Date datumKreiranja;
	private String banka;
	
	public IzvestajBankaRacuniDTO() {
		
	}

	public IzvestajBankaRacuniDTO(String brojRacuna, double stanje, Date datumKreiranja, String banka) {
		super();
		this.brojRacuna = brojRacuna;
		this.stanje = stanje;
		this.datumKreiranja = datumKreiranja;
		this.banka = banka;
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

	public String getBanka() {
		return banka;
	}

	public void setBanka(String banka) {
		this.banka = banka;
	}
	
	
	
}
