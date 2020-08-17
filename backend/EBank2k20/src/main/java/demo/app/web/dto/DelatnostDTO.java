package demo.app.web.dto;


import java.util.ArrayList;
import java.util.List;

import demo.app.entity.Delatnost;

public class DelatnostDTO {
	
	private long id;
	private String sifra;
	private String naziv;
	
	private List<KlijentDTO> klijenti = new ArrayList<KlijentDTO>();
	
	public DelatnostDTO() {
		
	}
	
	public DelatnostDTO(Delatnost obj) {
		id = obj.getId();
		sifra = obj.getSifra();
		naziv = obj.getNaziv();
	}

	public DelatnostDTO(long id, String sifra, String naziv, List<KlijentDTO> klijenti) {
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

	public List<KlijentDTO> getKlijenti() {
		return klijenti;
	}

	
}
