package demo.app.service;

import java.util.List;



import demo.app.entity.Drzava;

public interface DrzavaServiceInterface {

	List<Drzava> findAll();
	
	Drzava findOne(long id);
	
	Drzava save(Drzava drz);
	
	void remove(long id);

	
}
