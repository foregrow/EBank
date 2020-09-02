package demo.app.service;

import java.util.List;

import demo.app.entity.Korisnik;
import demo.app.web.dto.KorisnikDTO;

public interface KorisnikServiceInterface {

	List<Korisnik> findAll();
	
	Korisnik findOne(long id);
	
	Korisnik save(Korisnik kor);
	
	void remove(long id);
	
	Korisnik findByKorisnickoIme(String korime);
	
	List<KorisnikDTO> getAllDTOs(List<Korisnik> korisnici);
	
	KorisnikDTO getKorisnikDTO(Korisnik korisnik);
	
}
