package demo.app.service;

import java.util.List;

import demo.app.entity.UkidanjeRacuna;



public interface UkidanjeRacunaServiceInterface {

	List<UkidanjeRacuna> findAll();
	
	UkidanjeRacuna findOne(long id);
	
	UkidanjeRacuna save(UkidanjeRacuna ur);
	
	void remove(long id);
	
	List<UkidanjeRacuna> findAllByBankaId(long id);
	
	List<UkidanjeRacuna> findAllByKlijentIdWhereZavrsenoFalse(long id);
	
	UkidanjeRacuna findByRacunZaUkidanjeId(long id);

	
}
