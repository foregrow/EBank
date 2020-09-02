package demo.app.web.dto;

import java.util.ArrayList;


import java.util.List;
import java.util.Set;

import demo.app.entity.Banka;
import demo.app.entity.Korisnik;
import demo.app.entity.KursnaLista;
import demo.app.entity.Racun;

public class BankaDTO {

	private long id;
	private String sifra;
	private String naziv;
	private String adresa;
	private String email;
	private String web;
	private String telefon;
	private String fax;
	private String swift;
	private String obracunskiRacun;
	
	private List<RacunDTO> racuni = new ArrayList<RacunDTO>();
	private List<KursnaListaDTO> kursneListe = new ArrayList<KursnaListaDTO>();
	private List<KorisnikDTO> izvrsioci = new ArrayList<KorisnikDTO>();
	
	public BankaDTO() {
		
	}
	
	public BankaDTO(Banka obj) {
		id = obj.getId();
		sifra = obj.getSifra();
		naziv = obj.getNaziv();
		adresa = obj.getAdresa();
		email = obj.getEmail();
		web = obj.getWeb();
		telefon = obj.getTelefon();
		fax = obj.getFax();
		swift = obj.getSwift();
		obracunskiRacun = obj.getObracunskiRacun();
	}
	
	public BankaDTO(long id, String sifra, String naziv, String adresa, String email, String web, String telefon,
			String fax, String swift, String obracunskiRacun, List<RacunDTO> racuni, List<KursnaListaDTO> kursneListe,
			List<KorisnikDTO> izvrsioci) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.adresa = adresa;
		this.email = email;
		this.web = web;
		this.telefon = telefon;
		this.fax = fax;
		this.swift = swift;
		this.obracunskiRacun = obracunskiRacun;
		this.racuni = racuni;
		this.kursneListe = kursneListe;
		this.izvrsioci = izvrsioci;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public String getObracunskiRacun() {
		return obracunskiRacun;
	}

	public void setObracunskiRacun(String obracunskiRacun) {
		this.obracunskiRacun = obracunskiRacun;
	}

	public List<RacunDTO> getRacuni() {
		return racuni;
	}

	public List<KursnaListaDTO> getKursneListe() {
		return kursneListe;
	}
	
	public List<KorisnikDTO> getIzvrsioci(){
		return izvrsioci;
	}

	public void setRacuniListFromSet(Set<Racun> racuni) {
		List<Racun> rl = new ArrayList<Racun>(racuni);
		for(Racun obj : rl)
			this.racuni.add(new RacunDTO(obj));
	}

	public void setKursneListeListFromSet(Set<KursnaLista> kursneListe) {
		List<KursnaLista> kl = new ArrayList<KursnaLista>(kursneListe);
		for(KursnaLista obj : kl)
			this.kursneListe.add(new KursnaListaDTO(obj));
	}
	
	public void setIzvrsiociListFromSet(Set<Korisnik> izvrsioci) {
		List<Korisnik> il = new ArrayList<Korisnik>(izvrsioci);
		for(Korisnik obj : il)
			this.izvrsioci.add(new KorisnikDTO(obj));
	}

	
	
}
