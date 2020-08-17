package demo.app.service;

import java.util.List;

import demo.app.entity.Korisnik;

public interface KorisnikServiceInterface {

	List<Korisnik> findAll();
	
	Korisnik findOne(long id);
	
	Korisnik save(Korisnik kor);
	
	void remove(long id);
	
	Korisnik findByKorisnickoIme(String korime);
	
}
