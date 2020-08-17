package demo.app.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import demo.app.enums.TipPoruke;

@Entity
public class Poruka {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique = false, nullable = true)
	private TipPoruke tipPoruke;
	@Column(unique = false, nullable = true)
	private double iznos;
	@Column(unique = false, nullable = true)
	private String sifraValute;
	@Column(unique = false, nullable = true)
	private Date datumValute;
	
	/*@OneToMany(mappedBy = "poruka")
	private Set<Nalog> nalozi = new HashSet<Nalog>();*/
	
	public Poruka() {
		
	}

	public Poruka(long id, TipPoruke tipPoruke, double iznos, String sifraValute, Date datumValute) {
		super();
		this.id = id;
		this.tipPoruke = tipPoruke;
		this.iznos = iznos;
		this.sifraValute = sifraValute;
		this.datumValute = datumValute;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TipPoruke getTipPoruke() {
		return tipPoruke;
	}

	public void setTipPoruke(TipPoruke tipPoruke) {
		this.tipPoruke = tipPoruke;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public String getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}
	
	
}
