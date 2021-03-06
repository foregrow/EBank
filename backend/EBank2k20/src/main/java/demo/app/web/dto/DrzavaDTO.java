package demo.app.web.dto;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import demo.app.entity.Drzava;
import demo.app.entity.Nalog;
import demo.app.entity.Valuta;

public class DrzavaDTO {

	private long id;
	private String sifra;
	private String naziv;
	
	private List<ValutaDTO> valute = new ArrayList<ValutaDTO>();
	private List<NalogDTO> nalozi = new ArrayList<NalogDTO>();
	
	public DrzavaDTO() {
		
	}
	
	public DrzavaDTO(Drzava obj) {
		id = obj.getId();
		sifra = obj.getSifra();
		naziv = obj.getNaziv();
	}

	public DrzavaDTO(long id, String sifra, String naziv, List<ValutaDTO> valute, List<NalogDTO> nalozi) {
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

	public List<ValutaDTO> getValute() {
		return valute;
	}

	public List<NalogDTO> getNalozi() {
		return nalozi;
	}
	
	public void setValuteListFromSet(Set<Valuta> valute) {
		List<Valuta> vl = new ArrayList<Valuta>(valute);
		for(Valuta obj : vl)
			this.valute.add(new ValutaDTO(obj));
	}
	
	public void setNaloziListFromSet(Set<Nalog> nalozi) {
		List<Nalog> nl = new ArrayList<Nalog>(nalozi);
		for(Nalog obj : nl)
			this.nalozi.add(new NalogDTO(obj));
	}

	
	
}
