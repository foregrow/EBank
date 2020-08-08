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
public class Valuta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = false)
	private String sifra;
	@Column(unique = false, nullable = false)
	private String naziv;
	
	
	@OneToMany(mappedBy = "valuta")
	private Set<Racun> racuni = new HashSet<Racun>();
}
