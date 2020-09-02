package demo.app.web.dto;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import demo.app.entity.KursUValuti;
import demo.app.entity.Nalog;
import demo.app.entity.Racun;
import demo.app.entity.Valuta;

public class ValutaDTO {

	private long id;
	private String sifra;
	private String naziv;
	
	private DrzavaDTO drzava;
	
	private List<RacunDTO> racuni = new ArrayList<RacunDTO>();
	private List<KursUValutiDTO> kursKaoOsnovnaValuta = new ArrayList<KursUValutiDTO>();
	private List<KursUValutiDTO> kursKaoSporednaValuta = new ArrayList<KursUValutiDTO>();
	private List<NalogDTO> nalozi = new ArrayList<NalogDTO>();
	
	public ValutaDTO() {
		
	}
	
	public ValutaDTO(Valuta obj) {
		id = obj.getId();
		sifra = obj.getSifra();
		naziv = obj.getNaziv();
		
		if(obj.getDrzava() != null)
			drzava = new DrzavaDTO(obj.getDrzava());
		
	}
	
	public ValutaDTO(long id, String sifra, String naziv, DrzavaDTO drzava, List<RacunDTO> racuni,
			List<KursUValutiDTO> kursKaoOsnovnaValuta, List<KursUValutiDTO> kursKaoSporednaValuta,List<NalogDTO> nalozi) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.drzava = drzava;
		this.racuni = racuni;
		this.kursKaoOsnovnaValuta = kursKaoOsnovnaValuta;
		this.kursKaoSporednaValuta = kursKaoSporednaValuta;
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

	public DrzavaDTO getDrzava() {
		return drzava;
	}

	public void setDrzava(DrzavaDTO drzava) {
		this.drzava = drzava;
	}

	public List<RacunDTO> getRacuni() {
		return racuni;
	}


	public List<KursUValutiDTO> getKursKaoOsnovnaValuta() {
		return kursKaoOsnovnaValuta;
	}

	public List<KursUValutiDTO> getKursKaoSporednaValuta() {
		return kursKaoSporednaValuta;
	}
	public List<NalogDTO> getNalozi() {
		return nalozi;
	}
	
	public void setRacuniListFromSet(Set<Racun> racuni) {
		List<Racun> rl = new ArrayList<Racun>(racuni);
		for(Racun obj : rl)
			this.racuni.add(new RacunDTO(obj));
	}

	public void setKursKaoOsnovnaListFromSet(Set<KursUValuti> kurseviUValuti) {
		List<KursUValuti> kuvl = new ArrayList<KursUValuti>(kurseviUValuti);
		for(KursUValuti obj : kuvl)
			this.kursKaoOsnovnaValuta.add(new KursUValutiDTO(obj));
	}
	
	public void setKursKaoSporednaListFromSet(Set<KursUValuti> kurseviUValuti) {
		List<KursUValuti> kuvl = new ArrayList<KursUValuti>(kurseviUValuti);
		for(KursUValuti obj : kuvl)
			this.kursKaoSporednaValuta.add(new KursUValutiDTO(obj));
	}
	
	public void setNaloziListFromSet(Set<Nalog> nalozi) {
		List<Nalog> nl = new ArrayList<Nalog>(nalozi);
		for(Nalog obj : nl)
			this.nalozi.add(new NalogDTO(obj));
	}

	
}
