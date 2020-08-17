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
public class KursnaLista {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = true)
	private Date datum;
	@Column(unique = false, nullable = true)
	private String broj;
	@Column(unique = false, nullable = true)
	private Date datumPrimene;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Banka banka;
	
	@OneToMany(mappedBy = "kursnaLista")
	private Set<KursUValuti> kurseviUValutama = new HashSet<KursUValuti>();
	
	public KursnaLista() {
		
	}
	
	public KursnaLista(long id, Date datum, String broj, Date datumPrimene, Banka banka,
			Set<KursUValuti> kurseviUValutama) {
		super();
		this.id = id;
		this.datum = datum;
		this.broj = broj;
		this.datumPrimene = datumPrimene;
		this.banka = banka;
		this.kurseviUValutama = kurseviUValutama;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public Date getDatumPrimene() {
		return datumPrimene;
	}

	public void setDatumPrimene(Date datumPrimene) {
		this.datumPrimene = datumPrimene;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}

	public Set<KursUValuti> getKurseviUValutama() {
		return kurseviUValutama;
	}

	public void setKurseviUValutama(Set<KursUValuti> kurseviUValutama) {
		this.kurseviUValutama = kurseviUValutama;
	}
	
	
	

}
