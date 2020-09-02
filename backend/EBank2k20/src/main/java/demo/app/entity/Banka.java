package demo.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Banka {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = false)
	private String sifra;
	@Column(unique = false, nullable = false)
	private String naziv;
	@Column(unique = false, nullable = true)
	private String adresa;
	@Column(unique = false, nullable = true)
	private String email;
	@Column(unique = false, nullable = true)
	private String web;
	@Column(unique = false, nullable = true)
	private String telefon;
	@Column(unique = false, nullable = true)
	private String fax;
	@Column(unique = false, nullable = true)
	private String swift;
	@Column(unique = false, nullable = true)
	private String obracunskiRacun;
	
	@OneToMany(mappedBy = "banka")
	private Set<Racun> racuni = new HashSet<Racun>();
	@OneToMany(mappedBy = "banka")
	private Set<KursnaLista> kursneListe = new HashSet<KursnaLista>();
	@OneToMany(mappedBy = "banka")
	private Set<Korisnik> izvrsioci = new HashSet<Korisnik>();
	public Banka() {
		
	}
	
	public Banka(long id, String sifra, String naziv, String adresa, String email, String web, String telefon,
			String fax, String swift, String obracunskiRacun, Set<Racun> racuni, Set<KursnaLista> kursneListe,Set<Korisnik> izvrsioci) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.adresa = adresa;
		this.email = email;
		this.web = web;
		this.telefon = telefon;
		this.fax = fax;
		this.swift = swift;
		this.obracunskiRacun = obracunskiRacun;
		this.racuni = racuni;
		this.kursneListe = kursneListe;
		this.izvrsioci = izvrsioci;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public String getObracunskiRacun() {
		return obracunskiRacun;
	}

	public void setObracunskiRacun(String obracunskiRacun) {
		this.obracunskiRacun = obracunskiRacun;
	}

	public Set<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(Set<Racun> racuni) {
		this.racuni = racuni;
	}

	public Set<KursnaLista> getKursneListe() {
		return kursneListe;
	}

	public void setKursneListe(Set<KursnaLista> kursneListe) {
		this.kursneListe = kursneListe;
	}

	public Set<Korisnik> getIzvrsioci() {
		return izvrsioci;
	}

	public void setIzvrsioci(Set<Korisnik> izvrsioci) {
		this.izvrsioci = izvrsioci;
	}
	
	
}
