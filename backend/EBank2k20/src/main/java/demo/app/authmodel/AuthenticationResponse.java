package demo.app.authmodel;

public class AuthenticationResponse {

	private final String jwt;
	private final String uloga;
	
	public String getJwt() {
		return jwt;
	}
	
	public String getUloga() {
		return uloga;
	}

	public AuthenticationResponse(String jwt,String uloga) {
		super();
		this.jwt = jwt;
		this.uloga = uloga;
	}
	
	
}
