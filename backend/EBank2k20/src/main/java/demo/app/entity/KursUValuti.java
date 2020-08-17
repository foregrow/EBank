package demo.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class KursUValuti {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = false)
	private long redniBroj;
	@Column(unique = false, nullable = false)
	private double kupovni;
	@Column(unique = false, nullable = false)
	private double srednji;
	@Column(unique = false, nullable = false)
	private double prodajni;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Valuta osnovnaValuta;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Valuta sporednaValuta;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private KursnaLista kursnaLista;
	
	public KursUValuti() {
		
	}

	public KursUValuti(long id, long redniBroj, double kupovni, double srednji, double prodajni, Valuta osnovnaValuta,
			Valuta sporednaValuta, KursnaLista kursnaLista) {
		super();
		this.id = id;
		this.redniBroj = redniBroj;
		this.kupovni = kupovni;
		this.srednji = srednji;
		this.prodajni = prodajni;
		this.osnovnaValuta = osnovnaValuta;
		this.sporednaValuta = sporednaValuta;
		this.kursnaLista = kursnaLista;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(long redniBroj) {
		this.redniBroj = redniBroj;
	}

	public double getKupovni() {
		return kupovni;
	}

	public void setKupovni(double kupovni) {
		this.kupovni = kupovni;
	}

	public double getSrednji() {
		return srednji;
	}

	public void setSrednji(double srednji) {
		this.srednji = srednji;
	}

	public double getProdajni() {
		return prodajni;
	}

	public void setProdajni(double prodajni) {
		this.prodajni = prodajni;
	}

	public Valuta getOsnovnaValuta() {
		return osnovnaValuta;
	}

	public void setOsnovnaValuta(Valuta osnovnaValuta) {
		this.osnovnaValuta = osnovnaValuta;
	}

	public Valuta getSporednaValuta() {
		return sporednaValuta;
	}

	public void setSporednaValuta(Valuta sporednaValuta) {
		this.sporednaValuta = sporednaValuta;
	}

	public KursnaLista getKursnaLista() {
		return kursnaLista;
	}

	public void setKursnaLista(KursnaLista kursnaLista) {
		this.kursnaLista = kursnaLista;
	}
	
}
