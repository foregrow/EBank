package demo.app.authmodel;

public class AuthenticationRequest {
	
	private String korisnickoIme;
	private String lozinka;
	private String uloga;
	
	public AuthenticationRequest(){
		
	}
	public AuthenticationRequest(String korisnickoIme, String lozinka,String uloga) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.uloga = uloga;
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
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	
	
}
