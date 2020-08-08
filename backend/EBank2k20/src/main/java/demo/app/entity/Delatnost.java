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
public class Delatnost {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = false)
	private String sifra;
	@Column(unique = false, nullable = false)
	private String naziv;
	
	@OneToMany(mappedBy = "delatnost")
	private Set<Klijent> klijenti = new HashSet<Klijent>();
	
	public Delatnost() {
		
	}

	public Delatnost(long id, String sifra, String naziv, Set<Klijent> klijenti) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.klijenti = klijenti;
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

	public Set<Klijent> getKlijenti() {
		return klijenti;
	}

	public void setKlijenti(Set<Klijent> klijenti) {
		this.klijenti = klijenti;
	}
	
	
	
}
