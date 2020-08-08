package demo.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import demo.app.enums.UlogaKorisnika;

@Entity
public class Korisnik {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = false)
	private String korisnickoIme;
	@Column(unique = false, nullable = false)
	private String lozinka;
	@Column(unique = false, nullable = false)
	private UlogaKorisnika uloga;

	@Column(unique = false, nullable = true)
	private Klijent klijent;
	
	public Korisnik() {
		
	}
	

	public Korisnik(long id, String korisnickoIme, String lozinka, UlogaKorisnika uloga, Klijent klijent) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.uloga = uloga;
		this.klijent = klijent;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public UlogaKorisnika getUloga() {
		return uloga;
	}

	public void setUloga(UlogaKorisnika uloga) {
		this.uloga = uloga;
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}
	
	
	
}
