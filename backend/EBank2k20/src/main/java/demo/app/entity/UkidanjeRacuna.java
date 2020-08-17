package demo.app.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UkidanjeRacuna {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = false)
	private String obrazlozenje;
	@Column(unique = false, nullable = false)
	private Date datumKreiranja;
	@Column(unique = false, nullable = false)
	private boolean zavrseno;
	
	@OneToOne
	private Racun racunZaUkidanje;
	@OneToOne
	private Racun racunZaPrenosNovca;
	
	public UkidanjeRacuna() {
		
	}
	

	public UkidanjeRacuna(long id, String obrazlozenje, Date datumKreiranja, boolean zavrseno, Racun racunZaUkidanje,
			Racun racunZaPrenosNovca) {
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

	public Racun getRacunZaUkidanje() {
		return racunZaUkidanje;
	}

	public void setRacunZaUkidanje(Racun racunZaUkidanje) {
		this.racunZaUkidanje = racunZaUkidanje;
	}

	public Racun getRacunZaPrenosNovca() {
		return racunZaPrenosNovca;
	}

	public void setRacunZaPrenosNovca(Racun racunZaPrenosNovca) {
		this.racunZaPrenosNovca = racunZaPrenosNovca;
	}
	
	
}
