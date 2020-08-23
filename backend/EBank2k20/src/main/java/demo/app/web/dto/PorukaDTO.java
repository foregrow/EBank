package demo.app.web.dto;

import java.sql.Date;




import demo.app.enums.TipPoruke;

public class PorukaDTO {

	private long id;
	private TipPoruke tipPoruke;
	private double iznos;
	private String sifraValute;

	private Date datumValute;
	
	/*
	private Set<Nalog> nalozi = new HashSet<Nalog>();*/
	
	public PorukaDTO() {
		
	}

	public PorukaDTO(long id, TipPoruke tipPoruke, double iznos, String sifraValute, Date datumValute) {
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
