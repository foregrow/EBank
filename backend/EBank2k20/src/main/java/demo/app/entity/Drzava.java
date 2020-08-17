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
public class Drzava {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = false)
	private String sifra;
	@Column(unique = false, nullable = false)
	private String naziv;
	
	@OneToMany(mappedBy = "drzava")
	private Set<Valuta> valute = new HashSet<Valuta>();
	
	@OneToMany(mappedBy = "drzava")
	private Set<Nalog> nalozi = new HashSet<Nalog>();
	
	public Drzava() {
		
	}

	public Drzava(long id, String sifra, String naziv, Set<Valuta> valute, Set<Nalog> nalozi) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.valute = valute;
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

	public Set<Valuta> getValute() {
		return valute;
	}

	public void setValute(Set<Valuta> valute) {
		this.valute = valute;
	}

	public Set<Nalog> getNalozi() {
		return nalozi;
	}

	public void setNalozi(Set<Nalog> nalozi) {
		this.nalozi = nalozi;
	}
	
	
}
