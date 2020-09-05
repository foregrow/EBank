package demo.app.entity;

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
import javax.persistence.OneToOne;

import demo.app.enums.TipKlijenta;


@Entity
public class Klijent {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = false)
	private String ime;
	@Column(unique = false, nullable = false)
	private String prezime;
	@Column(unique = false, nullable = false)
	private String jmbg;
	@Column(unique = false, nullable = false)
	private String telefon;
	@Column(unique = false, nullable = false)
	private String adresa;
	@Column(unique = false, nullable = false)
	private TipKlijenta tipKlijenta;
	@Column(unique = false, nullable = false)
	private boolean odobren;
	
	@OneToOne
	private Korisnik korisnik;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Delatnost delatnost;
	
	@OneToMany(mappedBy = "klijent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Racun> racuni = new HashSet<Racun>();
	
	public Klijent() {
		
	}

	public Klijent(long id, String ime, String prezime, String jmbg, String telefon, String adresa,
			TipKlijenta tipKlijenta,  Korisnik korisnik, Delatnost delatnost, Set<Racun> racuni, boolean odobren) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.telefon = telefon;
		this.adresa = adresa;
		this.tipKlijenta = tipKlijenta;
		this.korisnik = korisnik;
		this.delatnost = delatnost;
		this.racuni = racuni;
		this.odobren = odobren;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public TipKlijenta getTipKlijenta() {
		return tipKlijenta;
	}

	public void setTipKlijenta(TipKlijenta tipKlijenta) {
		this.tipKlijenta = tipKlijenta;
	}
	

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Delatnost getDelatnost() {
		return delatnost;
	}

	public void setDelatnost(Delatnost delatnost) {
		this.delatnost = delatnost;
	}

	public Set<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(Set<Racun> racuni) {
		this.racuni = racuni;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}
	
	
}
