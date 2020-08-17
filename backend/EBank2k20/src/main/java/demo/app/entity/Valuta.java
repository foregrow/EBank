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

@Entity
public class Valuta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = false)
	private String sifra;
	@Column(unique = false, nullable = false)
	private String naziv;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Drzava drzava;
	
	@OneToMany(mappedBy = "valuta")
	private Set<Racun> racuni = new HashSet<Racun>();
	///npr. ako je ova valuta RSD, lista kurs u valuti gde se gleda od RSD (osnovna)
	@OneToMany(mappedBy = "osnovnaValuta")
	private Set<KursUValuti> kursKaoOsnovnaValuta = new HashSet<KursUValuti>();
	//npr. ako je ova valuta RSD, lista kurs u valuti gde se gleda prema RSD (sporedna)
	@OneToMany(mappedBy = "sporednaValuta")
	private Set<KursUValuti> kursKaoSporednaValuta = new HashSet<KursUValuti>();
	
	@OneToMany(mappedBy = "valuta")
	private Set<Nalog> nalozi = new HashSet<Nalog>();
	
	public Valuta() {
		
	}
	
	public Valuta(long id, String sifra, String naziv, Drzava drzava, Set<Racun> racuni,
			Set<KursUValuti> kursKaoOsnovnaValuta, Set<KursUValuti> kursKaoSporednaValuta,Set<Nalog> nalozi) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.drzava = drzava;
		this.racuni = racuni;
		this.kursKaoOsnovnaValuta = kursKaoOsnovnaValuta;
		this.kursKaoSporednaValuta = kursKaoSporednaValuta;
		this.nalozi = nalozi;
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

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

	public Set<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(Set<Racun> racuni) {
		this.racuni = racuni;
	}

	public Set<KursUValuti> getKursKaoOsnovnaValuta() {
		return kursKaoOsnovnaValuta;
	}

	public void setKursKaoOsnovnaValuta(Set<KursUValuti> kursKaoOsnovnaValuta) {
		this.kursKaoOsnovnaValuta = kursKaoOsnovnaValuta;
	}

	public Set<KursUValuti> getKursKaoSporednaValuta() {
		return kursKaoSporednaValuta;
	}

	public void setKursKaoSporednaValuta(Set<KursUValuti> kursKaoSporednaValuta) {
		this.kursKaoSporednaValuta = kursKaoSporednaValuta;
	}

	public Set<Nalog> getNalozi() {
		return nalozi;
	}

	public void setNalozi(Set<Nalog> nalozi) {
		this.nalozi = nalozi;
	}
	
	
}
