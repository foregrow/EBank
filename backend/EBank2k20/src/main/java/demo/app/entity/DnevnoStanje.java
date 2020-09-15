package demo.app.entity;

import java.util.Date;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DnevnoStanje {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = true)
	private String brojIzvoda;
	@Column(unique = false, nullable = true)
	private Date datumPrometa;
	@Column(unique = false, nullable = true)
	private double prethodnoStanje;
	@Column(unique = false, nullable = true)
	private double prometUKorist;
	@Column(unique = false, nullable = true)
	private double prometNaTeret;
	@Column(unique = false, nullable = true)
	private double novoStanje;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Racun racun;
	//nalog je u stvari transakcija
	@OneToMany(mappedBy = "dnevnoStanje")
	private Set<Nalog> nalozi = new HashSet<Nalog>();
	
	public DnevnoStanje() {
		
	}

	public DnevnoStanje(long id, String brojIzvoda, Date datumPrometa, double prethodnoStanje, double prometUKorist,
			double prometNaTeret, double novoStanje, Racun racun, Set<Nalog> nalozi) {
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

	public Racun getRacun() {
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

	public Set<Nalog> getNalozi() {
		return nalozi;
	}

	public void setNalozi(Set<Nalog> nalozi) {
		this.nalozi = nalozi;
	}
	
	
	
	
}
