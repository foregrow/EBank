package demo.app.web.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import demo.app.entity.Klijent;
import demo.app.entity.Racun;
import demo.app.enums.TipKlijenta;


public class KlijentDTO {

	private long id;
	private String ime;
	private String prezime;
	private String jmbg;
	private String telefon;
	private String adresa;
	private TipKlijenta tipKlijenta;
	private boolean odobren;
	
	private KorisnikDTO korisnik;
	private DelatnostDTO delatnost;
	
	private List<RacunDTO> racuni = new ArrayList<RacunDTO>();
	
	public KlijentDTO() {
		
	}
	
	public KlijentDTO(Klijent obj) {
		id = obj.getId();
		ime = obj.getIme();
		prezime = obj.getPrezime();
		jmbg = obj.getJmbg();
		telefon = obj.getTelefon();
		adresa = obj.getAdresa();
		odobren = obj.isOdobren();
		tipKlijenta = obj.getTipKlijenta();
		if(obj.getKorisnik() != null)
			korisnik = new KorisnikDTO(obj.getKorisnik());
		if(obj.getDelatnost() != null)
			delatnost = new DelatnostDTO(obj.getDelatnost());
	}

	public KlijentDTO(long id, String ime, String prezime, String jmbg, String telefon, String adresa,
			TipKlijenta tipKlijenta,  KorisnikDTO korisnik, DelatnostDTO delatnost, List<RacunDTO> racuni, boolean odobren) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.telefon = telefon;
		this.adresa = adresa;
		this.tipKlijenta = tipKlijenta;
		this.korisnik = korisnik;
		this.delatnost = delatnost;
		this.racuni = racuni;
		this.odobren = odobren;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public TipKlijenta getTipKlijenta() {
		return tipKlijenta;
	}

	public void setTipKlijenta(TipKlijenta tipKlijenta) {
		this.tipKlijenta = tipKlijenta;
	}
	

	public KorisnikDTO getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}

	public DelatnostDTO getDelatnost() {
		return delatnost;
	}

	public void setDelatnost(DelatnostDTO delatnost) {
		this.delatnost = delatnost;
	}
	

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}

	public List<RacunDTO> getRacuni() {
		return racuni;
	}
	
	public void setRacuniAktivniListFromSet(Set<Racun> racuni) {
		List<Racun> ra = new ArrayList<Racun>(racuni);
		for(Racun obj: ra) {
			if(!obj.isIzbrisan() && obj.isOdobren())
				this.racuni.add(new RacunDTO(obj));	
		}
			
	}
	
	public void setRacuniListFromSet(Set<Racun> racuni) {
		List<Racun> ra = new ArrayList<Racun>(racuni);
		for(Racun obj: ra) {
			
			this.racuni.add(new RacunDTO(obj));	
		}
			
	}

	public void setAktivniRacuniListFromSet(Set<Racun> racuni) {
		List<Racun> ra = new ArrayList<Racun>(racuni);
		for(Racun obj: ra) {
			if(!obj.isIzbrisan() && obj.isOdobren()) {
				this.racuni.add(new RacunDTO(obj));	
			}	
		}
			
	}
	
}
