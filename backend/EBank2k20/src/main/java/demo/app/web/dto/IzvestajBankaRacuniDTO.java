package demo.app.web.dto;

import java.util.Date;

public class IzvestajBankaRacuniDTO {
	
	private String brojRacuna;
	private double stanje;
	private Date datumKreiranja;
	
	public IzvestajBankaRacuniDTO() {
		
	}

	public IzvestajBankaRacuniDTO(String brojRacuna, double stanje, Date datumKreiranja) {
		super();
		this.brojRacuna = brojRacuna;
		this.stanje = stanje;
		this.datumKreiranja = datumKreiranja;
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

	
	
}
