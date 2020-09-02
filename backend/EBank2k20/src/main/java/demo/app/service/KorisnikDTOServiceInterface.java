package demo.app.service;

import java.util.List;

import demo.app.entity.Korisnik;
import demo.app.web.dto.KorisnikDTO;

public interface KorisnikDTOServiceInterface {

	List<KorisnikDTO> getAllDTOs(List<Korisnik> korisnici);
	
	KorisnikDTO getKorisnikDTO(Korisnik korisnik);
	
}
