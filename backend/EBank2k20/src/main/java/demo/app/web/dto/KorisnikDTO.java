package demo.app.web.dto;


import demo.app.entity.Korisnik;

import demo.app.enums.UlogaKorisnika;


public class KorisnikDTO {

	private long id;
	private String korisnickoIme;
	private String lozinka;
	private UlogaKorisnika uloga;

	private KlijentDTO klijent;
	private BankaDTO banka;
	
	public KorisnikDTO() {
		
	}
	
	public KorisnikDTO(Korisnik obj) {
		id = obj.getId();
		korisnickoIme = obj.getKorisnickoIme();
		lozinka = obj.getLozinka();
		uloga = obj.getUloga();
		if(obj.getBanka() != null)
			banka = new BankaDTO(obj.getBanka());
	}
	

	public KorisnikDTO(long id, String korisnickoIme, String lozinka, UlogaKorisnika uloga, KlijentDTO klijent, BankaDTO banka) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.uloga = uloga;
		this.klijent = klijent;
		this.banka = banka;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public UlogaKorisnika getUloga() {
		return uloga;
	}

	public void setUloga(UlogaKorisnika uloga) {
		this.uloga = uloga;
	}

	public KlijentDTO getKlijent() {
		return klijent;
	}

	public void setKlijent(KlijentDTO klijent) {
		this.klijent = klijent;
	}

	public BankaDTO getBanka() {
		return banka;
	}

	public void setBanka(BankaDTO banka) {
		this.banka = banka;
	}
	
	
	
}
