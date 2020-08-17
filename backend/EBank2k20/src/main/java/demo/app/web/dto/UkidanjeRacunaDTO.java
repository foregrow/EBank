package demo.app.web.dto;

import java.util.Date;

import demo.app.entity.UkidanjeRacuna;

public class UkidanjeRacunaDTO {

	private long id;
	private String obrazlozenje;
	private Date datumKreiranja;
	private boolean zavrseno;
	
	private RacunDTO racunZaUkidanje;
	private RacunDTO racunZaPrenosNovca;
	
	public UkidanjeRacunaDTO() {
		
	}
	
	public UkidanjeRacunaDTO(UkidanjeRacuna obj) {
		id = obj.getId();
		obrazlozenje = obj.getObrazlozenje();
		datumKreiranja = obj.getDatumKreiranja();
		zavrseno = obj.isZavrseno();
		
		if(obj.getRacunZaUkidanje() != null)
			racunZaUkidanje = new RacunDTO(obj.getRacunZaUkidanje());
		if(obj.getRacunZaPrenosNovca() != null)
			racunZaPrenosNovca = new RacunDTO(obj.getRacunZaPrenosNovca());
	}
	

	public UkidanjeRacunaDTO(long id, String obrazlozenje, Date datumKreiranja, boolean zavrseno, RacunDTO racunZaUkidanje,
			RacunDTO racunZaPrenosNovca) {
		super();
		this.id = id;
		this.obrazlozenje = obrazlozenje;
		this.datumKreiranja = datumKreiranja;
		this.zavrseno = zavrseno;
		this.racunZaUkidanje = racunZaUkidanje;
		this.racunZaPrenosNovca = racunZaPrenosNovca;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getObrazlozenje() {
		return obrazlozenje;
	}

	public void setObrazlozenje(String obrazlozenje) {
		this.obrazlozenje = obrazlozenje;
	}

	public Date getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public boolean isZavrseno() {
		return zavrseno;
	}

	public void setZavrseno(boolean zavrseno) {
		this.zavrseno = zavrseno;
	}

	public RacunDTO getRacunZaUkidanje() {
		return racunZaUkidanje;
	}

	public void setRacunZaUkidanje(RacunDTO racunZaUkidanje) {
		this.racunZaUkidanje = racunZaUkidanje;
	}

	public RacunDTO getRacunZaPrenosNovca() {
		return racunZaPrenosNovca;
	}

	public void setRacunZaPrenosNovca(RacunDTO racunZaPrenosNovca) {
		this.racunZaPrenosNovca = racunZaPrenosNovca;
	}
	
	
}
