package demo.app.web.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import demo.app.entity.Racun;

public class RacunDTO {

	private long id;
	private String brojRacuna;
	private double stanje;
	private Date datumKreiranja;
	private boolean izbrisan;
	private boolean odobren;
	
	private UkidanjeRacunaDTO ukidanjeRacuna;
	
	private BankaDTO banka;
	private KlijentDTO klijent;
	private ValutaDTO valuta;
	
	private List<DnevnoStanjeDTO> dnevnoStanje = new ArrayList<DnevnoStanjeDTO>();
	
	public RacunDTO() {
		
	}
	
	public RacunDTO(Racun obj) {
		id = obj.getId();
		brojRacuna = obj.getBrojRacuna();
		stanje = obj.getStanje();
		datumKreiranja = obj.getDatumKreiranja();
		izbrisan = obj.isIzbrisan();
		odobren = obj.isOdobren();
		
		if(obj.getBanka() != null)
			banka = new BankaDTO(obj.getBanka());
		if(obj.getKlijent() != null)
			klijent = new KlijentDTO(obj.getKlijent());
		if(obj.getValuta() != null)
			valuta = new ValutaDTO(obj.getValuta());
	}

	public RacunDTO(long id, String brojRacuna, double stanje, Date datumKreiranja, boolean izbrisan,
			UkidanjeRacunaDTO ukidanjeRacuna, BankaDTO banka, KlijentDTO klijent, ValutaDTO valuta,
			List<DnevnoStanjeDTO> dnevnoStanje, boolean odobren) {
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
		this.odobren = odobren;
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

	public UkidanjeRacunaDTO getUkidanjeRacuna() {
		return ukidanjeRacuna;
	}

	public void setUkidanjeRacuna(UkidanjeRacunaDTO ukidanjeRacuna) {
		this.ukidanjeRacuna = ukidanjeRacuna;
	}

	public BankaDTO getBanka() {
		return banka;
	}

	public void setBanka(BankaDTO banka) {
		this.banka = banka;
	}

	public KlijentDTO getKlijent() {
		return klijent;
	}

	public void setKlijent(KlijentDTO klijent) {
		this.klijent = klijent;
	}

	public ValutaDTO getValuta() {
		return valuta;
	}

	public void setValuta(ValutaDTO valuta) {
		this.valuta = valuta;
	}

	public List<DnevnoStanjeDTO> getDnevnoStanje() {
		return dnevnoStanje;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}
	

	
}
