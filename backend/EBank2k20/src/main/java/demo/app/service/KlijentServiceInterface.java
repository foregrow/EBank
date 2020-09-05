package demo.app.service;

import java.util.List;

import demo.app.entity.Klijent;

public interface KlijentServiceInterface {
	
	List<Klijent> findAll();
	
	Klijent findOne(long id);
	
	Klijent save(Klijent klijent);
	
	void remove(long id);
	
	Klijent getByKorisnikId(long id);
	
	List<Klijent> getOdobreniByBankaId(long id);
	
	List<Klijent> getNeodobreniByBankaId(long id);
	
}
