package demo.app.service;

import java.util.Date;
import java.util.List;



import demo.app.entity.DnevnoStanje;

public interface DnevnoStanjeServiceInterface {

	List<DnevnoStanje> findAll();
	
	DnevnoStanje findOne(long id);
	
	DnevnoStanje save(DnevnoStanje ds);
	
	void remove(long id);
	
	List<DnevnoStanje> dnevnoStanjeZaRacunPoDatumu(Date odDatum, Date doDatum, long rid);


	
}
