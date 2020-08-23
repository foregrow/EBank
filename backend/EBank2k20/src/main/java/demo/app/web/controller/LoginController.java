package demo.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.app.authmodel.AuthenticationRequest;
import demo.app.authmodel.AuthenticationResponse;
import demo.app.service.KorisnikService;
import demo.app.util.JwtUtil;

@CrossOrigin(origins ="*",allowedHeaders = "*")
@RestController
public class LoginController {
	@Autowired
	private KorisnikService ks;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping(value ="/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getKorisnickoIme(), authenticationRequest.getLozinka())
			);
		}catch (BadCredentialsException e) {
			throw new Exception ("netacni podaci username/password", e);
		}
		final UserDetails userDetails = ks
				.loadUserByUsername(authenticationRequest.getKorisnickoIme());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		String uloga = "";
		for (GrantedAuthority authority : userDetails.getAuthorities())
			uloga = authority.getAuthority();
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt,uloga));
	}

}
