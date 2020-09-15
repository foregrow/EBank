package demo.app.web.dto;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import demo.app.entity.DnevnoStanje;


public class DnevnoStanjeDTO {


	private long id;
	private String brojIzvoda;
	private Date datumPrometa;
	private double prethodnoStanje;
	private double prometUKorist;
	private double prometNaTeret;
	private double novoStanje;
	
	private RacunDTO racun;
	//nalog je u stvari transakcija
	private List<NalogDTO> nalozi = new ArrayList<NalogDTO>();
	
	public DnevnoStanjeDTO() {
		
	}
	public DnevnoStanjeDTO(DnevnoStanje obj) {
		id = obj.getId();
		brojIzvoda = obj.getBrojIzvoda();
		datumPrometa = obj.getDatumPrometa();
		prethodnoStanje = obj.getPrethodnoStanje();
		prometUKorist = obj.getPrometUKorist();
		prometNaTeret = obj.getPrometNaTeret();
		novoStanje = obj.getNovoStanje();
		if(obj.getRacun() != null)
			racun = new RacunDTO(obj.getRacun());
	}

	public DnevnoStanjeDTO(long id, String brojIzvoda, Date datumPrometa, double prethodnoStanje, double prometUKorist,
			double prometNaTeret, double novoStanje, RacunDTO racun, List<NalogDTO> nalozi) {
		super();
		this.id = id;
		this.brojIzvoda = brojIzvoda;
		this.datumPrometa = datumPrometa;
		this.prethodnoStanje = prethodnoStanje;
		this.prometUKorist = prometUKorist;
		this.prometNaTeret = prometNaTeret;
		this.novoStanje = novoStanje;
		this.racun = racun;
		this.nalozi = nalozi;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public double getPrethodnoStanje() {
		return prethodnoStanje;
	}

	public void setPrethodnoStanje(double prethodnoStanje) {
		this.prethodnoStanje = prethodnoStanje;
	}

	public double getPrometUKorist() {
		return prometUKorist;
	}

	public void setPrometUKorist(double prometUKorist) {
		this.prometUKorist = prometUKorist;
	}

	public double getPrometNaTeret() {
		return prometNaTeret;
	}

	public void setPrometNaTeret(double prometNaTeret) {
		this.prometNaTeret = prometNaTeret;
	}

	public double getNovoStanje() {
		return novoStanje;
	}

	public void setNovoStanje(double novoStanje) {
		this.novoStanje = novoStanje;
	}

	public RacunDTO getRacun() {
		return racun;
	}

	public void setRacun(RacunDTO racun) {
		this.racun = racun;
	}

	public List<NalogDTO> getNalozi() {
		return nalozi;
	}
	
}
