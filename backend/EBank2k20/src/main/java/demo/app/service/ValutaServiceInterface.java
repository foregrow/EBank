package demo.app.service;

import java.util.List;


import demo.app.entity.Valuta;



public interface ValutaServiceInterface {

	List<Valuta> findAll();
	
	Valuta findOne(long id);
	
	Valuta save(Valuta valuta);
	
	void remove(long id);
	
	

	
}
