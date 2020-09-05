package demo.app.service;

import java.util.List;


import demo.app.entity.Racun;



public interface RacunServiceInterface {

	List<Racun> findAll();
	
	Racun findOne(long id);
	
	Racun save(Racun r);
	
	void remove(long id);

	
}
