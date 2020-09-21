package demo.app.web.dto;


import java.util.Date;

import demo.app.entity.MedjubankarskiPrenos;
import demo.app.enums.TipTransfera;

public class MedjubankarskiPrenosDTO {


	private long id;
	private double iznos;
	private String valuta;
	private TipTransfera tipTransfera;
	private Date datumPrenosa;

	private BankaDTO bankaDuznika;
	private BankaDTO bankaPrimaoca;
	private RacunDTO racunDuznika;
	private RacunDTO racunPrimaoca;
	
	public MedjubankarskiPrenosDTO() {
		
	}
	
	public MedjubankarskiPrenosDTO(MedjubankarskiPrenos m) {
		this.id = m.getId();
		this.iznos = m.getIznos();
		this.valuta = m.getValuta();
		this.tipTransfera = m.getTipTransfera();
		this.datumPrenosa = m.getDatumPrenosa();
		
		if(m.getBankaDuznika() != null) {
			this.bankaDuznika = new BankaDTO(m.getBankaDuznika());
		}
		if(m.getBankaPrimaoca() != null) {
			this.bankaPrimaoca = new BankaDTO(m.getBankaPrimaoca());
		}
		if(m.getRacunDuznika() != null) {
			this.racunDuznika = new RacunDTO(m.getRacunDuznika());
		}
		if(m.getRacunPrimaoca() != null) {
			this.racunPrimaoca = new RacunDTO(m.getRacunPrimaoca());
		}
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

	public BankaDTO getBankaDuznika() {
		return bankaDuznika;
	}

	public void setBankaDuznika(BankaDTO bankaDuznika) {
		this.bankaDuznika = bankaDuznika;
	}

	public BankaDTO getBankaPrimaoca() {
		return bankaPrimaoca;
	}

	public void setBankaPrimaoca(BankaDTO bankaPrimaoca) {
		this.bankaPrimaoca = bankaPrimaoca;
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

	public TipTransfera getTipTransfera() {
		return tipTransfera;
	}

	public void setTipTransfera(TipTransfera tipTransfera) {
		this.tipTransfera = tipTransfera;
	}

	public Date getDatumPrenosa() {
		return datumPrenosa;
	}

	public void setDatumPrenosa(Date datumPrenosa) {
		this.datumPrenosa = datumPrenosa;
	}

	
	
}
