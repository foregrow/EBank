package demo.app.service;

import java.util.List;



import demo.app.entity.Poruka;



public interface PorukaServiceInterface {

	List<Poruka> findAll();
	
	Poruka findOne(long id);
	
	Poruka save(Poruka poruka);
	
	void remove(long id);
	
	

	
}
