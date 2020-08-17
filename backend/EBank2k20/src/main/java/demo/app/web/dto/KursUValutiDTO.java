package demo.app.web.dto;

import demo.app.entity.KursUValuti;

public class KursUValutiDTO {

	private long id;
	private long redniBroj;
	private double kupovni;
	private double srednji;
	private double prodajni;
	
	private ValutaDTO osnovnaValuta;
	private ValutaDTO sporednaValuta;
	private KursnaListaDTO kursnaLista;
	
	public KursUValutiDTO() {
		
	}
	
	public KursUValutiDTO(KursUValuti obj) {
		id = obj.getId();
		redniBroj = obj.getRedniBroj();
		kupovni = obj.getKupovni();
		srednji = obj.getSrednji();
		prodajni = obj.getProdajni();
		if(obj.getOsnovnaValuta() != null)
			osnovnaValuta = new ValutaDTO(obj.getOsnovnaValuta());
		if(obj.getSporednaValuta() != null)
			sporednaValuta = new ValutaDTO(obj.getSporednaValuta());
		if(obj.getKursnaLista() != null)
			kursnaLista = new KursnaListaDTO(obj.getKursnaLista());
	}

	public KursUValutiDTO(long id, long redniBroj, double kupovni, double srednji, double prodajni, ValutaDTO osnovnaValuta,
			ValutaDTO sporednaValuta, KursnaListaDTO kursnaLista) {
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

	public ValutaDTO getOsnovnaValuta() {
		return osnovnaValuta;
	}

	public void setOsnovnaValuta(ValutaDTO osnovnaValuta) {
		this.osnovnaValuta = osnovnaValuta;
	}

	public ValutaDTO getSporednaValuta() {
		return sporednaValuta;
	}

	public void setSporednaValuta(ValutaDTO sporednaValuta) {
		this.sporednaValuta = sporednaValuta;
	}

	public KursnaListaDTO getKursnaLista() {
		return kursnaLista;
	}

	public void setKursnaLista(KursnaListaDTO kursnaLista) {
		this.kursnaLista = kursnaLista;
	}
	
}
